package com.tianya.controller;

import com.alibaba.fastjson.JSON;
import com.tianya.entity.Car;
import com.tianya.entity.Person;
import com.tianya.entity.request.Son;
import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author changwenbo
 * @date 2022/6/14 11:17
 */
@RestController
@Slf4j
public class RequestParamController {


	@RequestMapping("/request/param/test0")
	public void test0(BigDecimal amount, @RequestHeader("code") String code) {

		log.info("amount = {}, code = {}", amount, code);
		log.info("amount = {}, code = {}", JSON.toJSONString(amount), GsonUtils.toJson(code));

		BigDecimal multiply = amount.multiply(BigDecimal.valueOf(100));
		log.info("multiply = {}", multiply);
		log.info("multiply = {}", JSON.toJSONString(multiply));
	}

	@RequestMapping("/request/param/test1")
	public void test1(@RequestBody Person person) {
		log.info("person = {}", person);

	}

	@RequestMapping("/request/param/test2")
	public Son test2(@RequestBody Son son) {
		log.info("son = {}", GsonUtils.toJson(son));
		log.info("person = {}", son.getPerson());

		return son;
	}

	@GetMapping("/request/param/test3")
	public Object test3(String name) {
		log.info("name = {}", name);

		return name;
	}

	@GetMapping("/request/param/test4")
	public Car test4() {

		Car car = new Car();
		car.setName("zhangsan");
		car.setAmount(BigDecimal.valueOf(12345));

		return car;
	}

	@RequestMapping("/request/param/test5")
	public BigDecimal test5(BigDecimal amount) {

		log.info("amount = {}", amount);
		BigDecimal multiply = amount.add(BigDecimal.valueOf(100L));
		return multiply;
	}

	@RequestMapping("/request/param/test6")
	public LocalDateTime test6(LocalDateTime time) {

		log.info("time = {}", time);
		LocalDateTime localDateTime = time.plusDays(1);

		return localDateTime;
	}

	@RequestMapping("/request/param/test7")
	public Date test7(Date date) {
		log.info("date = {}", date);
		return date;
	}

}

























