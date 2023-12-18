package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/28 17:10
 */
public class Main24 {
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode res = head.next;

		ListNode slow = head;
		ListNode fast = slow.next;

		ListNode pre = new ListNode(1);
		while (slow != null && slow.next != null) {
			slow.next = fast.next;
			fast.next = slow;

			pre.next = fast;
			pre = slow;

			slow = slow.next;
			if (slow != null) {
				fast = slow.next;
			}
		}

		return res;
	}
}


