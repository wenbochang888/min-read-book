package com.tianya.util.loadbalance;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author changwenbo
 * @date 2023/7/14 15:50
 */
public class RandomLoadBalance implements LoadBalance {

	// 完全随机算法
	public Server select(List<Server> serverList) {
		if (CollectionUtils.isEmpty(serverList)) {
			return null;
		}

		int seed = ThreadLocalRandom.current().nextInt(serverList.size());
		return serverList.get(seed);
	}
}
