package com.tianya.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author changwenbo
 * @date 2023/10/24 20:55
 */
public class Main104 {
	public int maxDepthCur(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepthCur(root.left), maxDepthCur(root.right)) + 1;
	}

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int ans = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode poll = queue.poll();
				if (poll.left != null) {
					queue.add(poll.left);
				}
				if (poll.right != null) {
					queue.add(poll.right);
				}
			}
			ans++;
		}



		return ans;
	}
}
