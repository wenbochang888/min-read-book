package com.tianya.leetcode;

public class Main2 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		int flag = 0;
		while (l1 != null || l2 != null) {
			int num = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + flag;
			if (num >= 10) {
				flag = 1;
			} else {
				flag = 0;
			}
			head.next = new ListNode(num % 10);
			head = head.next;

			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}

		if (flag == 1) {
			head.next = new ListNode(1);
		}

		return dummy.next;
	}
}
