package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * 최소스패닝트리
 * 그래프에서 트리를 만드는 법
 * 트리는 회로가 존재하지 않음
 * 그래프는 회로가 존재함
 */
public class 최소스패닝트리크루스칼연습 {
	private static class Edge implements Comparable<Edge>{
		private final int v1;//간선 정보
		private final int v2;//간선 정보
		private final int cost;//간선 비용
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
		ThreadLocalRandom random = ThreadLocalRandom.current();
//		int[] array = IntStream.rangeClosed(0, 500).toArray();
		
		IntStream.rangeClosed(0, 500)
			.forEach((index)->{
				int v1 = random.nextInt(1, 501);
				int v2 = random.nextInt(1, 501);
				while(v1==v2) {
					v2 = random.nextInt(1, 501);
				}
				int v = random.nextInt(1, 501);
				arr.add(new Edge(v1, v2, v));
			});
		
		//마지막 정검 구하기
//		int n=(int)Arrays.stream(inputArr)
//			.flatMapToInt(data-> {
//				arr.add(new Edge(data[0], data[1], data[2]));
//				return IntStream.of(data[0],data[1]);
//			})
//			.distinct()
//			.count();
		int n= arr.stream()
			.flatMapToInt(a->IntStream.of(a.v1,a.v2))
			.max().getAsInt();
		
		unf=new int[n+1];
		
		System.out.println(s(n));
		System.out.println(Arrays.toString(unf));
	}
	
	static int s(int v) {
		int result = 0;
		int cnt = 0;
		//자신 그룹에 자신으로 초기화
		for(int i=1;i<unf.length;i++) unf[i]=i;
		//최소비용 나오는 이유
		Collections.sort(arr);
		for(Edge next : arr) {
			if(cnt>v) break;
			//서로 다르면 그룹 맺기
			int f1 = f(next.v1) ;
			int f2 = f(next.v2) ;
			if(f1 != f2) {
//			if(f(next.v1) != f(next.v2)) {
				++cnt;
				//누산
				result += next.cost;
				//그룹 맺기
				u(f1, f2);
			}
		}
		
		return result;
	}
	//재귀적으로 같은 그룹인지 찾아준다.
	static int f(int v) {
		if(unf[v] == v) return unf[v];
		else return unf[v] = f(unf[v]);
	}
	static void u(int a, int b) {
		int fa = f(a);
		int fb = f(b);
		//서로 다르니 그룹맺기
//		if(fa != fb) unf[fa] = fb;
		unf[a] = b;
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