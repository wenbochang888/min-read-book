package com.tianya.entity;

import lombok.Data;

/**
 * @author changwenbo
 * @date 2023/11/28 15:18
 */
@Data
public class CommonResult<T> {
	private Integer ret;
	private String msg;
	private T data;

	private Boolean retry;

	public CommonResult() {
	}

	public CommonResult(Integer ret, String msg, T data) {
		this.ret = ret;
		this.msg = msg;
		this.data = data;
	}


	/**
	 * 成功返回结果
	 *
	 * @param data 获取的数据
	 */
	public static <T> CommonResult<T> success(T data) {
		return new CommonResult<>(200, BaseErrorCode.SUCCESS.getMsg(), data);
	}

	public static <T> CommonResult<T> failed(ErrorCode errorCode) {
		return new CommonResult<>(errorCode.getRet(), errorCode.getMsg(), null);
	}


	public static <T> CommonResult<T> failed(int ret, String msg, T data) {
		return new CommonResult<>(ret, msg, data);
	}

	public static <T> CommonResult<T> failed(String message) {
		return new CommonResult<>(BaseErrorCode.FAILED.getRet(), message, null);
	}

	public static <T> CommonResult<T> failed() {
		return failed(BaseErrorCode.FAILED);
	}


}