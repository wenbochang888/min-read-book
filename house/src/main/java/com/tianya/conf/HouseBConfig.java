package com.tianya.conf;

import com.tianya.entity.HouseB;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changwenbo
 * @date 2023/1/12 16:19
 */
@Configuration
public class HouseBConfig {

	// houseAll 引入了 houseB 会导致ConfigurationProperties注入失效，暂不清楚原因
	@Bean
	@ConfigurationProperties("house.person")
	public HouseB houseB() {
		return new HouseB();
	}
}
