package com.tianya.test;

import com.tianya.test.common.RulerEnum;
import com.tianya.test.common.TxnHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author changwenbo
 * @date 2023/7/28 10:19
 */
@Slf4j
@Component
public class LoanCreditExpireDate extends TxnHandler<ActivityContext> {


	@Override
	protected Boolean onHandle(ActivityContext context) {
		log.info("LoanCreditExpireDate");
		return CONTINUE;
	}

	@Override
	public String getRulerHandlerName() {
		return RulerEnum.LOAN_CREDIT_EXPIRE_DATE.getName();
	}
}
