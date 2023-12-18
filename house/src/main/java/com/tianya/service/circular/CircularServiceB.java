package com.tianya.service.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author changwenbo
 * @date 2023/3/14 14:07
 */
@Service
public class CircularServiceB {

	@Autowired
	private CircularServiceA circularServiceA;
}
