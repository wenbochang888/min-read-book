package com.tianya.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.tianya.dao3.UserMapper;
import com.tianya.entity.TElement;
import com.tianya.entity.User;
import com.tianya.service.TelementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author changwenbo
 * @date 2022/6/14 11:17
 */
@RestController
@Slf4j
public class MybatisPlusController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TelementService service;

	@RequestMapping("/test/mybatis/test")
	public String test() {
		TElement element = new TElement();
		element.setfElementName("111");

		log.info("element = {}", JSON.toJSONString(element));
		boolean save = service.save(element);
		log.info("save = {}, element = {}", save, JSON.toJSONString(element));


		return "ok";
	}


	@RequestMapping("/test/mybatis/page")
	public String testPublish0(String str, int page, int current) {


		List<Integer> list = Lists.newArrayList(1, 2,3 ,4,5 ,6);
		List<Integer> remove = Lists.newArrayList(2, 5);

		System.out.println(list);

		list.removeIf(remove::contains);

		System.out.println(list);

		return "ok";
	}

	@RequestMapping("/test/mybatis/select/one")
	public String testPublish1() {

		LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
		queryWrapper.last("limit 1");

		User user = userMapper.selectOne(queryWrapper);
		System.out.println(user);


		return "ok";
	}

	@RequestMapping("/test/mybatis/delete/id")
	public String deleteById(String id) {

		LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
		queryWrapper.eq(User::getId, id);

		int delete = userMapper.delete(queryWrapper);
		log.info("delete = {}", delete);

		return "ok";
	}

	@RequestMapping("/test/mybatis/insert/id")
	public String insertById(String id) {

		User user = new User();
		user.setId(NumberUtils.toLong(id));
		user.setAge(18);
		user.setName("lucas");
		user.setEmail("123456@qq.com");

		int insert = userMapper.insert(user);
		log.info("insert = {}", insert);

		return "ok";
	}
}

























