package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * https://leetcode.cn/problems/combinations/
 *
 * @author changwenbo
 * @date 2023/4/21 14:24
 */
public class Main77 {
	public static void main(String[] args) {
		System.out.println(new Main77().combine(4, 2));
	}

	List<List<Integer>> res = new ArrayList<>();
	LinkedList<Integer> linkedList = new LinkedList<>();
	public List<List<Integer>> combine(int n, int k) {

		dfs(1, n, k);

		return res;
	}

	public void dfs(int start, int n, int k) {
		if (linkedList.size() == k) {
			res.add(new ArrayList<>(linkedList));
			return;
		}

		for (int i = start; i <= n; i++) {
			// 剪枝
			if ((n - start + 1 + linkedList.size()) < k) {
				continue;
			}

			linkedList.add(i);
			dfs(i + 1, n, k);
			linkedList.removeLast();
		}
	}
}
