package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main15 {


	public static void main(String[] args) {


		int[] a = {-1,0,1,2,-1,-4};

		new Main15().threeSum(a);

	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			int z = nums[i];
			// x + y = 0 - z
			Map<Integer, Integer> map = new HashMap<>();
			for (int j = i + 1; j < nums.length; j++) {
				int x = nums[j];
				int y = -x - z;
				if (map.containsKey(y)) {
					List<Integer> list = new ArrayList<>();
					list.add(x);
					list.add(y);
					list.add(z);
					set.add(list);
				}
				map.put(x, x);
			}
		}



		return new ArrayList<>(set);
	}

	// [-4,-1,-1,0,1,2]
	// -4 + x + y = 0
	// x + y = 1



}
