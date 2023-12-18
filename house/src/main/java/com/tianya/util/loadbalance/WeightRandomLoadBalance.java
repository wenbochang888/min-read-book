package com.tianya.util.loadbalance;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author changwenbo
 * @date 2023/7/14 15:50
 */
public class WeightRandomLoadBalance implements LoadBalance {

	// 加权随机
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


		if (totalWeight == 0) {
			return null;
		}

		int offset = ThreadLocalRandom.current().nextInt(totalWeight);
		for (int i = 0; i < serverList.size(); i++) {
			if (offset < weightArr[i]) {
				return serverList.get(i);
			}
		}

		return null;
	}
}
