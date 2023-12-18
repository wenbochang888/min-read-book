package com.tianya.exception;

/**
 * @author changwenbo
 * @date 2022/6/28 10:08
 */
public class CashException extends RuntimeException {
	private String code;

	public CashException() {
		super();
	}

	public CashException(String code) {
		super(code);
		this.code = code;
	}

	public CashException(String code, String message) {
		super(message);
		this.code = code;
	}

	public CashException(String code, Throwable throwable) {
		super(code, throwable);
		this.code = code;
	}

	public CashException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
