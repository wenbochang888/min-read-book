package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/10 17:33
 */
public class Main42 {
	public int trap(int[] height) {
		int[] preMax = new int[height.length];
		preMax[0] = height[0];
		for (int i = 1; i < height.length; i++) {
			preMax[i] = Math.max(preMax[i - 1], height[i]);
		}

		int[] sufMax = new int[height.length];
		sufMax[height.length - 1] = height[height.length - 1];
		for (int i = height.length - 2; i >= 0; i--) {
			sufMax[i] = Math.max(sufMax[i + 1], height[i]);
		}

		int max = 0;
		for (int i = 0; i < height.length; i++) {
			max += (Math.min(preMax[i], sufMax[i])) - height[i];
		}


		return max;
	}
}
