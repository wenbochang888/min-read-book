package com.tianya.entity.request;

/**
 * @author changwenbo
 * @date 2023/3/10 14:43
 */
public class CommonRequest implements CommonRequestInterface{
	private String logId;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
}
