package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;


public class 최소스패닝트리크루스칼연습 {
	private static class Edge implements Comparable<Edge>{
		private final int v1;
		private final int v2;
		private final int cost;
	    Edge(int v1, int v2, int cost) {
	        this.v1 = v1;
			this.v2 = v2;
	        this.cost = cost;
	    }
	    public int compareTo(Edge ob){
	        return this.cost-ob.cost;
	    }
	}
	
	static int[] unf;
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
		ArrayList<Edge> arr=new ArrayList<>();
		
		int n=(int)Arrays.stream(inputArr)
			.flatMapToInt(data-> {
				arr.add(new Edge(data[0], data[1], data[2]));
				return IntStream.of(data[0],data[1]);
			})
			.distinct()
			.count();
		
		unf=new int[n+1];
		int answer=0;
		for(int i=1; i<=n; i++) unf[i]=i;
		
		//최소 비용이 나오는 이유, 정렬
		Collections.sort(arr);
		
		for(Edge ob : arr){
			int fv1=find(ob.v1);
			int fv2=find(ob.v2);
			
			//같으면 이미 그룹, 다르면 그룹을 맺어줌
			if(fv1!=fv2){
				answer+=ob.cost;
				union(ob.v1, ob.v2);
			}
		}
		System.out.println(answer);
	}
	
	public static int Find(int v){
		if(v==unf[v]) return v;
		else return unf[v]=Find(unf[v]);
	}
	public static void Union(int a, int b){
		int fa=Find(a);
		int fb=Find(b);
		if(fa!=fb) unf[fa]=fb;
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa != fb) unf[fa] = fb;
	}
	private static int find(int v) {
		if(v == unf[v]) return unf[v];
		return unf[v] = find(unf[v]);
	}
}
/*
 * 모든 도시를 연결하면서 드는 최소비용을 출려한다.
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