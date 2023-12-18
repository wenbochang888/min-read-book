package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/10/31 21:11
 */
public class Main543 {

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		dfs(root);
		return ans;
	}

	int ans = 0;
	public int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = dfs(root.left);
		int right = dfs(root.right);
		ans = Math.max(ans, left + right);

		return Math.max(left, right) + 1;
	}

}
