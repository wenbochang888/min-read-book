package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * https://leetcode.cn/problems/combination-sum/
 *
 * @author changwenbo
 * @date 2023/4/21 14:24
 */
public class Main39 {
	public static void main(String[] args) {
		System.out.println(new Main39().combinationSum(new int[]{2, 3, 6, 7}, 7));
	}

	/**
	 * 输入：candidates = [2,3,6,7], target = 7
	 * 输出：[[2,2,3],[7]]
	 */



	List<List<Integer>> res = new ArrayList<>();
	LinkedList<Integer> path = new LinkedList<>();
	public List<List<Integer>> combinationSum(int[] candidates, int target) {

		dfs(candidates, target, 0, 0);

		return res;
	}

	public void dfs(int[] a, int target, int sum, int start) {
		if (sum == target) {
			res.add(new ArrayList<>(path));
			return;
		}

		if (sum > target) {
			return;
		}

		for (int i = start; i < a.length; i++) {
			sum += a[i];
			path.add(a[i]);
			dfs(a, target, sum, i);
			sum -= a[i];
			path.removeLast();
		}
	}

}

























