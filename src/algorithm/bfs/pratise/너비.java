package algorithm.bfs.pratise;

import java.util.LinkedList;
import java.util.Queue;

public class 너비 {
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
	@SuppressWarnings("unused")
	private static void BFS(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.offer(node);
		while(!q.isEmpty()) {
			for (int i = 0,lv = 1,len = q.size(); i < len; i++) {
				Node cur = q.poll();
//				System.out.print(lv+++ " : ");
				System.out.print(cur.value+" ");
				if(cur.lt!=null) q.offer(cur.lt);
				if(cur.rt!=null) q.offer(cur.rt);
			}
			System.out.println();
		}
	}
}
