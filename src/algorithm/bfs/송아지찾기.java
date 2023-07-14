package algorithm.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
송아지 찾기(BFS : 상태트리탐색)
현수는 송아지를 잃어버렸다. 다행히 송아지에는 위치추적기가 달려 있다. 현수의 위치와 송아
지의 위치가 수직선상의 좌표 점으로 주어지면 현수는 현재 위치에서 송아지의 위치까지 다음
과 같은 방법으로 이동한다. 송아지는 움직이지 않고 제자리에 있다.
현수는 스카이 콩콩을 타고 가는데 한 번의 점프로 앞으로 1, 뒤로 1, 앞으로 5를 이동할 수
있다. 최소 몇 번의 점프로 현수가 송아지의 위치까지 갈 수 있는지 구하는 프로그램을 작성
하세요.
▣ 입력설명
첫 번째 줄에 현수의 위치 S와 송아지의 위치 E가 주어진다. 직선의 좌표 점은 1부터 10,000
까지이다.
▣ 출력설명
점프의 최소횟수를 구한다. 답은 1이상이며 반드시 존재합니다.
▣ 입력예제 1
5 14
▣ 출력예제 1
3
▣ 입력예제 2
8 3
▣ 출력예제 2
5
 */
public class 송아지찾기 {
	static int[] nextArr = {1, -1, 5}; // 방향
	static int start; //시작
	static int end;   //목표
	static int[] chk = new int[10001]; //재방문 방지
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		start = sc.nextInt();
		end = sc.nextInt();
		sc.close();
		System.out.println(sol());
		
		
	}
	
	static int sol() {
		//몇번만에
		int level = 0;
		Queue<Integer> q = new LinkedList<>();
		//방문
		chk[start] = 1;
		//BFS 상태 트리 시작
		q.offer(start);
		
		//깊이 만큼 반복
		while(!q.isEmpty()) {
			//한 단계 깊이 들어간다.
			level++;
			//같은 레벨 요소만큼 순회
			for(int i = 0, len = q.size(); i < len; i++) {
				//현 위치
				int c = q.poll();
				//다음으로 갈 위치
				for(int n : nextArr) {
					int cn = c + n;
					//첫 방문인지
					if(isFirstVisit(cn)) {
						//다음 경로가 목적지인지
						if(cn == end) return level;
						//방문
						chk[cn] = 1;
						q.offer(cn);
					}
				}
			}
		}
		
		return -1;
	}

	private static boolean isFirstVisit(int cn) {
		return (1 <= cn && cn <= 10000) && chk[cn] == 0;
	}
	
}
