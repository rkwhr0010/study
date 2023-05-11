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
//		ThreadLocalRandom random = ThreadLocalRandom.current();
//		int[] array = IntStream.rangeClosed(0, 500).toArray();
		
//		IntStream.rangeClosed(0, 500)
//			.forEach((index)->{
//				int v1 = random.nextInt(1, 501);
//				int v2 = random.nextInt(1, 501);
//				while(v1==v2) {
//					v2 = random.nextInt(1, 501);
//				}
//				int v = random.nextInt(1, 501);
//				arr.add(new Edge(v1, v2, v));
//			});
		
		//마지막 정검 구하기
		int n=(int)Arrays.stream(inputArr)
			.flatMapToInt(data-> {
				arr.add(new Edge(data[0], data[1], data[2]));
				return IntStream.of(data[0],data[1]);
			})
			.distinct()
			.count();
//		int n= arr.stream()
//			.flatMapToInt(a->IntStream.of(a.v1,a.v2))
//			.max().getAsInt();
		
		unf=new int[n+1];
		
		System.out.println(s(n));
		System.out.println(Arrays.toString(unf));
	}
	
	//재귀적으로 그룹값을 찾는다.
	static int f1(int v) {
		//그룹 값이 내 값과 같으면, 아무거나 리턴해도된다.
		if(unf[v]==v) return unf[v];
		//내 그룹에 저장된 부모링크로 재귀적으로 부모의부모 끝까지 그룹값을 탐색한다.
		else return unf[v] = f1(unf[v]);
	}
	//두 값이 그룹이 다르면, 같은 그룹으로 묶어주고, 그 결과를 리턴한다.
	static boolean u1(final int a,final  int b) {
		boolean result = false;
		//찾은 그룹 값이 서로 다르면 그룹 맺어준다.
		final int fa = f1(a);
		final int fb = f1(b);
		//a가 b와 같은 그룹임을 표시한다.
		result = fa!=fb;
		if(result) unf[fa] = fb;
		return result;
	}
	static int s(final int v) {
		//누산 값
		int result = 0;
		for(int i=1;i<unf.length;i++) unf[i]=i;
		//그리디를 위한 정렬
		Collections.sort(arr);
		//현 시점 가장 작은 값
		for(final Edge now : arr) {
			//두 간선간 같은 그룹이냐? 같은 그룹이면 누산
			if(u1(now.v1, now.v2)) result += now.cost;
		}
		return result;
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