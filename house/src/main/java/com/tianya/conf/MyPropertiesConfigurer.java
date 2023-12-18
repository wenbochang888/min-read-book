package com.tianya.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author changwenbo
 * @date 2023/1/10 19:57
 */
@Slf4j
public class MyPropertiesConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		log.info("k = {}, v = {}", propertyName, propertyValue);
		if (propertyName.contains("name")) {
			return "zhangsan123";
		}
		return convertPropertyValue(propertyValue);
	}

//	@Override
//	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
//			throws BeansException {
//		super.processProperties(beanFactoryToProcess, props);
//
//		log.info("props = {}", GsonUtils.toJson(props));
//		props.setProperty("person.name2", "zhangsan333");
//
//	}
}
