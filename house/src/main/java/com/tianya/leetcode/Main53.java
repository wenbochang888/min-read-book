package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/17 15:23
 */
public class Main53 {
	public static void main(String[] args) {

	}


	public int maxSubArray(int[] nums) {
		int max = nums[0];
		int[] dp = new int[nums.length];
		dp[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (dp[i - 1] > 0) {
				dp[i] = dp[i - 1] + nums[i];
			} else {
				dp[i] = nums[i];
			}

			max = Math.max(max, dp[i]);

		}



		return max;
	}
}
