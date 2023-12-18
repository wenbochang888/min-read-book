package com.tianya.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author changwenbo
 * @date 2023/10/25 21:12
 */
public class Main226 {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);

		new Main226().invertTree1(root);
	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}

				TreeNode temp = node.left;
				node.left = node.right;
				node.right = temp;
			}
		}

		return root;
	}

	public TreeNode invertTree1(TreeNode root) {
		if (root == null) {
			return root;
		}

		TreeNode node = root.left;
		root.left = root.right;
		root.right = node;
		invertTree1(root.right);
		invertTree1(root.left);

		return root;
	}
}
