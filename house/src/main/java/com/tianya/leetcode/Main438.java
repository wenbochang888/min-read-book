package com.tianya.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2023/9/10 18:14
 */
public class Main438 {

	public static void main(String[] args) {
		String s = "cbaebabacd";
		String p = "abc";
		System.out.println(JSON.toJSONString(new Main438().findAnagrams(s, p)));
	}


	public List<Integer> findAnagrams(String s, String p) {

		if (p.length() > s.length()) {
			return new ArrayList<>();
		}

		Map<Character, Integer> map = new HashMap<>();
		for (char ch : p.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		int left = 0;
		int right = 0;
		Map<Character, Integer> mathchMap = new HashMap<>();
		List<Integer> ans = new ArrayList<>();
		boolean checkSame = false;
		while (right < s.length()) {
			if ((right - left + 1) == p.length()) {
				if (checkSame) {
					if (s.charAt(left) == s.charAt(right)) {
						left++;
						right++;
						ans.add(left);
					} else {
						left++;
						checkSame = false;
					}
					continue;
				}

				if (checkSameMap(map, mathchMap)) {
					ans.add(left);
					left++;
					right++;
					checkSame = true;
					continue;
				} else {
					left++;
					checkSame = false;
					continue;
				}
			}

			if (!map.containsKey(s.charAt(right))) {
				left = right;
				left++;
				right++;
				continue;
			}

			right++;

		}


		return ans;
	}

	private boolean checkSameMap(Map<Character, Integer> map, Map<Character, Integer> mathchMap) {
		for (Map.Entry<Character, Integer> mp : map.entrySet()) {
			Character key = mp.getKey();
			Integer value = mp.getValue();

			if (!mathchMap.containsKey(key)) {
				return false;
			}

			if (!mathchMap.get(key).equals(value)) {
				return false;
			}

		}



		return true;
	}
}
















