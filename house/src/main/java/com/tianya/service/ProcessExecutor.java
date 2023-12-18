package com.tianya.service;

/**
 * @author changwenbo
 * @date 2023/3/10 14:33
 */
@FunctionalInterface
public interface ProcessExecutor<T> {
	T execute() throws Exception;
}
