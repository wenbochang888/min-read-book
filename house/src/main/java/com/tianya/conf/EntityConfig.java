package com.tianya.conf;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author changwenbo
 * @date 2023/1/6 11:25
 */
@Slf4j
@Configuration
public class EntityConfig {
	@Primary
	@Bean
	public PropertyPlaceholderConfigurer configurer() {
		MyPropertiesConfigurer configurer = new MyPropertiesConfigurer();
		configurer.setIgnoreUnresolvablePlaceholders(true);
		configurer.setOrder(1);

		List<Resource> resources = new ArrayList<>();
		resources.add(new ClassPathResource("key.properties"));
		configurer.setLocations(resources.toArray(new Resource[0]));
		return configurer;
	}


	@Bean
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	public TransactionTemplate transactionTemplate(DataSource dataSource) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(platformTransactionManager(dataSource));
		// 事务隔离级别  读未提交
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
		transactionTemplate.setTimeout(300);
		return transactionTemplate;
	}

	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}

}
