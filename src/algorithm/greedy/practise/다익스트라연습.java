package algorithm.greedy.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

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
	
	static void sol11(int v) {
		PriorityQueue<Edge> Q = new PriorityQueue<>();
		dis[v] = 0;
		Q.add(new Edge(v, 0));
		
		while(!Q.isEmpty()) {
			Edge cur = Q.poll();
			
			if(dis[cur.vex] <cur.cost ) continue;
			
			for(Edge n : graph.get(cur.vex)) {
				if(dis[n.vex] > cur.cost+n.cost) {
					dis[n.vex] = cur.cost+n.cost;
					Q.add(new Edge(n.vex , cur.cost+n.cost));
				}
				
			}
			
		}
		
	}
	
	
	static void sol4(int v) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		dis[v] = 0;
		q.add(new Edge(v, 0));
		
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			if(cur.cost > dis[cur.vex]) continue;
			for(Edge next : graph.get(cur.vex)) {
				if(dis[next.vex] > cur.cost + next.cost) {
					dis[next.vex] = cur.cost + next.cost;
					q.add(new Edge(next.vex, cur.cost + next.cost));
				}
			}
		}
	}

	static void sol1(int v) {
		PriorityQueue<Edge> Q = new PriorityQueue<>();
		Q.add(new Edge(v, 0));
		dis[v] = 0;
		
		while(!Q.isEmpty()) {
			//가장 작은 값
			Edge c = Q.poll();
			
			//지금까지 누산해온 값이 큐 코스트에 저장돼 있다.
			//그 값이 dis에 저장된 값보다 크면 계산할 가치가 없다.
			if(c.cost > dis[c.vex]) continue;
			
			//현재 정점에서 다음 경로들
			for(Edge n : graph.get(c.vex)) {
				//여기 정점은 누산된 cost가 아닌 날 데이터
				if(dis[n.vex]> c.cost+ n.cost) {
					dis[n.vex] = c.cost+ n.cost;
					Q.add(new Edge(n.vex, c.cost+ n.cost));
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
		
		sol11(1);
		System.out.println("== 정점 별 최소 비용 ==");
		for(int i=2; i<graph.size(); i++){
			if(dis[i]!=Integer.MAX_VALUE) System.out.println(i+" : "+dis[i]);
			else System.out.println(i+" : impossible");
		}
	}
}
