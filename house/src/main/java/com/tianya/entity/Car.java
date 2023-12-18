package com.tianya.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tianya.entity.serializer.BigDecimalSerializer;
import com.tianya.enums.ClientTypeEnum;
import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author changwenbo
 * @date 2023/1/12 16:25
 */
@Slf4j
public class Car {
	private String name;

	private int age;

	private ClientTypeEnum clientTypeEnum;

	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Car() {
	}

	public Car(String name, int age, BigDecimal amount) {
		this.name = name;
		this.age = age;
		this.amount = amount;
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

	public ClientTypeEnum getClientTypeEnum() {
		return clientTypeEnum;
	}

	public void setClientTypeEnum(ClientTypeEnum clientTypeEnum) {
		this.clientTypeEnum = clientTypeEnum;
	}

	@Override
	public String toString() {
		return GsonUtils.toJson(this);
	}
}
