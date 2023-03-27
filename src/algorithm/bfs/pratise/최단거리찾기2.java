package algorithm.bfs.pratise;

import java.util.LinkedList;
import java.util.Queue;

public class 최단거리찾기2 {
	//한 턴당 움직이는 경우의 수
	static int[] dis = {1,-1,5};
	//최대 거리
	static int max;
	//메모이제이션
	static boolean[] chk;
	//시작 위치
	static int start;
	//목적지
	static int end;
	//최단거리 
	static int answer;
	
	public static void main(String[] args) {
		max = 10000;
		chk = new boolean[max+1];//0사용 안함
		start = 4;
		end = 50;
		BFS();
		System.out.println(answer);
	}

	private static void BFS() {
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(start);
		while(!Q.isEmpty()) {
			answer++;
			for (int i = 0,len=Q.size() ; i < len; i++) {
				Integer value = Q.poll();
				
				if(value == end) return;
				for(Integer next : dis) {
					Integer nv = next + value;
					if(nv>=1 && nv<=10000 && chk[nv]==false) {
						chk[nv] = true;
						Q.offer(nv);
					}
				}
			}
		}
	}
}
