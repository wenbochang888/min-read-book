package com.tianya.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author changwenbo
 * @date 2023/3/28 15:19
 */
@Slf4j
public class MonitorThreadPoolExecutor extends ThreadPoolExecutor {
	private final Cache<Runnable, Long> taskCache = CacheBuilder.newBuilder()
			.expireAfterWrite(30, TimeUnit.MINUTES)
			.maximumSize(2000)
			.build();

	public MonitorThreadPoolExecutor(int corePoolSize,
	                                 int maximumPoolSize,
	                                 long keepAliveTime,
	                                 TimeUnit unit,
	                                 BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public MonitorThreadPoolExecutor(int corePoolSize,
	                                 int maximumPoolSize,
	                                 long keepAliveTime,
	                                 TimeUnit unit,
	                                 BlockingQueue<Runnable> workQueue,
	                                 ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	public MonitorThreadPoolExecutor(int corePoolSize,
	                                 int maximumPoolSize,
	                                 long keepAliveTime,
	                                 TimeUnit unit,
	                                 BlockingQueue<Runnable> workQueue,
	                                 String threadName) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, getThreadFactory(threadName));
	}

	public MonitorThreadPoolExecutor(int corePoolSize,
	                                 int maximumPoolSize,
	                                 long keepAliveTime,
	                                 TimeUnit unit,
	                                 BlockingQueue<Runnable> workQueue,
	                                 RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public MonitorThreadPoolExecutor(int corePoolSize,
	                                 int maximumPoolSize,
	                                 long keepAliveTime,
	                                 TimeUnit unit,
	                                 BlockingQueue<Runnable> workQueue,
	                                 ThreadFactory threadFactory,
	                                 RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	public MonitorThreadPoolExecutor(int corePoolSize,
	                                 int maximumPoolSize,
	                                 long keepAliveTime,
	                                 TimeUnit unit,
	                                 BlockingQueue<Runnable> workQueue,
	                                 String threadName,
	                                 RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, getThreadFactory(threadName), handler);
	}


	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		taskCache.put(r, System.currentTimeMillis());
	}

	@Override
	public void execute(Runnable command) {
		super.execute(wrap(command, getContextForTask()));
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Long time = taskCache.getIfPresent(r);
		if (time != null) {
			//log.info("task run time = {}ms", System.currentTimeMillis() - time);
			taskCache.invalidate(r);
		}
	}

	private static ThreadFactory getThreadFactory(String threadName) {
		return new CustomizableThreadFactory(threadName + "-");
	}

	private Map<String, String> getContextForTask() {
		return MDC.getCopyOfContextMap();
	}

	private Runnable wrap(Runnable runnable, Map<String, String> context) {
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
