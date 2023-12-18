package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/24 15:10
 */
public class Main143 {
	public void reorderList(ListNode head) {
		if (head.next == null) {
			return;
		}

		ListNode midNode = getMidNode(head);
		ListNode head2 = reverse(midNode);
		ListNode head1 = head;
		ListNode cur = head1;

		boolean flag = true;
		while (cur.next != null) {
			if (flag) {
				head1 = head1.next;
				cur.next = head2;
				cur = head2;
				flag = false;
			} else {
				head2 = head2.next;
				cur.next = head1;
				cur = head1;
				flag = true;
			}

		}

	}

	private ListNode reverse(ListNode midNode) {
		ListNode pre = null;
		ListNode cur = midNode;
		ListNode next = cur.next;

		while (cur != null) {
			cur.next = pre;
			pre = cur;
			cur = next;
			if (next != null) {
				next = next.next;
			}
		}

		return pre;
	}

	private ListNode getMidNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = slow.next;
		while (true) {
			if (fast == null) {
				return slow;
			}
			slow = slow.next;
			if (fast.next != null) {
				fast = fast.next.next;
			} else {
				fast = null;
			}

		}
	}
}
