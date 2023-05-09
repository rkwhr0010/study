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
	static ArrayList<Edge> arr=new ArrayList<>();
	
	public static void main(String[] args){
		//마지막 정검 구하기
		int n=(int)Arrays.stream(inputArr)
			.flatMapToInt(data-> {
				arr.add(new Edge(data[0], data[1], data[2]));
				return IntStream.of(data[0],data[1]);
			})
			.distinct()
			.count();
		
		unf=new int[n+1];
		
		System.out.println(sol1(n));
	}
	
	static int sol3(int n) {
		int answer = 0;
		//정점비용 초기화
		for(int i=1;i<=n; i++) unf[i] = i;
		//정렬
		Collections.sort(arr);
		for(Edge ob :arr) {
			//현재 가장 최소비용 정점에 간선이 서로 다른 그룹이냐
			if(find(ob.v1) != find(ob.v2)) {
				//서로 다르니 그룹을 맺어주며, 값을 누산한다.
				answer += ob.cost;
				uni(ob.v1, ob.v2);
			}
		}
		return answer;
	}
	
	
	private static void sol(int n) {
		int answer=0;
		for(int i=1; i<=n; i++) unf[i]=i;
		//최소 비용이 나오는 이유, 정렬
		Collections.sort(arr);
		for(Edge ob : arr){
			//같으면 이미 그룹, 다르면 그룹을 맺어줌
			if(find(ob.v1)!=find(ob.v2)){
				answer+=ob.cost;
				uni(ob.v1, ob.v2);
			}
		}
		System.out.println(answer);
	}
	
	static int sol1(int n) {
		int ans = 0;
		for(int i=1; i<=n; i++) unf[i]=i;
		Collections.sort(arr);
		for(Edge e : arr) {
			if(find(e.v1) != find(e.v2) ) {
				uni(e.v1, e.v2);
				ans += e.cost;
			}
		}
		return ans;
	}
	
	
	
	static int find(int v) {
		if(v==unf[v])return unf[v];
		else return unf[v] = find(unf[v]);
	}
	static void uni(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa!=fb)unf[fa]=fb;
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