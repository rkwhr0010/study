package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;
/**
가중치 방향그래프에서 1번 정점에서 모든 정점으로의 최소 거리비용을 출력하는 프로
그램을 작성하세요. 
 */
public class 다익스트라연습 {
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
	
	static void sol(int v) {
		//내부적으로 이분검색을 사용해 시간 복잡도가 log n
		PriorityQueue<Edge> q = new PriorityQueue<>();
		//시작 정점은 거리 0
		dis[v] = 0;
		//시작 정점은 비용 0
		q.offer(new Edge(v, 0));
		while(!q.isEmpty()) {
			//자료구조 상 가장 비용이 작은 정점이 나온다.
			Edge cur = q.poll();
			//현재 가장 작은 비용이 이미 저장된 비용보다 크면 확인할 필요가 없다.
			if(cur.cost>dis[cur.vex]) continue;
			//graph.get(cur.vex)은 n번 수행된다.
			//graph.get(cur.vex) 결과로 나온 Edge는 최소 n번 이상 수행된다.
			for(Edge next : graph.get(cur.vex)) {
				//다음 경로 비용 계산 시작
				//다음 경로에 이미 저장된 비용 > 현재까지 누산된 비용 + 다음 경로까지 비용
				if(dis[next.vex] > cur.cost + next.cost ) {
					dis[next.vex] = cur.cost + next.cost;
					//중요_ 현재까지 누산된 비용을 다음 경로까지 물고간다.
					q.offer(new Edge(next.vex, cur.cost + next.cost));
				}
			}
		}
	}
	
	static void sol1(int v) {
		//최소비용을 위한 큐
		PriorityQueue<Edge> q = new PriorityQueue<>();
		//현재 위치 최소비용
		dis[v] = 0;
		//현재 상태를 가진 정점객체
		q.offer(new Edge(v, 0));
		
		while(!q.isEmpty()) {
			//우선순위큐 특성상 최소 비용 정점순으로 나옴
			Edge min = q.poll();
			//현재 정점위치에서 다음 경로로 가능 비용 계산
			for(Edge next : graph.get(min.vex)) {
				//혹시, 다음 경로에 저장된 비용이, 지금까지 비용+다음 비용 보다 크냐
				if(dis[next.vex] > min.cost+next.cost) {
					//더 적으니 비용 갱신
					dis[next.vex] = min.cost+next.cost ;
					q.offer(new Edge(next.vex, min.cost+next.cost));
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
		
		sol1(1);
		System.out.println("== 정점 별 최소 비용 ==");
		for(int i=2; i<graph.size(); i++){
			if(dis[i]!=Integer.MAX_VALUE) System.out.println(i+" : "+dis[i]);
			else System.out.println(i+" : impossible");
		}
	}
}
