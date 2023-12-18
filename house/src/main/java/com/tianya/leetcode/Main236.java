package com.tianya.leetcode;

import com.tianya.util.GsonUtils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Main236 {

	public static void main(String[] args) {
		Main236 main = new Main236();

		TreeNode root = new TreeNode(3);
		TreeNode left = root.left = new TreeNode(5);
		TreeNode right = root.right = new TreeNode(1);
		left.left = new TreeNode(6);
		left.right = new TreeNode(2);
		right.left = new TreeNode(0);
		right.right = new TreeNode(8);

		main.dfs(root, null);
		System.out.println(GsonUtils.toJson(main.map));
	}




	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		dfs(root, null);
		Set<TreeNode> pNode = new LinkedHashSet<>();
		TreeNode pp = p;
		while (pp != null) {
			pNode.add(pp);
			pp = map.get(pp);
		}

		Set<TreeNode> qNode = new LinkedHashSet<>();
		TreeNode qq = q;
		while (qq != null) {
			qNode.add(qq);
			qq = map.get(qq);
		}

		for (TreeNode node : pNode) {
			if (qNode.contains(node)) {
				return node;
			}
		}



		return null;
	}

	Map<TreeNode, TreeNode> map = new HashMap<>();
	public void dfs(TreeNode root, TreeNode parentNode) {
		if (root == null) {
			return;
		}

		map.put(root, parentNode);
		dfs(root.left, root);
		dfs(root.right, root);
	}
}















