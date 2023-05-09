package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;
/**
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
	
	static void sol(int v) {
		//우선순위 큐
		Queue<Edge> q = new PriorityQueue<>();
		dis[v] = 0;
		q.offer(new Edge(v, 0));
		while(!q.isEmpty()) {
			//현재 상태값을 가진 정점.
			Edge cur = q.poll();
			//혹시 지금까지 비용이 이미 저장된 비용보다 큰가?
			if(cur.cost>dis[cur.vex]) continue;
			//현재에서 다음 경로들
			for(Edge next : graph.get(cur.vex)) {
				//누산된 비용이 현재까지비용 + 다음경로비용보다 크면 교체한다.
				if(dis[next.vex] > cur.cost+next.cost) {
					dis[next.vex] = cur.cost+next.cost;
					q.offer(new Edge(next.vex, dis[next.vex]));
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
		System.out.println("==정점 만큼 골라내기==");
		Arrays.stream(arr)
			.flatMap(data -> Stream.of(data[0],data[1]))
			.distinct()
			.peek(data -> System.out.print(data+", "))
			.forEach( data -> graph.add(new ArrayList<>()));
		//거리비용 저장 배열
		dis = new int[graph.size()];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		System.out.println("\n==간선 정보==");
		Arrays.stream(arr)
			.peek(data->System.out.println(data[0]+"->" +data[1]+" 가중치:"+data[2]))
			.forEach(data ->graph.get(data[0]).add(new Edge(data[1], data[2])));
		
		so(1);
		
		
		System.out.println("== 정점 별 최소 비용 ==");
		for(int i=2; i<graph.size(); i++){
			if(dis[i]!=Integer.MAX_VALUE) System.out.println(i+" : "+dis[i]);
			else System.out.println(i+" : impossible");
		}
	}
}
