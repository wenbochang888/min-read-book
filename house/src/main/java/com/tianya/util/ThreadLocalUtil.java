package com.tianya.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.Data;
import org.redisson.api.RLock;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changwenbo
 * @date 2023/9/5 16:38
 */
public class ThreadLocalUtil {


	private static final ThreadLocal<Map<Class<?>, Object>> THREAD_LOCAL = ThreadUtil.createThreadLocal(false);

	private ThreadLocalUtil() {}

	public static <T> void setObj(T t) {
		if (t == null) {
			return;
		}

		if (THREAD_LOCAL.get() == null) {
			THREAD_LOCAL.set(new ConcurrentHashMap<>(16));
		}


		Map<Class<?>, Object> threadLocalMap = THREAD_LOCAL.get();
		threadLocalMap.put(t.getClass(), t);
	}

	public static <T> T getObj(Class<T> clazz) {
		Map<Class<?>, Object> threadLocalMap = Optional.ofNullable(THREAD_LOCAL.get()).orElseGet(ConcurrentHashMap::new);
		Object obj = threadLocalMap.get(clazz);
		if (obj == null) {
			return null;
		}
		return BeanUtil.toBean(obj, clazz);
	}

	public static void remove() {
		THREAD_LOCAL.remove();
	}


	@Data
	public static class LockEntity {
		private RLock rLock;
	}
}

