package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/17 17:40
 */
public class Main238 {
	public int[] productExceptSelf(int[] nums) {
		int[] preArray = new int[nums.length];
		int[] suffixArray = new int[nums.length];

		preArray[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			preArray[i] = preArray[i - 1] * nums[i];
		}

		suffixArray[nums.length - 1] = nums[nums.length - 1];
		for (int i = nums.length - 2; i >= 0; i--) {
			suffixArray[i] = suffixArray[i + 1] * nums[i];
		}

		int[] res = new int[nums.length];
		res[0] = suffixArray[1];
		res[nums.length - 1] = preArray[nums.length - 2];
		for (int i = 1; i < nums.length - 1; i++) {
			res[i]  = preArray[i - 1] * suffixArray[i + 1];
		}

		return res;
	}
}
