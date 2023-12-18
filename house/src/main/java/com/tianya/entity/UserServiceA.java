package com.tianya.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2023/2/3 11:20
 */
@Slf4j
public class UserServiceA extends AbstractUserService implements UserService {
	@Override
	protected void initList() {
		log.info("UserServiceA initList this = {}", this);
	}
}
