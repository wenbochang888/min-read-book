package com.tianya.entity;

import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2023/1/6 11:10
 */
@Slf4j
public class HouseAll {
	private String name;

	private int age;

	private HouseA houseA;
	private HouseB houseB;

	public HouseAll() {
		log.info("HouseAll empty construct...");
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

	public HouseA getHouseA() {
		return houseA;
	}

	public void setHouseA(HouseA houseA) {
		this.houseA = houseA;
	}

	public HouseB getHouseB() {
		return houseB;
	}

	public void setHouseB(HouseB houseB) {
		this.houseB = houseB;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
