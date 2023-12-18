package com.tianya.service.process;

import com.tianya.service.ProcessExecutor;

/**
 * @author changwenbo
 * @date 2023/3/10 15:30
 */
public class ObjectProcess extends AbstractProcessor<Object, Object> {
	@Override
	protected Object onError(Object request, ProcessExecutor<Object> executor) {
		return null;
	}
}
