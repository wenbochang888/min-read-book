package com.tianya.entity;

import com.tianya.util.GsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * @author changwenbo
 * @date 2023/1/6 11:10
 */
@Data
@Slf4j
public class HouseA {
	private String name;

	private int age;

	private LocalDate creditNo;

	public HouseA() {
		log.info("HouseA empty construct...");
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
