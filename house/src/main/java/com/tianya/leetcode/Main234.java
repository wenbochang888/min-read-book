package com.tianya.leetcode;

import java.util.Stack;

/**
 * @author changwenbo
 * @date 2023/9/23 17:00
 */
public class Main234 {
	public boolean isPalindrome(ListNode head) {
		Stack<ListNode> stack = new Stack<>();
		ListNode node = head;
		while (node != null) {
			stack.push(node);
			node = node.next;
		}

		int size = stack.size() >> 1;
		int i = 0;

		while (!stack.isEmpty() && ++i < size) {
			ListNode pop = stack.pop();
			if (pop.val != head.val) {
				return false;
			}
			head = head.next;
		}


		return true;
	}

}
