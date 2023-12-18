package com.tianya.service;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author changwenbo
 * @date 2023/3/27 14:56
 */
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

	private Map<String, String> getContextForTask() {
		return MDC.getCopyOfContextMap();
	}

	@Override
	public void execute(Runnable command) {
		super.execute(wrap(command, getContextForTask()));
	}

	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(wrap(task, getContextForTask()));
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return super.submit(wrap(task, getContextForTask()));
	}

	/**
	 * @param task
	 * @param context
	 * @param <T>
	 * @return
	 */
	private static <T> Callable<T> wrap(final Callable<T> task, final Map<String, String> context) {
		return () -> {
			if (null != context) {
				MDC.setContextMap(context);
			}

			try {
				return task.call();
			} finally {
				if (!context.isEmpty()) {
					MDC.clear();
				}
			}
		};
	}

	/**
	 *
	 * @param runnable
	 * @param context
	 * @return
	 */
	private static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
		return () -> {
			if (null != context) {
				MDC.setContextMap(context);
			}

			try {
				runnable.run();
			} finally {
				if (!context.isEmpty()) {
					MDC.clear();
				}
			}
		};
	}
}
