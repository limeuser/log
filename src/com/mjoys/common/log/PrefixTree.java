package com.mjoys.common.log;

import java.util.LinkedList;
import java.util.List;

public class PrefixTree<K, V> {
	public class Node {
		List<Node> children;
		K key;
		V value;
	}
	
	private Node root;
	
	public PrefixTree(K rootKey, V rootValue) {
		this.root = new Node();
		this.root.key = rootKey;
		this.root.value = rootValue;
		this.root.children = new LinkedList<Node>();
	}
	
	public V get(K[] keys) {
		Node node = this.root;
		List<Node> nodes = this.root.children;
		
		int i = 0;
		for (Node child : nodes) {
			if (child.key.equals(keys[i])) {
				i++;
				node = child;
				nodes = child.children;
			}
		}
		
		return node.value;
	}
	
	public void insert(K[] keys, V value) {
		List<Node> nodes = this.root.children;
		
		int i = 0;
		for (Node child : nodes) {
			if (child.key.equals(keys[i])) {
				i++;
				nodes = child.children;
			}
		}
		
		for (; i < keys.length; i++) {
			Node inserting = new Node();
			inserting.children = new LinkedList<Node>();
			inserting.key = keys[i];
			inserting.value = value;
			
			nodes.add(inserting);
			nodes = inserting.children;
		}
	}
}
