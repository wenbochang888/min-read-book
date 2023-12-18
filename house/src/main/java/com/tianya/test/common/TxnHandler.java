package com.tianya.test.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2023/7/28 10:03
 */
@Slf4j
public abstract class TxnHandler<T> implements Handler<T, Boolean> {
	protected static final Boolean CONTINUE = Boolean.TRUE;
	protected static final Boolean FINISH = Boolean.FALSE;


	@Override
	public Boolean handle(T context) {
		Boolean result = Boolean.TRUE;
		Boolean preHandle = preHandle(context);
		String handlerName = getClass().getSimpleName();
		log.info("[活动执行规则]{} preHandle: {}", handlerName, preHandle);

		if (preHandle) {
			try {
				log.info("[活动执行规则] {} executing.", handlerName);
				result = onHandle(context);
			} catch (Throwable e) {
				log.warn("error = {}", e.getMessage(), e);
				throw e;
			} finally {
				postHandle(context);
			}
		}

		log.info("[活动执行规则] {} result: {}", handlerName, result);
		return result;
	}


	/**
	 * 处理器前置处理，在处理业务逻辑前执行
	 *
	 * @param context
	 *
	 * @return 如果返回{@code true}，进入处理器处理业务逻辑，否则跳过该处理器
	 */
	protected Boolean preHandle(T context) {
		return true;
	}

	/**
	 * 处理器业务处理单元
	 *
	 * @param context
	 *
	 * @return 如果返回{@code CONTINUE}继续执行下一个处理器，返回{@code FINISH}结束执行处理器链
	 */
	protected abstract Boolean onHandle(T context);

	/**
	 * 处理器后置处理，在处理业务逻辑后执行
	 *
	 * @param context
	 */
	protected void postHandle(T context) {
	}

	public abstract String getRulerHandlerName();
}
