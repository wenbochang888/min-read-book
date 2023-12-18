package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/10 16:35
 */
public class Main283 {


	public void moveZeroes(int[] nums) {
		int slow = 0;
		int fast = 0;

		while (slow <= fast && fast < nums.length) {
			if (nums[fast] != 0) {
				nums[slow++] = nums[fast++];
			} else {
				fast++;
			}
		}
		for (int i = slow; i < fast; i++) {
			nums[i] = 0;
		}
	}
}
