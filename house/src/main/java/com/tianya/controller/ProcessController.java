package com.tianya.controller;

import com.google.common.collect.Lists;
import com.tianya.entity.context.PersonContext;
import com.tianya.entity.request.PersonRequest;
import com.tianya.service.HouseService;
import com.tianya.service.process.PersonProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author changwenbo
 * @date 2022/6/14 11:17
 */
@RestController
@Slf4j
public class ProcessController extends PersonProcess {

	@Autowired
	private HouseService houseService;

	@RequestMapping("/test/process")
	public List<String> testPublish0(PersonRequest personRequest, HttpServletRequest httpServletRequest) {
		PersonContext context = new PersonContext(personRequest, httpServletRequest);
		return process(context, () -> houseService.transferStrToMD(Lists.newArrayList("zs", "ls")));
	}
}

























