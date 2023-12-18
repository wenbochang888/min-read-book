package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/24 15:10
 */
public class Main142 {
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}

		ListNode slow = head;
		ListNode fast = slow.next;

		ListNode node = null;
		while (true) {
			if (slow == fast) {
				node = slow.next;
				break;
			}

			if (fast == null || fast.next == null) {
				return null;
			}

			slow = slow.next;
			fast = fast.next.next;
		}

		while (head != node) {
			head = head.next;
			node = node.next;
		}

		return node;
	}
}
