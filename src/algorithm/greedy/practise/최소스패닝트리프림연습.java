package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 최소스패닝트리프림연습 {
	private static class Edge implements Comparable<Edge>{
		private final int vex;
		private final int cost;
	    Edge(int vex, int cost) {
	        this.vex = vex;
	        this.cost = cost;
	    }
	    public int compareTo(Edge ob){
	        return this.cost-ob.cost;
	    }
	    @Override
	    public String toString() {
	    	return "["+vex+","+cost+"]";
	    }
	}
	
	static int[][] inputArr = 
		{{1 ,2 ,12}
		,{1 ,9 ,25}
		,{2 ,3 ,10}
		,{2 ,8 ,17}
		,{2 ,9 ,8 }
		,{3 ,4 ,18}
		,{3 ,7 ,55}
		,{4 ,5 ,44}
		,{5 ,6 ,60}
		,{5 ,7 ,38}
		,{7 ,8 ,35}
		,{8 ,9 ,15}};
	
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
	static int[] ch;
	static int answer=0;
	public static void main(String[] args){
		
		Arrays.stream(inputArr)
			.flatMapToInt(data->IntStream.of(data[0],data[1]))
			.distinct()
			.forEach( dummy-> graph.add(new ArrayList<>()) );
		graph.add(new ArrayList<>());
		
		ch = new int[graph.size()];
		
		for(int[] arr : inputArr) {
			//다익스트라는 단방향이라 한 줄만
			graph.get(arr[0]).add(new Edge(arr[1], arr[2]));
			//이문제는 양방향으로 한 줄만 더 추가
			graph.get(arr[1]).add(new Edge(arr[0], arr[2]));
		}
		sol(1);
		System.out.println(answer);
	}
	
	private static void extracted(int v) {
		
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
	}
	
	static void sol(int v) {
		PriorityQueue<Edge> Q = new PriorityQueue<>();
		Q.add(new Edge(v, 0));
		
		while(!Q.isEmpty()) {
			Edge c = Q.poll();
			if(ch[c.vex] == 0) {
				ch[c.vex] = 1;
				answer+= c.cost;
				for(Edge n : graph.get(c.vex)) {
					if(ch[n.vex]==0) Q.add(new Edge(n.vex, n.cost));
				}
			}
			
		}
		
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