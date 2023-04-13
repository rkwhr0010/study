package algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class 최소스패닝트리크루스칼 {
	static class Edge implements Comparable<Edge>{
	    public int v1;
		public int v2;
		public int cost;
	    Edge(int v1, int v2, int cost) {
	        this.v1 = v1;
			this.v2 = v2;
	        this.cost = cost;
	    }
	    @Override
	    public int compareTo(Edge ob){
	        return this.cost-ob.cost;
	    }
	}
	static int[] unf;
	public static int Find(int v){
		if(v==unf[v]) return v;
		else return unf[v]=Find(unf[v]);
	}
	static int find(int v) {
		if(v == unf[v]) return unf[v];
		else return unf[v] = find(unf[v]);
	}
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa != fb) unf[fa] = fb;
	}
	
	public static void Union(int a, int b){
		int fa=Find(a);
		int fb=Find(b);
		if(fa!=fb) unf[fa]=fb;
	}
	public static void main(String[] args){
		ArrayList<Edge> arr=new ArrayList<>();
		
		int[][] inputArr = 
			{{} //0쓰기 싫음
			,{1 ,2 ,12}
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
		
		Arrays.stream(inputArr)
			.flatMapToInt(data-> {
				arr.add(new Edge(data[0], data[1], data[2]));
				return IntStream.of(data[0],data[1]);
			})
			.distinct()
			.count();
		
		long n=Arrays.stream(inputArr)
				.flatMapToInt(data-> IntStream.of(data[0],data[1]))
				.distinct()
				.count()+1;
		
		unf=new int[(int)n];
		
		for(int i=1; i<=n; i++) unf[i]=i;
		int answer=0;
		//최소 비용이 나오는 이유, 정렬
		Collections.sort(arr);
		for(Edge ob : arr){
			int fv1=find(ob.v1);
			int fv2=find(ob.v2);
			if(fv1!=fv2){
				answer+=ob.cost;
				union(ob.v1, ob.v2);
			}
		}
		System.out.println(answer);
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