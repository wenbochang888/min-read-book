package com.tianya.leetcode;

public class Main19 {
	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode dummy = new ListNode(-1);
		dummy.next = head;

		ListNode fast = dummy;
		ListNode slow = dummy;

		while (n-- != 0) {
			fast = fast.next;
		}

		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		slow.next = slow.next.next;




		return dummy.next;
	}
}
