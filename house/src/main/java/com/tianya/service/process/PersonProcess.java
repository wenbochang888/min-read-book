package com.tianya.service.process;

import com.google.common.collect.Lists;
import com.tianya.entity.context.PersonContext;
import com.tianya.entity.request.PersonRequest;
import com.tianya.service.ProcessExecutor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author changwenbo
 * @date 2023/3/10 15:30
 */
public class PersonProcess extends AbstractProcessor<PersonContext, List<String>> {

	@Override
	protected void preHandle(PersonContext context, ProcessExecutor<List<String>> executor) throws Exception {
		PersonRequest person = context.getPerson();
		// check request param
		int age = person.getAge();
		String name = person.getName();
		if (age < 18 || StringUtils.isEmpty(name)) {
			throw new RuntimeException("age = " + age + " exceed");
		}
	}

	@Override
	protected List<String> onError(PersonContext request, ProcessExecutor<List<String>> executor) {
		return Lists.newArrayList();
	}
}
