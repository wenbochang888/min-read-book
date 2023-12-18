package com.tianya.leetcode.leetocde_360;

import java.util.HashSet;
import java.util.Set;

/**
 * @author changwenbo
 * @date 2023/9/2 18:00
 */
public class Main2 {

	public long minimumPossibleSum(int n, int target) {
		long res = 0L;
		Set<Long> removeSet = new HashSet<>();
		Set<Long> nums = new HashSet<>();

		Long cnt = 1L;
		while (true) {
			if (nums.size() == n) {
				break;
			}
			if (!removeSet.contains(cnt)) {
				nums.add(cnt);
				res += cnt;
				removeSet.add(target - cnt);
			}

			cnt++;
		}


		return res;
	}
}
