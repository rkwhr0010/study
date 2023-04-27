package algorithm.bfs.pratise;

import java.util.LinkedList;
import java.util.Queue;

public class 송아지찾기연습 {
	//한 턴당 움직이는 경우의 수
	static int[] dis = {1,-1,5};
	//최대 거리
	static int max;
	//방명록
	static int[] chk;
	//시작 위치
	static int start;
	//목적지
	static int end;
	//최단거리 
	static int answer;
	public static void main(String[] args) {
		max = 10000;
		chk = new int[max+1];//0사용 안함
		start = 4;
		end = 50;
		BFS3(start);
		System.out.println(answer);
	}
	static void BFS2(int start) {
		chk[start] = 1;
		LinkedList<Integer> Q = new LinkedList<>();
		Q.add(start);
		
		while(!Q.isEmpty()) {
			answer ++ ;
			for(int i=0,len = Q.size();i<len;i++) {
				Integer cur = Q.poll();
				if(cur == end) return;
				for(Integer next : dis) {
					int nv = next + cur;
					if(nv>=1&&nv<=10000&& chk[nv] == 0) {
						chk[nv] = 1;
						Q.add(nv);
					}
				}
			}
		}
	}
	
	static void BFS3(int start) {
		chk[start] = 1;
		Queue<Integer> Q = new LinkedList<>();
		Q.add(start);
		
		while(!Q.isEmpty()) {
			answer ++ ;
			for(int i=0,length=Q.size();i<length;i++) {
				Integer value = Q.poll();
				if(value == end) return;
				for(Integer next : dis) {
					int nv = next+value;
					if(nv>=1&&nv<=10000&&chk[nv] == 0 ) {
						chk[nv] = 1;
						Q.add(nv);
					}
				}
			}
		}
	}
	
	
	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		chk[start] = 1;
		q.add(start);
		
		while(!q.isEmpty()) {
			answer++;
			for(int i=0,len = q.size();i<len;i++) {
				int value = q.poll();
				if(value == end) return;
				
				for(int next : dis) {
					int nv = next + value;
					if(nv>=1&&nv<=10000&& chk[nv]==0) {
						q.add(nv);
						chk[nv]= 1;
					}
				}
			}
		}
	}
}
