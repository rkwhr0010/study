package udemyjavascript;

import java.util.LinkedList;

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
		@Override
		public String toString() {
			return val.toString();
		}
	}
	
	static class BinaryTree<T extends Comparable<T>>{
		Node<T> root;
		
		BinaryTree<T> add(T val) {
			Node<T> node = new Node<>(val);
			if(root == null) {
				root = node;
			} else {
				Node<T> parents = root;
				while(true) {
					if(parents.val.compareTo(node.val) > 0) {
						if(parents.left == null) {
							parents.left = node;
							break;
						} else {
							parents = parents.left;
						}
					} else {
						if(parents.right == null) {
							parents.right = node;
							break;
						} else {
							parents = parents.right;
						}
					}
				}
//				add(root, node);
			}
			return this;
		}
		
		BinaryTree<T> add(Node<T> parents, Node<T> node) {
			//부모가 더 크다.
			if(parents.val.compareTo(node.val) > 0) {
				if(parents.left == null) {
					parents.left = node;
				} else {
					add(parents.left, node );
				}
			//부모가 더 작다.
			} else {
				if(parents.right == null) {
					parents.right = node;
				} else {
					add(parents.right, node );
				}
			}
			return this;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			//level 별 출력을 위한 큐
			LinkedList<Node<T>> q = new LinkedList<>();
			q.offer(root);
			
			while(!q.isEmpty()) {
				sb.append(q).append("\n");
				int len = q.size();
				for(int i = 0; i< len; i++) {
					Node<T> cur = q.poll();
					if(cur.left != null) q.add(cur.left);
					if(cur.right != null) q.add(cur.right);
				}
			}
			return sb.toString();
		}
		
	}
	
	
	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.add(10);
		binaryTree.add(5);
		binaryTree.add(2);
		binaryTree.add(7);
		binaryTree.add(13);
		binaryTree.add(11);
		binaryTree.add(16);
		
		System.out.println(binaryTree);
		
	}
}
