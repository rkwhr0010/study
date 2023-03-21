package algorithm.bfs;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class 너비우선탐색 {
	static class Node{
		public Node lt,rt;
		public int value;
		public Node(int value) {
			super();
			this.value = value;
		}
		
	}
	public static void main(String[] args) {
		Node node = new Node(1);
		node.value = 1;
		node.lt= new Node(2);
		node.rt= new Node(3);
		node.lt.lt= new Node(4);
		node.lt.rt= new Node(5);
		node.rt.lt= new Node(6);
		node.rt.lt= new Node(7);
		BFS(node);
	}
	
	static void BFS(Node node) {
		Queue<Node> q = new ConcurrentLinkedQueue<>();
		q.add(node);
		int lv = 1;
		while(!q.isEmpty()) {
			System.out.print(lv+++ " : ");
			int len = q.size();
			for (int i = 0; i < len; i++) {
				Node no = q.poll();
				System.out.print(no.value+" ");
				if(no.lt != null) q.offer(no.lt);
				if(no.rt != null) q.offer(no.rt);
			}
			System.out.println();
		}
		
		
	}
}
