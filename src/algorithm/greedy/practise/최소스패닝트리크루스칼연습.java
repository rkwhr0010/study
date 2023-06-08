package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
크루스칼 알고리즘은 그래프에서 최소 비용 신장 트리를 찾는 알고리즘입니다. 
최소 비용 신장 트리란 그래프의 모든 노드를 연결하는 트리 중에서 간선의 비용을 모두 합한 값이 최소인 트리를 말합니다. 
크루스칼 알고리즘은 다음과 같은 과정으로 동작합니다.

1. 그래프의 모든 간선을 비용 순으로 정렬합니다.
2. 가장 작은 간선부터 시작하여 그래프의 모든 노드를 연결합니다.
3. 새로운 간선을 추가할 때마다 사이클이 생성되지 않도록 합니다.

크루스칼 알고리즘은 시간 복잡도가 O(E * log V)입니다. 여기서 E는 그래프의 간선 개수이고, V는 그래프의 노드 개수입니다.

크루스칼 알고리즘은 최소 비용 신장 트리를 찾는 데 가장 많이 사용되는 알고리즘 중 하나입니다. 간단하고 구현이 쉽고, 시간 복잡도가 O(E * log V)로 빠르기 때문입니다.

크루스칼 알고리즘은 컴퓨터 네트워크, 전기 배선, 교통 네트워크 등 다양한 분야에서 사용됩니다.


 * 
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
	static List<Edge> arr;
	
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
		
		//입력값 처리
		arr = Arrays.stream(inputArr)
			.map( row -> new Edge(row[0], row[1], row[2]) )
			.collect(Collectors.toList());
		
		//노드 종류 구하기
		int kind = (int)arr.stream()
			.flatMapToInt(e -> IntStream.of(e.v1,e.v2))
			.distinct()
			.count();
		
		unf=new int[kind+1];
		
//		System.out.println(s(kind));
//		System.out.println(Arrays.toString(unf));
		
		
		//자료구조 생성
		UnionFind uf = new UnionFind(kind);
		
		//자체 정렬 기준이 있지만, 명시적으로 정렬이 필요하다는 것을 보여주기 위해, 외부 정렬기준을 넣었다.
		Collections.sort(arr, (e1, e2)->e1.cost-e2.cost);
		int result = uf.generateUnion(arr);
		System.out.println(result);
		
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
	
	private static class UnionFind{
		int[] parent; //부모 id 저장소
		UnionFind(int kind) {
			parent = new int[kind+1];
			for(int i=0;i<parent.length;i++) {
				parent[i] = i; //초기엔 자기 자신이 부모
			}
		}
		//재귀적으로 입력 id 부모를 찾는 함수
		int find(int id) {
			if(parent[id] == id) return id;
			else return parent[id] = find(parent[id]);
		}
		//서로 다른 그룹을 하나의 집합으로 만들어주는 함수
		void union(final int id1,final int id2) {
			if( !isSame(id1, id2) ) parent[id1] = id2;
		}
		//서로 같은 부모를 공유하는지 확인하는 함수
		boolean isSame(final int id1,final int id2) {
			return find(id1) == find(id2);
		}
		int generateUnion(List<Edge> list) {
			int result = 0; 
			for(Edge e : list) {
				union(e.v1,e.v2);
				if(isSame(e.v1, e.v2)) {
					result += e.cost;
					//서로 다른 그룹인지 체크할 필요없다. 이미 union에서 체크한다.
				}
			}
			return result;
		}
	}
	
}

/*
 * 모든 도시를 연결하면서 드는 최소비용을 출력한다.
196

*/