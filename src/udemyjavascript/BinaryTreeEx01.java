package udemyjavascript;

import udemyjavascript.BinaryTreeEx01.BinaryTree;

public class BinaryTreeEx01 {
	static class Node<T extends Comparable<T>>{
		T val;
		Node<T> left;
		Node<T> right;
		
		public Node(T val) {
			super();
			this.val = val;
		}
	}
	
	static class BinaryTree<T extends Comparable<T>>{
		Node<T> root;
		
		BinaryTree<T> add(T val) {
			Node<T> node = new Node<>(val);
			if(root == null) {
				root = node;
			} else {
				add(root, node);
			}
			return this;
		}
		
		BinaryTree<T> add(Node<T> pare, Node<T> node) {
			if(pare.val.compareTo(node.val) > 0) {
				if(pare.left == null) {
					pare.left = node;
				} else {
					add(pare.left, node );
				}
			} else {
				if(pare.right == null) {
					pare.right = node;
				} else {
					add(pare.left, node );
				}
			}
			return this;
		}
		
	}
	
	
	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(1);
		binaryTree.add(2);
//		binaryTree.add(3);
//		binaryTree.add(4);
//		binaryTree.add(5);
	}
}
