package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 * https://leetcode.cn/problems/combination-sum-ii/
 *
 * @author changwenbo
 * @date 2023/4/21 14:24
 */
public class Main40 {
	public static void main(String[] args) {
		System.out.println(new Main40().combinationSum2(new int[]{2,2,2}, 4));
	}

	/**
	 输入: candidates = [2,5,2,1,2], target = 5,
	 输出:
	 [
	 [1,2,2],
	 [5]
	 ]
	 */



	List<List<Integer>> res = new ArrayList<>();
	LinkedList<Integer> path = new LinkedList<>();
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		dfs(candidates, target, 0, 0);
		return res;
	}

	public void dfs(int[] a, int target, int sum, int start) {
		if (target == sum) {
			res.add(new ArrayList<>(path));
			return;
		}

		if (sum > target) {
			return;
		}

		for (int i = start; i < a.length; i++) {

			// i > start 详细理解一下
			// https://leetcode.cn/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/225211/
			if (i > start && a[i] == a[i - 1]) {
				continue;
			}

			sum += a[i];
			path.add(a[i]);
			dfs(a, target, sum, i + 1);
			sum -= a[i];
			path.removeLast();
		}
	}

}

























