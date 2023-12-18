package com.tianya.entity;

import javax.annotation.PostConstruct;

/**
 * @author changwenbo
 * @date 2023/2/9 14:22
 */
public abstract class AbstractUserService {

	@PostConstruct
	public void init() {
		initList();		
	}

	protected abstract void initList();
}
