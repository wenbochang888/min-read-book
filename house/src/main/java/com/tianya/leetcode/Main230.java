package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/11/1 20:24
 */
public class Main230 {
	int cur;
	int res;
	public int kthSmallest(TreeNode root, int k) {
		cur = k;
		dfs(root);
		return res;
	}

	public void dfs(TreeNode node) {
		if (node == null) {
			return;
		}

		dfs(node.left);

		if (--cur == 0) {
			res = node.val;
			return;
		}

		dfs(node.right);
	}
}
