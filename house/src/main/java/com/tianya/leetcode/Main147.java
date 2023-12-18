package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/10/22 16:23
 */
public class Main147 {
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode last = head;
		ListNode cur = head.next;

		while (cur != null) {
			while (cur != null && cur.val >= last.val) {
				last = cur;
				cur = cur.next;
			}
			if (cur == null) {
				break;
			}

			ListNode pre = dummy;
			while (pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
			}

			last.next = cur.next;
			ListNode next = pre.next;
			pre.next = cur;
			cur.next = next;
			cur = last.next;

		}


		return dummy.next;
	}
}
