package algorithm.dfs.pratise;

import java.util.ArrayList;
import java.util.Arrays;

public class 경로인접연습 {
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
	static void DFS2(int i) {
		if(i==n) answer ++ ;
		else {
			for(int nv : graph.get(i)) {
				if(ch[nv]==0) {
					ch[nv]=1;
					DFS2(nv);
					ch[nv] =0;
				}
			}
		}
	}
	

	private static void DFS(int i) {
		if(i==n) {
			answer++;
		}
		else {
			for(int nv : graph.get(i)) {
				if(ch[nv]==0) {
					ch[nv] = 1;
					DFS(nv);
					ch[nv] = 0;
					
				}
			}
			
		}
		
		
	}
}
