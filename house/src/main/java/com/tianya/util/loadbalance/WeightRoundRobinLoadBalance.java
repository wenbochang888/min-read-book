package com.tianya.util.loadbalance;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author changwenbo
 * @date 2023/7/14 17:48
 */
public class WeightRoundRobinLoadBalance implements LoadBalance {

	int cur = 0;

	// 权重轮询算法
	@Override
	public Server select(List<Server> serverList) {
		if (CollectionUtils.isEmpty(serverList)) {
			return null;
		}

		int[] weightArr = new int[serverList.size()];
		int totalWeight = 0;
		for (int i = 0; i < serverList.size(); i++) {
			Server server = serverList.get(i);
			totalWeight += server.getWeight();
			weightArr[i] = totalWeight;
		}

		cur = cur >= totalWeight ? 1 : ++cur;
		for (int i = 0; i < serverList.size(); i++) {
			if (cur <= weightArr[i]) {
				return serverList.get(i);
			}
		}

		return null;
	}
}
