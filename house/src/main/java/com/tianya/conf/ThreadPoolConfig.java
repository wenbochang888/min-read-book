package com.tianya.conf;

import com.tianya.service.MdcThreadPoolTaskExecutor;
import com.tianya.service.MonitorThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author changwenbo
 * @date 2023/3/28 15:44
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

	@Bean
	public ThreadPoolExecutor executorService() {
		ThreadPoolExecutor ex = new MonitorThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024));
		return ex;
	}

	// 可以解决线程池日志一些问题，详细看MdcThreadPoolTaskExecutor
	// https://blog.csdn.net/u011937566/article/details/122664268
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		MdcThreadPoolTaskExecutor executor = new MdcThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(30);
		executor.setQueueCapacity(1000);
		executor.setKeepAliveSeconds(3000);
		executor.setThreadNamePrefix("thread-execute");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		return executor;
	}

}
