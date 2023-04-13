package algorithm.greedy;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최소스패닝트리프림 {
	static class Edge implements Comparable<Edge>{
	    public int vex;
		public int cost;
	    Edge(int vex, int cost) {
	        this.vex = vex;
	        this.cost = cost;
	    }
	    @Override
	    public int compareTo(Edge ob){
	        return this.cost-ob.cost;
	    }
	}
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int n=kb.nextInt();
		int m=kb.nextInt();
		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<=n; i++){
			graph.add(new ArrayList<Edge>());
		}
		int[] ch=new int[n+1];
		for(int i=0; i<m; i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			int c=kb.nextInt();
			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
		}
		
		int answer = extracted(graph, ch);
		System.out.println(answer);
	}
	private static int extracted(ArrayList<ArrayList<Edge>> graph, int[] ch) {
		int answer=0;
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(1, 0));
		while(!pQ.isEmpty()){
			Edge tmp=pQ.poll();
			int ev=tmp.vex;
			if(ch[ev]==0){
				ch[ev]=1;
				answer+=tmp.cost;
				for(Edge ob : graph.get(ev)){
					if(ch[ob.vex]==0) pQ.offer(new Edge(ob.vex, ob.cost));
				}
			}
		}
		return answer;
	}
	
	static int sol(ArrayList<ArrayList<Edge>> graph, int[] ch) {
		int answer=0;
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(1, 0));
		
		while(!pQ.isEmpty()) {
			Edge cur = pQ.poll();
			int curV = cur.vex;
			if(ch[curV] == 0) {
				ch[curV] =1;
				answer += cur.cost;
				
			}
			
		}
		
		return answer;
	}
}
/*
 * 
9 12
1 2 12
1 9 25
2 3 10
2 8 17
2 9 8
3 4 18
3 7 55
4 5 44
5 6 60
5 7 38
7 8 35
8 9 15


196

*/