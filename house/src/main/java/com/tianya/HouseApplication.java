package com.tianya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


//@PropertySource(value = "classpath:key.properties")
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml") // 引入xml配置文件
//@PropertySource("classpath:conf/hsminfo0901.properties")
public class HouseApplication {


	public static void main(String[] args) {

		SpringApplication.run(HouseApplication.class, args);
	}



}
