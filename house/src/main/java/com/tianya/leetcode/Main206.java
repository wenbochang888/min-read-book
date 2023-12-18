package com.tianya.leetcode;

import com.tianya.util.GsonUtils;

/**
 * @author changwenbo
 * @date 2023/9/2 17:31
 */
public class Main206 {

	public static void main(String[] args) {
		ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		Main206 main206 = new Main206();
		ListNode listNode1 = main206.reverseList(listNode);
		System.out.println(GsonUtils.toJson(listNode1));
	}

	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = null;
		ListNode cur = head;
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

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode node = reverseList(head.next);
		ListNode next = head.next;
		next.next = head;
		head.next = null;

		return node;
	}
}























