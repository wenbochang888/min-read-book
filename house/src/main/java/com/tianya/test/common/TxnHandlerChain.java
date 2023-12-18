package com.tianya.test.common;

import com.google.common.collect.Lists;
import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2023/7/28 10:29
 */
@Slf4j
@Component
public class TxnHandlerChain {

	@Autowired
	private ApplicationContext applicationContext;

	private Map<String, Handler> handlerMap = new HashMap<>();

	public List<Handler> getActivityHandler(String activityId) {
		List<Handler> handlersRules = new ArrayList<>();

		List<String> rulersName = getRulerName(activityId);
		if (CollectionUtils.isEmpty(rulersName)) {
			return handlersRules;
		}

		for (String rulerName : rulersName) {
			Handler handler = handlerMap.get(rulerName);
			if (handler != null) {
				handlersRules.add(handler);
			}
		}

		return handlersRules;
	}

	private static List<String> getRulerName(String activityId) {
		return Lists.newArrayList(RulerEnum.CREDIT_PASS_NOT_LOAN_DATE.getName(), RulerEnum.LOAN_CREDIT_EXPIRE_DATE.getName());
	}

	@PostConstruct
	public void init() {
		Map<String, Handler> beansMap = applicationContext.getBeansOfType(Handler.class);
		beansMap.forEach((k, v) -> handlerMap.put(v.getRulerHandlerName(), v));
		log.info("handlerMap = {}", GsonUtils.toJson(handlerMap));
	}

}















