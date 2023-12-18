package com.tianya.init;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.tianya.entity.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author changwenbo
 * @date 2022/3/9 15:49
 */
@Slf4j
@Component
public class HouseTestInitializer implements InitializingBean {


	/**
	 * Autowired默认使用byType类型注入，就是寻找bean的class
	 * 如果寻找不到，或者多个，则会使用byName进行注册
	 * 如使用userServiceA，看看能否找到唯一。当然可以用Qualifier来指定name的名称
	 * byName优先使用Qualifier，如果没有则会去找对应的变量来
	 */
	@Qualifier("user")
	@Autowired
	private UserService userService;

	/**
	 * Resource默认使用byName进行注册，就是注册bean的 id/name那个字段
	 * 如果通过byName找不到，则会退化为byType进行寻找
	 */
	@Resource(name = "userServiceB")
	private UserService userServiceB;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("userService = {}", userService);
		log.info("userService = {}", userServiceB);
	}

	@PostConstruct
	public void init() {
		log.info("start init FlowRule");
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		rule.setResource("testPublish0");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 20.
		rule.setCount(1);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}

}





















