package algorithm.dfs.pratise;

import java.util.ArrayList;

public class 경탐인 {
	static int n, m, answer=0;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] ch;
	public static void main(String[] args) {
		n = 5;
		m = 9;
		ch = new int[n+1];
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		graph.get(1).add(2);
		graph.get(1).add(3);
		graph.get(1).add(4);
		graph.get(2).add(1);
		graph.get(2).add(3);
		graph.get(2).add(5);
		graph.get(3).add(4);
		graph.get(4).add(2);
		graph.get(4).add(5);
		ch[1]=1;
		DFS(1);
		System.out.println(answer);
	}
	private static void DFS(int v) {
		if(v == n) answer++;
		else {
			for(int nv : graph.get(v)) {
				if(ch[nv]==0) {
					ch[nv]=1;
					DFS(nv);
					ch[nv]=0;
				}
			}
		}
		
	}
	
	
	
}
