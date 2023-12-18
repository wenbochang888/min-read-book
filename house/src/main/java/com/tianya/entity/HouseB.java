package com.tianya.entity;

import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2023/1/6 11:10
 */
@Slf4j
public class HouseB {
	private String name;

	private int age;

	public HouseB() {
		log.info("HouseB empty construct...");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
