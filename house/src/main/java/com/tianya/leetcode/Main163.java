package com.tianya.leetcode;

/**
 * @author changwenbo
 * @date 2023/9/23 16:33
 */
public class Main163 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		ListNode A = headA;
		ListNode B = headB;

		while (headA != null || headB != null) {
			if (headA == headB) {
				return headA;
			}

			if (headA == null) {
				headA = B;
				headB = headB.next;
				continue;
			}

			if (headB == null) {
				headB = A;
				headA = headA.next;
				continue;
			}

			headA = headA.next;
			headB = headB.next;
		}

		return null;
	}
}
