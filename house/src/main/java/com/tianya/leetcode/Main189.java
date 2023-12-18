package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/17 15:54
 */
public class Main189 {

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7};
		new Main189().rotate(a, 3);
	}

	public void rotate(int[] nums, int k) {
		k %= nums.length;
		if (k == 0) {
			return;
		}

		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k -1);
		reverse(nums, k , nums.length - 1);
	}


	public void reverse(int[] a, int left, int right) {
		while (left < right) {
			int t = a[left];
			a[left] = a[right];
			a[right] = t;
			left++;
			right--;
		}

	}

}
