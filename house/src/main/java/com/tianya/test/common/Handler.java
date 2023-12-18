package com.tianya.test.common;

/**
 * @author changwenbo
 * @date 2023/7/28 10:03
 */
public interface Handler<T, R> {
	/**
	 * 业务处理
	 *
	 * @param context
	 * @return
	 */
	R handle(T context);

	String getRulerHandlerName();
}

