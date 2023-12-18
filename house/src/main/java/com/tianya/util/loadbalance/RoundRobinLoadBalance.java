package com.tianya.util.loadbalance;

import java.util.List;

/**
 * @author changwenbo
 * @date 2023/7/14 17:48
 */
public class RoundRobinLoadBalance implements LoadBalance {

	int cur = 0;

	// 轮询算法
	@Override
	public Server select(List<Server> serverList) {
		if (serverList != null && serverList.size() > 0) {
			if (cur >= serverList.size()) {
				cur = 0;
			}
			return serverList.get(cur++);
		}
		return null;
	}
}
