package algorithm.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 그래프최단거리 {
	static int n, m, answer = 0;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] ch, dis;

	public static void BFS(int v) {
		ch[v] = 1;
		dis[v] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v);
		while (!queue.isEmpty()) {
			int cv = queue.poll();
			for (int nv : graph.get(cv)) {
				if (ch[nv] == 0) {
					ch[nv] = 1;
					queue.offer(nv);
					dis[nv] = dis[cv] + 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		n = 6;
		m = 9;
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
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
}
