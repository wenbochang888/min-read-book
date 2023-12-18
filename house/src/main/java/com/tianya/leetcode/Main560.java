package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/10/15 17:06
 */
public class Main560 {
	public static void main(String[] args) {
		new Main560().subarraySum(new int[]{1,2,1,2,1}, 3);
	}
	public int subarraySum(int[] nums, int k) {

		int res = 0;
		for (int left = 0; left < nums.length; left++) {
			int sum = 0;
			for (int right = left; right < nums.length; right++) {
				sum += nums[right];
				if (sum == k) {
					res++;
				}
			}
		}

		//System.out.println(res);

		return res;
	}
}
