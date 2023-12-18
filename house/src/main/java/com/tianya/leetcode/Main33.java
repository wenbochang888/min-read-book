package com.tianya.leetcode;


import com.alibaba.fastjson.JSON;

import java.util.concurrent.ThreadLocalRandom;

public class Main33 {
	public static void main(String[] args) {

		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = ThreadLocalRandom.current().nextInt(10);
		}

		System.out.println(JSON.toJSONString(a));
		new Main33().sortArray(a);
		System.out.println(JSON.toJSONString(a));

	}

	public int[] sortArray(int[] nums) {
		sortArray(nums, 0, nums.length - 1);
		return nums;
	}

	public int[] sortArray(int[] a, int left, int right) {
		if (a == null || a.length == 0) {
			return a;
		}

		if (left < right) {
			int mid = quickSort(a, left, right);
			sortArray(a, left, mid - 1);
			sortArray(a, mid + 1, right);
		}

		return a;
	}

	private int quickSort(int[] a, int left, int right) {
		int t = getIndex(a, left, right);

		while (left < right) {
			while (left < right && a[right] >= t) {
				right--;
			}
			a[left] = a[right];

			while (left < right && a[left] <= t) {
				left++;
			}
			a[right] = a[left];
		}

		a[left] = t;
		return left;
	}

	public int getIndex(int[] a, int left, int right) {
		int mid = ThreadLocalRandom.current().nextInt(left, right + 1);

		int t = a[mid];
		a[mid] = a[left];
		a[left] = t;
		return a[left];
	}


	public int search(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (a[mid] == target) {
				return mid;
			}

			// 左边升序
			if (a[mid] >= a[left]) {
				if (a[mid] > target && a[left] <= target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (a[mid] < target && a[right] >= target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}


		}



		return -1;
	}







	public static int binarySearch(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (a[mid] == target) {
				return mid;
			}

			if (a[mid] > target) {
				right = mid - 1;
			}

			if (a[mid] < target) {
				left = mid + 1;
			}
		}


		return -1;
	}

	public static int binarySearchLeft(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (a[mid] == target) {
				right = mid - 1;
			}

			if (a[mid] > target) {
				right = mid - 1;
			}

			if (a[mid] < target) {
				left = mid + 1;
			}
		}

		if (left >= a.length || a[left] != target) {
			return -1;
		}


		return left;
	}

	public static int binarySearchRight(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (a[mid] == target) {
				left = mid + 1;
			}

			if (a[mid] < target) {
				left = mid + 1;
			}

			if (a[mid] > target) {
				right = mid - 1;
			}
		}

		if (right < 0 || a[right] != target) {
			return -1;
		}


		return right;
	}


}
















