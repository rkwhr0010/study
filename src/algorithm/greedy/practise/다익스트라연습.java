package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 다익스트라연습 {
	
	private static class Edge implements Comparable<Edge>{
	    public int vex;
		public int cost;
		
		private Edge(int vex, int cost) {
	        this.vex = vex;
	        this.cost = cost;
	    }
	    @Override
	    public int compareTo(Edge ob){
	        return this.cost-ob.cost;
	    }
	    @Override
	    public String toString() {
	    	return "["+vex+", "+cost+"]";
	    }
	}
	
	static ArrayList<ArrayList<Edge>> graph;
	static int[] dis;
	public static void solution(int v){
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(v, 0));
		dis[v]=0;
		while(!pQ.isEmpty()){
			Edge tmp=pQ.poll();
			int now=tmp.vex;
			int nowCost=tmp.cost;
			if(nowCost>dis[now]) continue;
			for(Edge ob : graph.get(now)){
				if(dis[ob.vex]>nowCost+ob.cost){
					dis[ob.vex]=nowCost+ob.cost;
					pQ.offer(new Edge(ob.vex, nowCost+ob.cost));
				}
			}
		}
	}
	
	static void sol (int v) {
		PriorityQueue<Edge> Q = new PriorityQueue<>();
		dis[v] = 0;
		Q.add(new Edge(v, 0));
		
		while(!Q.isEmpty()) {
			Edge cur = Q.poll();
			
			if(dis[cur.vex] < cur.cost ) continue;
			for(Edge next : graph.get(cur.vex)) {
				if(dis[next.vex] > cur.cost + next.cost) {
					dis[next.vex] = cur.cost + next.cost;
					Q.add(new Edge(next.vex , cur.cost + next.cost));
				}
			}
		}
	}
	
	static void sol2(int v) {
		//진행 상황을 저장할 큐
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		dis[v] = 0;
		pQ.add(new Edge(v, 0));
		while(!pQ.isEmpty()) {
			Edge cur = pQ.poll();
			if(dis[cur.vex]<cur.cost) continue;
			//여기서 나온 Edge는 입력된 Edge 
			//그 정점에서 다음 경로들
			for(Edge next : graph.get(cur.vex)) {
				//다음 경로에 저장된 cost > 지금까지 cost합 + 현재위치에서 다음까지 cost
				if(dis[next.vex] > cur.cost + next.cost ) {
					dis[next.vex] = cur.cost + next.cost;
					pQ.add(new Edge(next.vex, cur.cost + next.cost));
				}
			}
		}
	}

	static void sol3(int v) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dis[v] = 0;
		pq.offer(new Edge(v, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for(Edge next : graph.get(cur.vex)) {
				if(dis[next.vex] > cur.cost + next.cost) {
					dis[next.vex] = cur.cost + next.cost;
					pq.add(new Edge(next.vex, cur.cost + next.cost));
				}
			}
		}
	}
	
	public static void main(String[] args){
		int n=6; // 정점 수
		int m=9; // 경로 입력 수
		graph = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<=n; i++){
			graph.add(new ArrayList<Edge>());
		}
		dis=new int[n+1];
		
		int[][] tmp = 
			{	 {1, 2, 12}
				,{1, 3, 4 }
				,{2, 1, 2 }
				,{2, 3, 5 }
				,{2, 5, 5 }
				,{3, 4, 5 }
				,{4, 2, 2 }
				,{4, 5, 5 }
				,{6, 4, 5 }	};
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		for(int i=0; i<m; i++){
			int a= tmp[i][0];
			int b= tmp[i][1];
			int c= tmp[i][2];
			graph.get(a).add(new Edge(b, c));
		}
		
//		T.solution(1);
		sol2(1);
		System.out.println(graph);
		for(int i=2; i<=n; i++){
			if(dis[i]!=Integer.MAX_VALUE) System.out.println(i+" : "+dis[i]);
			else System.out.println(i+" : impossible");
		}
	}
}
/*
2 : 11
3 : 4
4 : 9
5 : 14
6 : impossible
*/