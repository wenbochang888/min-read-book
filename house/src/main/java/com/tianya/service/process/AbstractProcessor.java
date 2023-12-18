package com.tianya.service.process;

import com.tianya.service.ProcessExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2023/3/10 14:34
 */
@Slf4j
public abstract class AbstractProcessor<REQ, RESP> {

	public RESP process(REQ request, ProcessExecutor<RESP> executor) {
		try {
			preHandle(request, executor);
			return onHandle(request, executor);
		} catch (Exception e) {
			// 抛异常
			log.error("e = {}", e.getMessage(), e);
			return onError(request, executor);
		} finally {
			postHandle(request, executor);
		}
	}

	protected void preHandle(REQ request, ProcessExecutor<RESP> executor) throws Exception {
	}

	protected RESP onHandle(REQ request, ProcessExecutor<RESP> executor) throws Exception {
		try {
			return executor.execute();
		} catch (Exception e) {
			throw e;
		}
	}

	protected abstract RESP onError(REQ request, ProcessExecutor<RESP> executor);

	protected void postHandle(REQ request, ProcessExecutor<RESP> executor) {
	}
}
