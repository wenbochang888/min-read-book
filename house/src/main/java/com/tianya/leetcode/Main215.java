package com.tianya.leetcode;

public class Main215 {
	public  int findKthLargest(int[] nums, int k) {
		return sort(nums, 0, nums.length - 1, nums.length - k);
	}

	public static int sort(int[] a, int left, int right, int index) {
		if (left < right) {
			int mid = qSort(a, left, right);
			if (mid == index) {
				return a[mid];
			}
			if (mid < index) {
				return sort(a, mid + 1, right, index);
			} else {
				return sort(a, left, mid - 1, index);
			}
		}

		return a[index];
	}

	private static int qSort(int[] a, int left, int right) {
		int p = getIndex(a, left, right);
		while (left < right) {
			while (left < right && a[right] >= p) {
				right--;
			}
			a[left] = a[right];

			while (left < right && a[left] <= p) {
				left++;
			}
			a[right] = a[left];
		}

		a[left] = p;

		return left;
	}

	public static int getIndex(int[] a, int left, int right) {
		int mid = left + ((right - left) >> 1);
		int t = a[left];
		a[left] = a[mid];
		a[mid] = t;
		return a[left];
	}
}
