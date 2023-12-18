package com.tianya.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2023/10/22 14:26
 */
public class Main146 {
	public static void main(String[] args) {

		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);

		cache.get(1);

		cache.put(3, 3);


		cache.put(1, 10);
	}

}

class Nodee {
	int key;
	int val;
	Nodee pre;
	Nodee next;

	public Nodee() {
		this.key = -1;
		this.val = -1;
	}
	public Nodee(int key, int val) {
		this.key = key;
		this.val = val;
	}
}

class LRUCache {
	int size = 0;
	Nodee head = new Nodee();
	Nodee tail = new Nodee();
	Map<Integer, Nodee> map = new HashMap<>();
	public LRUCache(int capacity) {
		size = capacity;
		head.next = tail;
		tail.pre = head;
	}

	public int get(int key) {
		Nodee node = map.get(key);
		if (node == null) {
			return -1;
		}
		deleteNode(node);
		insertTail(node.key, node.val);
		return node.val;
	}

	public void put(int key, int val) {
		Nodee node = map.get(key);
		Nodee newNode = insertTail(key, val);
		map.put(key, newNode);
		if (node != null) {
			deleteNode(node);
		}

		if (map.size() > size) {
			Nodee deleteNode = head.next;
			deleteNode(deleteNode);
			map.remove(deleteNode.key);
		}
	}

	private void deleteNode(Nodee node) {
		Nodee first = node.pre;
		Nodee second = node.next;
		first.next = second;
		second.pre = first;
	}

	private Nodee insertTail(int key, int val) {
		Nodee first = tail.pre;
		Nodee second = tail;
		Nodee node = new Nodee(key, val);
		first.next = node;
		node.next = second;
		second.pre = node;
		node.pre = first;
		return node;
	}
}
