package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;
/**
다익스트라 알고리즘은 그래프에서 한 노드에서 다른 노드로 가는 최단 경로를 찾는 알고리즘입니다. 
이 알고리즘은 1956년 네덜란드의 컴퓨터 과학자 에츠허르 데이크스트라가 고안했습니다.

다익스트라 알고리즘은 다음과 같은 단계로 동작합니다.

1. 출발 노드를 선택합니다.
2. 출발 노드를 제외한 모든 노드의 최소 비용을 무한대로 초기화합니다.
3. 방문하지 않은 노드 중에서 최소 비용이 가장 작은 노드를 선택합니다.
4. 선택한 노드를 거쳐서 다른 노드로 가는 비용을 계산합니다.
5. 계산된 비용이 기존의 최소 비용보다 작으면 최소 비용을 갱신합니다.
6. 3~5번을 반복합니다.

다익스트라 알고리즘은 최악의 경우 O(E log V)의 시간 복잡도를 갖습니다. 여기서 E는 그래프의 간선 수, V는 그래프의 노드 수입니다.

다익스트라 알고리즘은 그래프에서 최단 경로를 찾는 데 널리 사용되는 알고리즘입니다. 예를 들어, 도로 교통망에서 출발지에서 목적지까지 가는 최단 경로를 찾는 데 사용할 수 있습니다.

가중치 방향그래프에서 1번 정점에서 모든 정점으로의 최소 거리비용을 출력하는 프로
그램을 작성하세요. 
 */
public class 중요_다익스트라연습 {
	private static class Edge implements Comparable<Edge>{
	    private final int vex;
	    private final int cost;
		
		private Edge(int vex, int cost) {
	        this.vex = vex;
	        this.cost = cost;
	    }
	    public int compareTo(Edge ob){
	        return Integer.compare(this.cost,ob.cost);
	    }
	    public String toString() {
	    	return "["+vex+", "+cost+"]";
	    }
	}
	static ArrayList<ArrayList<Edge>> graph;
	static int[] dis;
	
	static void so(int v) {
		//다익스트라를 위한 우선순위 큐, 탐색 속도 N log N
		Queue<Edge> q = new PriorityQueue<>();
		//거리 값
		dis[v] = 0;
		//현재 상태 저장
		q.offer(new Edge(v, 0));
		while(!q.isEmpty()) {
			//현재 위치
			Edge cur = q.poll();
			//현재까지 누산 비용이, 저장된 비용보다 크면 필요없다.
			if(cur.cost>dis[cur.vex]) continue;
			//현재 정점에서 갈 수 있는 다음 경로들
			for(Edge next : graph.get(cur.vex)) {
				//다음 경로에 저장된 최소비용이, 현개까지 누산된 경로비용+다음까지 가는 비용보다 크냐
				if(dis[next.vex] > cur.cost + next.cost ) {
					dis[next.vex] = cur.cost + next.cost;
					q.offer(new Edge(next.vex, dis[next.vex]));//더 작은 값으로 갱신
				}
				
			}
		}
	}
	
