package algorithm.bfs.pratise;

import java.util.ArrayList;

public class 그래프최단 {
	static int n = 0;
	static int m = 0;
	static int answer = 0;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] ch ;
	static int[] dis ;
	
	public static void main(String[] args) {
		n = 6;
		m = 9;
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		ch = new int[n + 1];
		dis = new int[n + 1];
		graph.get(1).add(3);
		graph.get(1).add(4);
		graph.get(2).add(1);
		graph.get(2).add(5);
		graph.get(3).add(4);
		graph.get(4).add(5);
		graph.get(4).add(6);
		graph.get(6).add(2);
		graph.get(6).add(5);
		BFS(1);
		for (int i = 2; i <= n; i++) {
			System.out.println(i + " : " + dis[i]);
		}
	}

	private static void BFS(int i) {
		ch[i] = 1;
		dis[i] = 0;
		
		
	}
	
	
	
}
