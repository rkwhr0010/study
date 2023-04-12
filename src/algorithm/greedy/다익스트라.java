package algorithm.greedy;

import java.util.*;
class Edge implements Comparable<Edge>{
    public int vex;
	public int cost;
    Edge(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge ob){
        return this.cost-ob.cost;
    }
}

class 다익스트라 {
	static int n, m;
	static ArrayList<ArrayList<Edge>> graph;
	static int[] dis;
	public void solution(int v){
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		pQ.offer(new Edge(v, 0));
		dis[v]=0;
		while(!pQ.isEmpty()){
			Edge tmp=pQ.poll();
			int now=tmp.vex;
			int nowCost=tmp.cost;
			if(nowCost>dis[now]) continue;
			for(Edge ob : graph.get(now)){
				if(dis[ob.vex]>nowCost+ob.cost){
					dis[ob.vex]=nowCost+ob.cost;
					pQ.offer(new Edge(ob.vex, nowCost+ob.cost));
				}
			}
		}
	}
	void sol(int v) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		dis[v] = 0;
		queue.add(new Edge(v, 0));
		
		while(!queue.isEmpty()) {
			//현시점 가장 비용 위치
			Edge e = queue.poll();
			int vex = e.vex;
			int cost = e.cost;
			if(cost> dis[vex]) continue;
			for(Edge next : graph.get(vex)) {
				//다음 경로, 현재까지비용+다음비용
				if(dis[next.vex] > cost+next.cost) {
					dis[next.vex] = cost+next.cost;
					queue.add(new Edge(next.vex, cost+next.cost));
				}
			}
		}
	}
	
	
	void solution2(int v) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(v, 0));
		dis[v] = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int now = edge.vex;
			int cost = edge.cost;
			
			//최소 정로를 구하는 것이기에 현재 코스트보다 구해진 코스트가 크면 넘어간다.
			//단, 이 조건을 반드시 만족하려면 cost가 음수여선 안된다.
			if(cost>dis[now]) continue;
			
			//현재 위치에서 갈 수 있는 모든 경로를 순회
			for(Edge e : graph.get(now)) {
				//다음으로 갈 위치의 경로 비용보다 현재 비용 + 다음 경로비용이 더 작다면 교체해야 한다.
				if(dis[e.vex] > cost+e.cost) {
					dis[e.vex] = cost+e.cost;
					//더 작은 비용만 큐에 넣는다.
					//더 컸다면 무슨 짓을 해도 현재보다 더 작은 값이 나올 수 없기 때문에 저장을 안한다.
					//이유는 cost는 반드시 정수라는 조건이 걸려있기 때문이다.
					pq.offer(new Edge(e.vex, cost+e.cost));
				}
			}
		}
	}
	

	public static void main(String[] args){
		다익스트라 T = new 다익스트라();
		Scanner kb = new Scanner(System.in);
		n=kb.nextInt();
		m=kb.nextInt();
		graph = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<=n; i++){
			graph.add(new ArrayList<Edge>());
		}
		dis=new int[n+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		for(int i=0; i<m; i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			int c=kb.nextInt();
			graph.get(a).add(new Edge(b, c));
		}
		T.sol(1);
		for(int i=2; i<=n; i++){
			if(dis[i]!=Integer.MAX_VALUE) System.out.println(i+" : "+dis[i]);
			else System.out.println(i+" : impossible");
		}
	}
}
/*
6 9
1 2 12
1 3 4
2 1 2
2 3 5
2 5 5
3 4 5
4 2 2
4 5 5
6 4 5

2 : 11
3 : 4
4 : 9
5 : 14
6 : impossible


 * 
 */

