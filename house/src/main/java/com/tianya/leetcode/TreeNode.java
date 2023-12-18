package com.tianya.leetcode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TreeNode {
	public int val;
    public TreeNode left;
    public TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}


    public static void main(String[] args) {
        TreeNode head = init();
        orderTraversalCur(head);
        System.out.println("====================");
        orderTraversal(head);
    }

    public static TreeNode init() {
        TreeNode head = new TreeNode(1);
        TreeNode left = head.left = new TreeNode(2);
        left.right = new TreeNode(4);

        TreeNode right = head.right = new TreeNode(3);
        right.left = new TreeNode(5);
        return head;
    }

    public static void preTraversalCur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        preTraversalCur(head.left);
        preTraversalCur(head.right);
    }

    public static void preTraversal(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

    }

    public static void afterTraversalCur(TreeNode head) {
        if (head == null) {
            return;
        }
        afterTraversalCur(head.left);
        afterTraversalCur(head.right);
        System.out.println(head.val);
    }

    public static void afterTraversal(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);

        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(list);
        System.out.println(list);
    }


    public static void orderTraversalCur(TreeNode head) {
        if (head == null) {
            return;
        }
        orderTraversalCur(head.left);
        System.out.println(head.val);
        orderTraversalCur(head.right);
    }

    public static void orderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }

        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {

            if (head != null) {
                stack.push(head);
                head = head.left;
            } else if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                head = node.right;
            }
        }
    }

}















