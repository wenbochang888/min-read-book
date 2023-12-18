package com.tianya.leetcode;

import com.google.common.collect.Lists;

public class Main5 {

	public static void main(String[] args) {
		int[] a = {};
		int s = a.length;
	}

	// 中心扩散法
	// 详见：https://leetcode.cn/problems/longest-palindromic-substring/solutions/63641/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/
	public String longestPalindrome(String s) {
		int start = 0;
		int end = 0;
		int max = 0;

		// 循环遍历整个字符串
		for (int mid = 0; mid < s.length(); mid++) {
			// 向左边扩散
			int left = mid;
			while (left > 0 && s.charAt(left - 1) == s.charAt(mid)) {
				left--;
			}

			// 向右边扩散
			int right = mid;
			while (right < s.length() - 1 && s.charAt(right + 1) == s.charAt(mid)) {
				right++;
			}

			// 中间扩散
			while (left > 0 && right < s.length() - 1) {
				if (s.charAt(left - 1) == s.charAt(right + 1)) {
					left--;
					right++;
				} else {
					break;
				}
			}

			// 记录字符串
			int len = right - left + 1;
			if (len > max) {
				max = len;
				start = left;
				end = right;
			}
		}


		return s.substring(start, end + 1);
	}
}
