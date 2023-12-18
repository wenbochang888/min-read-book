package com.tianya.util.loadbalance;

import java.util.List;

/**
 * @author changwenbo
 * @date 2023/7/14 15:54
 */
public interface LoadBalance {
	Server select(List<Server> serverList);
}
