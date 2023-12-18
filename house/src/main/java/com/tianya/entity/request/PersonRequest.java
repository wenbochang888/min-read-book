package com.tianya.entity.request;

/**
 * @author changwenbo
 * @date 2023/3/10 14:44
 */
public class PersonRequest implements CommonRequestInterface {
	private String name;

	private int age;

	public PersonRequest() {
	}

	public PersonRequest(String name, int age) {
		this.age = age;
		this.name = name;
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
}
