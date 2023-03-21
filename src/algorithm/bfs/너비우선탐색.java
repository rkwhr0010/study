package algorithm.bfs;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class 너비우선탐색 {
	static class Node{
		public static Node lt,rt;
		public static int value;
	}
	public static void main(String[] args) {
		
		
		Node.value = 1;
		Node.lt.value = 2;
		Node.rt.value = 3;
		Node.lt.lt.value = 4;
		Node.lt.rt.value = 5;
		Node.rt.lt.value = 6;
		Node.rt.rt.value = 7;
		
	}
	
	static void BFS(Node node) {
		Queue<Node> q = new ConcurrentLinkedQueue<>();
		q.add(node);
		
		while(!q.isEmpty()) {
			System.out.print(node.value+ " : ");
			int len = q.size();
			for (int i = 0; i < len; i++) {
				Node no = q.poll();
				System.out.println(no.value+" ");
				if(no.lt != null) q.offer(no.lt);
				if(no.rt != null) q.offer(no.rt);
			}
			System.err.println();
		}
		
		
	}
}
