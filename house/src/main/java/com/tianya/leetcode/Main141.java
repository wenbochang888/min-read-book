package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/24 15:10
 */
public class Main141 {
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head;
		ListNode fast = slow.next;
		while (true) {
			if (slow == fast) {
				return true;
			}

			if (fast == null || fast.next == null) {
				return false;
			}

			slow = slow.next;
			fast = fast.next.next;


		}
	}
}
