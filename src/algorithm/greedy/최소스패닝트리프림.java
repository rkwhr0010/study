package algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 최소스패닝트리프림 {
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
	
	public static void main(String[] args){
		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
		
		Arrays.stream(inputArr)
			.flatMapToInt(data->IntStream.of(data[0],data[1]))
			.distinct()
			.forEach( dummy-> graph.add(new ArrayList<>()) );
		graph.add(new ArrayList<>());
		
		int[] ch=new int[graph.size()];
		
		for(int[] arr : inputArr) {
			graph.get(arr[0]).add(new Edge(arr[1], arr[2]));
			graph.get(arr[1]).add(new Edge(arr[0], arr[2]));
		}
		
		System.out.println(extracted(graph, ch));
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