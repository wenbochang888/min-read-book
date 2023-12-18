package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/11/1 19:58
 */
public class Main98 {
	long pre = Long.MIN_VALUE;
	public boolean isValidBST2(TreeNode root) {
		if (root == null) {
			return true;
		}

		if (!isValidBST2(root.left)) {
			return false;
		}

		int val = root.val;
		if (val <= pre) {
			return false;
		}

		pre = val;

		return isValidBST2(root.right);
	}

	public boolean dfs(TreeNode node, long left, long right) {
		if (node == null) {
			return true;
		}

		if (node.val > left && node.val < right) {
			return dfs(node.left, left, node.val) && dfs(node.right, node.val, right);
		} else {
			return false;
		}
	}


	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}


		return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}


}
