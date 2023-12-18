package com.tianya.init.monitor;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2022/3/9 15:49
 */
@Slf4j
@Component
public class DataSourceInitializer implements InitializingBean {
	@Autowired
	private DataSource dataSource;

	@Autowired(required = false)
	private Map<String, DataSource> dataSourceMap;


	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("dataSource = {}", dataSource);
		log.info("size = {}, dataSourceMap = {}", dataSourceMap.size(), dataSourceMap);

		for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
			if (entry.getValue() instanceof DruidDataSource) {
				String key = entry.getKey();
				DruidDataSource druidDataSource = (DruidDataSource) entry.getValue();
				log.info("key = {}", key);
				int minIdle = druidDataSource.getMinIdle();
				int maxActive = druidDataSource.getMaxActive();
				log.info("minIdle = {}, maxActive = {}", minIdle, maxActive);
			}

			if (entry.getValue() instanceof HikariDataSource) {
				String key = entry.getKey();
				HikariDataSource hikariDataSource = (HikariDataSource) entry.getValue();
				HikariPoolMXBean hikariPoolMXBean = hikariDataSource.getHikariPoolMXBean();
				HikariConfigMXBean hikariConfigMXBean = hikariDataSource.getHikariConfigMXBean();
				log.info("hikariPoolMXBean = {}, hikariConfigMXBean = {}", hikariPoolMXBean, hikariConfigMXBean);
			}
		}
	}

}





















