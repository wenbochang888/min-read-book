package com.tianya.controller;

import cn.hutool.core.util.ObjectUtil;
import com.tianya.util.GsonUtils;
import com.tianya.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author changwenbo
 * @date 2022/6/14 11:17
 */
@RequestMapping("/test/redis")
@RestController
@Slf4j
public class RedisController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedissonClient redissonClient;

	@RequestMapping("/get/key")
	public Object get(String key, String operator) {

		Object val = "";
		try {
			if (StringUtils.isEmpty(operator) || operator.equals("string")) {
				val = stringRedisTemplate.opsForValue().get(key);

			} else if (operator.equals("hash")) {
				val = stringRedisTemplate.opsForHash().entries(key);
			}

			log.info("val = {}", GsonUtils.toJson(val));
		} catch (Exception e) {
			log.error("e = {}", e.getMessage(), e);
		}

		return ObjectUtil.isNull(val) ? "ok" : val;
	}

	@RequestMapping("/set/key")
	public String set(String key, String val) {

		try {
			stringRedisTemplate.opsForValue().set(key, val, 60, TimeUnit.MINUTES);

		} catch (Exception e) {
			log.error("e = {}", e.getMessage(), e);
		}

		return "ok";
	}


	@RequestMapping("/lock/key/b")
	public String lockB(String key) {


		RLock lock = redissonClient.getLock(key);
		try {
			lock.tryLock(1, TimeUnit.SECONDS);


			TimeUnit.MINUTES.sleep(30);


		} catch (Exception e) {}
		finally {
			lock.unlock();
		}

		return "";
	}

	@RequestMapping("/lock/key")
	public String lock(String key) {

		for (int i = 0; i < 2; i++) {
			test(key + i);
			testSync(key + i);
		}

		return "ok";
	}

	private void test(String key) {
		RLock lock = redissonClient.getLock(key);
		ThreadLocalUtil.LockEntity lockEntity = new ThreadLocalUtil.LockEntity();
		lockEntity.setRLock(lock);
		ThreadLocalUtil.setObj(lockEntity);


		int cnt = 0;
		try {
			boolean flag = lock.tryLock(1, TimeUnit.SECONDS);
			if (flag) {
				cnt++;
				log.info("testA-{}----->>>  get lock success, cnt = {}", key, cnt);
			} else {
				log.info("testA-{}----->>>  get lock fail = {}", key, flag);
			}

			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {

		} finally {
			ThreadLocalUtil.LockEntity obj = ThreadLocalUtil.getObj(ThreadLocalUtil.LockEntity.class);
			RLock lock2 = obj.getRLock();
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
				cnt--;
				log.info("testA-{}---->>>  release lock cnt = {}", key, cnt);
			} else {
				log.info("testA-{}---->>>  not hold lock", key);
			}
		}
	}


	private void testSync(String key) {
		CompletableFuture.supplyAsync(() -> {
			RLock lock = redissonClient.getLock(key);
			ThreadLocalUtil.LockEntity lockEntity = new ThreadLocalUtil.LockEntity();
			lockEntity.setRLock(lock);
			ThreadLocalUtil.setObj(lockEntity);
			Thread thread = Thread.currentThread();

			int cnt = 0;
			try {
				boolean flag = lock.tryLock(1, TimeUnit.SECONDS);

				if (flag) {
					cnt++;
					log.info("testSync-{}---->>>   get lock success, cnt = {}", key, cnt);
				} else {
					log.info("testSync-{}---->>>   get lock fail = {}", key, flag);
					return "fail";
				}

				TimeUnit.SECONDS.sleep(5);
			} catch (Exception e) {
				log.error("e = {}", e.getMessage(), e);
			} finally {

				ThreadLocalUtil.LockEntity obj = ThreadLocalUtil.getObj(ThreadLocalUtil.LockEntity.class);
				RLock lock2 = obj.getRLock();

				if (lock2.isHeldByCurrentThread()) {
					lock2.unlock();
					cnt--;
					log.info("testSync-{}---->>>  release lock cnt = {}", key, cnt);
				} else {
					log.info("testSync-{}---->>>  not hold lock", key);
				}
			}

			return "";
		});
	}


}

























