package com.tianya;

import com.tianya.leetcode.ListNode;

/**
 * @author changwenbo
 * @date 2022/6/25 16:58
 */
public class Main {

	public static void main(String[] args) throws Exception {

		ListNode head = new ListNode(1);
		ListNode cur = head;
		for (int i = 2; i <= 8; i++) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}

		new Main().reverseKGroup(head, 3);

	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}

		ListNode lenNode = head;
		int len = 0;
		while (lenNode != null) {
			len++;
			lenNode = lenNode.next;
		}

		ListNode dummy = new ListNode(-1);
		ListNode p0 = dummy;

		for (int i = 0; i < len / k; i++) {
			ListNode pre = p0;
			ListNode cur = pre.next;
			ListNode next = cur.next;

			for (int j = 0; j < k; j++) {
				cur.next = pre;
				pre = cur;
				cur = next;
				if (next != null) {
					next = next.next;
				}
			}
			ListNode p0Next = p0.next;
			p0.next = pre;
			p0Next.next = cur;
			p0 = p0Next;
		}

		return dummy.next;
	}

}
