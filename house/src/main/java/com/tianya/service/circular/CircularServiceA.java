package com.tianya.service.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author changwenbo
 * @date 2023/3/14 14:07
 *
 * 测试循环依赖
 */
@Service
public class CircularServiceA {

	@Autowired
	private CircularServiceB circularServiceB;
}
