package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author changwenbo
 * @date 2023/9/17 15:30
 */
public class Main56 {


	public static void main(String[] args) {

		int[][] a = {{2, 6}, {1, 3}, {1, 2}, {8, 10}};
		new Main56().merge(a);
	}

	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (x, y) -> {
			if (x[0] == y[0]) {
				return x[1] - y[1];
			}
			return x[0] - y[0];
		});

		List<int[]> list = new ArrayList<>();
		list.add(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			int[] merge = list.get(list.size() - 1);
			if (intervals[i][0] > merge[1]) {
				list.add(intervals[i]);
			} else {
				merge[1] = Math.max(merge[1], intervals[i][1]);
			}

		}


		//System.out.println(JSON.toJSONString(list));

		return list.toArray(new int[][]{});
	}
}
