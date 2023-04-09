package algorithm.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 송아지찾기 {
	//한 턴당 움직이는 경우의 수
	static int[] dis = {1,-1,5};
	//최대 거리
	static int max;
	//메모이제이션
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
		BFS();
	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		while(!q.isEmpty()) {
			int len = q.size();
			answer++;// 회차 갱신
			System.out.print(answer+" times ");
			for (int i = 0; i < len; i++) {
				int value = q.poll();
				if(value==end) {
					System.out.println(value + " end");
					return;
				}
				for(int next : dis) {
					int nv = next+value;
//					if(nextValue==end) {
//						answer++;// 회차 갱신
//						System.out.print(answer+"회차 ");
//						System.out.println(nextValue + " 도착");
//						return;
//					}
					if(nv>=1 && nv <=10000  && chk[nv]==0) {
						chk[nv]= 1;
						q.offer(nv);
					}
				}
				System.out.print(value+" ");
			}
			System.out.println();
		}
	}
}
