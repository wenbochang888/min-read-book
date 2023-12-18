package com.tianya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author changwenbo
 * @date 2023/11/28 15:20
 */

@AllArgsConstructor
@Getter
public enum BaseErrorCode implements ErrorCode {
	SUCCESS(200, "成功"),
	FAILED(500, "系统异常"),
	;

	private final Integer ret;
	private final String msg;



}
