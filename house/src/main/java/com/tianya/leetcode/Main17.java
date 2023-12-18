package com.tianya.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 17. 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * 
 * @author changwenbo
 * @date 2023/4/21 14:24
 */
public class Main17 {
	public static void main(String[] args) {
		System.out.println(new Main17().letterCombinations("237"));
	}

	/**
	 * 输入：digits = "23"
	 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
	 */
	static Map<String, Character[]> map = new HashMap<>();

	static {
		map.put("2",  new Character[]{'a', 'b', 'c'});
		map.put("3",  new Character[]{'d', 'e', 'f'});
		map.put("4",  new Character[]{'g', 'h', 'i'});
		map.put("5",  new Character[]{'j', 'k', 'l'});
		map.put("6",  new Character[]{'m', 'n', 'o'});
		map.put("7",  new Character[]{'p', 'q', 'r', 's'});
		map.put("8",  new Character[]{'t', 'u', 'v'});
		map.put("9",  new Character[]{'w', 'x', 'y', 'z'});
	}

	List<String> res = new ArrayList<>();
	LinkedList<Character> path = new LinkedList<>();
	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			return res;
		}
		dfs(digits, 0);
		return res;
	}

	public void dfs(String digits, int start) {
		if (path.size() == digits.length()) {
			res.add(path.stream().map((x) -> x + "").collect(Collectors.joining()));
			return;
		}

		String ch = digits.charAt(start) + "";
		Character[] characters = map.get(ch);
		for (int i = 0; i < characters.length; i++) {
			path.add(characters[i]);
			dfs(digits, start + 1);
			path.removeLast();
		}
	}

}
