	//출발 위치를 입력 받음
	static void ex1(final int start) {
		//1. 시작 노드 제외 전체 맥스값 초기화
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		//2. 가장 작은 비용 탐색을 위한 큐
		final PriorityQueue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(start, 0));
		while(!q.isEmpty()) {
			//3. 최소 비용 노드 탐색
			final Edge lowCostEdge = q.poll();
			for(Edge nextEdge : graph.get(lowCostEdge.vex)) {
				//4. 다음으로 가는 비용 계산
				final Integer calculatedValue = lowCostEdge.cost + nextEdge.cost;
				//5. 계산된 비용이 기존의 최소 비용보다 작으면 최소 비용을 갱신
				if(dis[nextEdge.vex]>calculatedValue) {
					dis[nextEdge.vex] = calculatedValue;
					q.offer(new Edge(nextEdge.vex, calculatedValue));
				}
			}
		}//3~5 반복
	}
	static void ex2(final int start) {
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		final Queue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			final Edge lowCostEdge = pq.poll();
			
			for(final Edge nextEdge : graph.get(lowCostEdge.vex)) {
				final Integer sumCost = Integer.sum(lowCostEdge.cost, nextEdge.cost);
				if(dis[nextEdge.vex] > sumCost) {
					dis[nextEdge.vex] = sumCost;
					pq.add(new Edge(nextEdge.vex, sumCost));
				}
			}
		}
	}
	
	static void ex3(int start) {
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		Queue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(start, 0));
		while(!q.isEmpty()) {
			Edge lowCostEdge = q.poll();
			for(final Edge nextEdge : graph.get(lowCostEdge.vex)) {
				final int sumCost = Integer.sum(lowCostEdge.cost,nextEdge.cost);
				if(dis[nextEdge.vex]> sumCost) {
					dis[nextEdge.vex] = sumCost;
					q.offer(new Edge(nextEdge.vex, sumCost));
				}
			}
		}
	}
	
	static void ex4(int start) {
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(start, 0));
		
		while(!q.isEmpty()) {
			final Edge lowEdge = q.poll();
			for(final Edge nextEdge : graph.get(lowEdge.vex)) {
				final int sumCost = lowEdge.cost + nextEdge.cost;
				if(dis[nextEdge.vex]>sumCost) {
					dis[nextEdge.vex] = sumCost;
					q.offer(new Edge(nextEdge.vex, sumCost));
				}
			}
		}
	}
	
	static void ex5(int start) {
		//모든 배열을 최대값(가능하면 무한대)로 초기화
		Arrays.fill(dis, Integer.MAX_VALUE);
		//단, 시작 값은 0으로 초기화
		dis[start] = 0;
		
		//다익스트라 알고리즘 탐색을 위한 비교 기준(최소값)
		Comparator<Edge> comparator = (a,b)-> Integer.compare(a.cost, b.cost);
		//상태값 저장을 위한 우선순위 큐
		PriorityQueue<Edge> q = new PriorityQueue<>(comparator);
		//탐색 시작 값
		q.offer(new Edge(start, 0));
		
		while(!q.isEmpty()) {
			Edge now = q.poll();//현시점 가장 최소비용
			for(Edge next : graph.get(now.vex)) {//현 위치에 연결된 다른 경로들
				Integer sumCost = now.cost + next.cost;//현재까지 계산된 최소비용 + 다음 경로 비용
				if(dis[next.vex] > sumCost) {//다음 경로에 저장된 최소비용이 현재까지 계산 비용보다 크면 교체
					dis[next.vex] = sumCost;
					q.offer(new Edge(next.vex, sumCost));//가장 최소 비용인 경우에만 다익스트라 탐색 대상이 된다.
				}
			}
		}
	}
	static void ex6 (int start) {
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(start, 0));
		
		while(!q.isEmpty()) {
			Edge now = q.poll();
			for(Edge next : graph.get(now.vex)) {
				int sum = now.cost+next.cost;
				if(dis[next.vex]>sum) {
					dis[next.vex] = sum;
					q.offer(new Edge(next.vex, sum));
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		//{간선시작, 간선끝, 가중치}
		int[][] arr = 
			{{1, 2, 12}
			,{1, 3, 4 }
			,{2, 1, 2 }
			,{2, 3, 5 }
			,{2, 5, 5 }
			,{3, 4, 5 }
			,{4, 2, 2 }
			,{4, 5, 5 }
			,{6, 4, 5 }	};
		
		graph = new ArrayList<ArrayList<Edge>>();
		graph.add(new ArrayList<>()); // 0 번 미사용
		Arrays.stream(arr)
			.flatMap(data -> Stream.of(data[0],data[1]))
			.distinct()
			.forEach( data -> graph.add(new ArrayList<>()));
		//거리비용 저장 배열
		dis = new int[graph.size()];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		System.out.println("\n==간선 정보==");
		Arrays.stream(arr)
			.peek(data->System.out.println(data[0]+"->" +data[1]+" 가중치:"+data[2]))
			.forEach(data ->graph.get(data[0]).add(new Edge(data[1], data[2])));
		
		ex6(1);
		
		
		System.out.println("== 정점 별 최소 비용 ==");
		for(int i=2; i<graph.size(); i++){
			if(dis[i]!=Integer.MAX_VALUE) System.out.println(i+" : "+dis[i]);
			else System.out.println(i+" : impossible");
		}
	}
}
