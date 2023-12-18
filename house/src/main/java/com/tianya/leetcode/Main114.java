package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Main114 {

	public void flatten1(TreeNode root) {

		dfs(root);

		for (int i = 1; i < list.size(); i++) {
			list.get(i - 1).right = list.get(i);
			list.get(i - 1).left = null;
		}

	}

	public void flatten(TreeNode cur) {
		TreeNode root = cur;
		if (root == null) {
			return;
		}

		while (root != null) {
			if (root.left != null) {
				TreeNode right = findRight(root.left);
				right.right = root.right;
				root.right = root.left;
				root.left = null;
			}
			root = root.right;
		}

	}

	private TreeNode findRight(TreeNode left) {
		TreeNode node = left;
		while (node.right != null) {
			node = node.right;
		}

		return node;
	}

	List<TreeNode> list = new ArrayList<>();
	public void dfs(TreeNode root) {
		if (root == null) {
			return;
		}

		list.add(root);
		dfs(root.left);
		dfs(root.right);
	}



}
