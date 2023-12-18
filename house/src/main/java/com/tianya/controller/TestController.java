package com.tianya.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianya.dao3.UserMapper;
import com.tianya.entity.User;
import com.tianya.enums.ClientTypeEnum;
import com.tianya.test.ActivityContext;
import com.tianya.test.CreditPassNotLoanDate;
import com.tianya.test.LoanCreditExpireDate;
import com.tianya.test.common.Handler;
import com.tianya.test.common.TxnHandlerChain;
import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author changwenbo
 * @date 2022/6/14 11:17
 */
@RestController
@Slf4j
public class TestController {

	@Autowired
	private ApplicationContext applicationContext;

//	@Resource
//	private TElementMapper mapper;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ExecutorService es;

	@Resource
	private ThreadPoolTaskExecutor executor;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MybatisPlusInterceptor mybatisPlusInterceptor;


	@RequestMapping("/ce74d92d2f1f94905343c8daa563eb9e.txt")
	public String minProgram() {
		return "eea1a0350309e799734ab9027d73521c3c3227e6";
	}


	@RequestMapping("/test/publish")
	public String testPublish0(String str, ClientTypeEnum clientType) {

		log.info("type = {}", GsonUtils.toJson(clientType));

		List<InnerInterceptor> interceptors = mybatisPlusInterceptor.getInterceptors();
		System.out.println(interceptors);


		LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
		queryWrapper.select(User::getId, User::getName, User::getAge); // select id, name, age
		queryWrapper.gt(User::getAge, 1) // > 20
				.likeRight(StringUtils.isNotEmpty(str), User::getName, str) // str != null ==> name like 'str%'
				;//.last("limit 1"); // limit 1

		Page<User> userPage = new Page<>(1, 10);
		Page<User> userPageList = userMapper.selectPage(userPage, null);


		System.out.println(GsonUtils.toJson(userPageList));
		List<User> records = userPageList.getRecords();
		System.out.println(GsonUtils.toJson(records));

		return "ok";
	}

	@RequestMapping("/test/publish/submit")
	public String testPublish1() {

		LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
		queryWrapper.last("limit 1");

		User user = userMapper.selectOne(queryWrapper);
		System.out.println(user);


		return "ok";
	}


	@Autowired
	private TxnHandlerChain txnHandlerChain;

	@Autowired
	private CreditPassNotLoanDate creditPassNotLoanDate;

	@Autowired
	private LoanCreditExpireDate loanCreditExpireDate;

	@RequestMapping("/test/publish/submit2")
	public String testPublish2() {

		List<Handler> activityHandler = txnHandlerChain.getActivityHandler("1");
		log.info("handler = {}", GsonUtils.toJson(activityHandler));

		ActivityContext context = new ActivityContext();
		for (Handler handler : activityHandler) {
			handler.handle(context);
		}

		log.info("============");

		log.info(creditPassNotLoanDate.getRulerHandlerName());
		log.info(loanCreditExpireDate.getRulerHandlerName());


		return "ok";
	}

}

























