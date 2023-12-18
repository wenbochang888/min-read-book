package com.tianya.leetcode.leetocde_360;

/**
 * @author changwenbo
 * @date 2023/9/2 17:52
 */
public class Main1 {
	public int furthestDistanceFromOrigin(String moves) {
		int n = 0;
		int L = 0;
		int R = 0;
		for (char ch : moves.toCharArray()) {
			if (ch == '_') {
				n++;
			}
			if (ch == 'L') {
				L++;
			}
			if (ch == 'R') {
				R++;
			}
		}

		return n + Math.abs(L - R);
	}
}
