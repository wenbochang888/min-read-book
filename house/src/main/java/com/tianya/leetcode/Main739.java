package com.tianya.leetcode;

import javafx.util.Pair;

import java.util.Stack;

public class Main739 {
	public int[] dailyTemperatures(int[] temperatures) {
		Stack<Pair<Integer, Integer>> stack = new Stack<>();
		int[] ans = new int[temperatures.length];


		for (int i = 0; i < temperatures.length; i++) {

			while (!stack.isEmpty() && temperatures[i] > stack.peek().getValue()) {
				Pair<Integer, Integer> pair = stack.pop();
				int index = pair.getKey();
				ans[index] = i - index;
			}

			stack.push(new Pair<>(i, temperatures[i]));

		}

		return ans;
	}
}
