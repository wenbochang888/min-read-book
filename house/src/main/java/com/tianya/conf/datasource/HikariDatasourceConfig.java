package com.tianya.conf.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author changwenbo
 * @date 2023/3/24 16:07
 */
@Configuration
public class HikariDatasourceConfig {

	/**
	 * hikari + jade
	 */

	@Bean("jade.dataSource.hikari")
	@ConfigurationProperties("spring.datasource.hikari")
	public DataSource hikariDataSource(){
		HikariDataSource hikariDataSource = new HikariDataSource();
		return hikariDataSource;
	}
}
