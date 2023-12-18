package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 216. 组合总和 III
 * https://leetcode.cn/problems/combination-sum-iii/
 * 
 * @author changwenbo
 * @date 2023/4/21 14:24
 */
public class Main216 {
	public static void main(String[] args) {
		System.out.println(new Main216().combinationSum3(3, 9));
	}

	List<List<Integer>> res = new ArrayList<>();
	LinkedList<Integer> path = new LinkedList<>();
	public List<List<Integer>> combinationSum3(int k, int n) {
		dfs(1, k, n, 0);
		return res;
	}

	public void dfs(int start, int k, int n, int sum) {
		if (path.size() == k && n == sum) {
			res.add(new ArrayList<>(path));
			return;
		}

		if (path.size() >= k || sum >= n) {
			return;
		}

		for (int i = start; i <= 9; i++) {
			if (sum >= n || path.size() >= k) {
				break;
			}

			sum += i;
			path.add(i);
			dfs(i + 1, k, n, sum);
			sum -= i;
			path.removeLast();
		}
	}
}
















