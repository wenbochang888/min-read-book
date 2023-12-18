package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/11/1 19:43
 */
public class Main108 {
	public TreeNode sortedArrayToBST(int[] nums) {
		return dfs(nums, 0, nums.length - 1);
	}

	public TreeNode dfs(int[] a, int left, int right) {
		if (left > right) {
			return null;
		}

		int mid = (left + right) >> 1;
		TreeNode node = new TreeNode(a[mid]);
		node.left = dfs(a, left, mid - 1);
		node.right = dfs(a, mid + 1, right);

		return node;
	}
}
