package com.tianya.init;

import com.tianya.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author changwenbo
 * @date 2022/3/9 15:49
 */
@Slf4j
@Component
public class CacheInitializer implements InitializingBean, ApplicationRunner, CommandLineRunner,
		 ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private Car car;

	@Value("${person.name2:moren}")
	private String name;

	@Value("${person.age2:moren}")
	private String age;

	static {
	}

	// 1
	public CacheInitializer() {
		log.info("construct  --> applicationContext = {}", applicationContext);
	}

	// 2
	@PostConstruct
	public void postCut() {
		log.info("PostConstruct  --> applicationContext = {}", applicationContext);
	}

	// 3
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("name = {}, age = {}", name, age);
	}

	// 4 ApplicationRunner
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("ApplicationRunner  -->");
	}


	// 5 CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		log.info("run  -->");
	}

	// 6 ApplicationListener<ApplicationReadyEvent>
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info("onApplicationEvent  ApplicationReadyEvent --> car = {}", car);
	}
}
