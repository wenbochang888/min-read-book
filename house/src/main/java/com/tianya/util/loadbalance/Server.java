package com.tianya.util.loadbalance;

import com.alibaba.fastjson.JSON;

/**
 * @author changwenbo
 * @date 2023/7/14 15:50
 */
public class Server {
	private String ip;

	private String name;

	private int weight;

	public Server(String ip, String name) {
		this.ip = ip;
		this.name = name;
	}

	public Server(String ip, String name, int weight) {
		this.ip = ip;
		this.name = name;
		this.weight = weight;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
