package com.tianya.entity;

/**
 * @author changwenbo
 * @date 2023/11/28 15:19
 */
public interface ErrorCode {
	/**
	 * 返回响应码
	 * @return Integer
	 */
	Integer getRet();

	/**
	 * 返回响应信息
	 * @return String
	 */
	String getMsg();
}

