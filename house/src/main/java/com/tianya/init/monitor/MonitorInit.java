package com.tianya.init.monitor;

import com.alibaba.druid.pool.DruidDataSource;
import com.tianya.entity.BeanNoId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author changwenbo
 * @date 2023/3/20 14:42
 */
@Slf4j
@Component
public class MonitorInit {
	@Autowired(required = false)
	private Map<String, DruidDataSource> dataSourceMap;

	// 只有被spring管理的bean 才会被管理，会被记录
	@Autowired(required = false)
	private Map<String, ThreadPoolExecutor> executorBeanMap;

	@Autowired
	private Map<String, BeanNoId> beanNoIdMap;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		log.info("dataSourceMap = {}", dataSourceMap);
		log.info("executorBeanMap = {}", executorBeanMap);
		log.info("beanNoIdMap = {}", beanNoIdMap);

		Map<String, BeanNoId> beansOfType = applicationContext.getBeansOfType(BeanNoId.class);
		log.info("beansOfType = {}", beansOfType);
	}

}
