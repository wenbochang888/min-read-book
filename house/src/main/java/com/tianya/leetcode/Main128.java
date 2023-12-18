package com.tianya.leetcode;

import com.tianya.util.GsonUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author changwenbo
 * @date 2023/9/3 14:14
 */
public class Main128 {


	public static void main(String[] args) {
		int[] a = {100,4,200,1,3,2, 5, 6, 2, 3, 11, 12, 13,14, 50};
		System.out.println(new Main128().longestConsecutive(a));
		System.out.println(new Main128().longestConsecutive2(a));
	}

	public int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Arrays.sort(nums);
		System.out.println(GsonUtils.toJson(nums));

		int max = 1;
		int curMax = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] + 1) {
				curMax++;
			}

			else  if (nums[i] == nums[i - 1]) {}

			else {
				curMax = 1;
			}

			max = Math.max(max, curMax);
		}

		System.out.println("max = " + max);
		return max;
	}

	public int longestConsecutive2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}

		int max = 1;
		for (int num : set) {
			int curMax = 1;

			int t = num - 1;
			if (set.contains(t)) {
				continue;
			}

			while (true) {
				if (!set.contains(++num)) {
					break;
				}
				curMax++;
			}

			max = Math.max(max, curMax);
		}


		return max;
	}
}

























