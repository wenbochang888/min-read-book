package com.tianya.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2023/9/10 17:55
 */
public class Main3 {
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		Map<Character, Integer> map = new HashMap<>();
		int left = 0;
		int max = 0;
		for (int right = 0; right < s.length(); right++) {
			char rightChar = s.charAt(right);
			map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
			while (map.get(rightChar) != 1) {
				char leftChar = s.charAt(left);
				map.put(leftChar, map.get(leftChar) - 1);
				left++;
			}

			max = Math.max(max, right - left + 1);
		}


		return max;
	}
}























