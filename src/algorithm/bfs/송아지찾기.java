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
	static class TestCase {
		final int[] input;
		final int result;
		
		public TestCase(int[] input, int result) {
			this.input = input;
			this.result = result;
		}
	}
	
	static TestCase[] testCases = {
		new TestCase(new int[] {8, 3}, 5),	
		new TestCase(new int[] {13, 35}, 6),	
		new TestCase(new int[] {1, 21}, 4),	
		new TestCase(new int[] {3, 4356}, 873),	
		new TestCase(new int[] {7, 8945}, 1790),	
	};
	
	static int[] nextArr = {1, -1, 5}; // 방향
	static int start; //시작
	static int end;   //목표
	static int[] chk = new int[10001]; //재방문 방지
	
	public static void main(String[] args) {
		System.out.println(sol());
//		System.out.println(solution());
		
		
	}
	static int sol() {
		int result = 0 ;
		//방문
		chk[start] = 1;
		
		LinkedList<Integer> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			result++; //찾은 수 추가
			int size = q.size();
			for(int i = 0; i < size; i++) {
				int cur = q.poll();
				for(int next : nextArr) {
					int nextVal = cur + next;
					if(0 <= nextVal && nextVal <= 10000 && chk[nextVal] == 0) {
						if (nextVal == end) return result;
						chk[nextVal] = 1;
						q.offer(nextVal);
					}
				}
			}
		}
		
		return result;
	}
	
	static int solution() {
		//얼마만에 찾았는지 깊이 변수
		int level = 0;
		//좌표용 큐
		Queue<Integer> q = new LinkedList<>();
		//방문
		chk[start] = 1;
		q.offer(start);
		
		//방문 좌표 순회
		while(!q.isEmpty()) {
			//탐색 깊이 하나 증가
			level++;
			//BFS에서 같은 레벨을 판별하는 중요한 길이 변수
			final int sameLevelSize = q.size();
			for(int i = 0; i < sameLevelSize; i++) {
				int cur = q.poll();
				//다음 좌표 가중치
				for(final int next : nextArr) {
					//다음 좌표
					final int nextV = cur + next;
					//첫 방문 체크
					if((0 <= nextV && nextV <= 10000) && chk[nextV] == 0) {
						//핵심 로직(탈출 조건)
						if(nextV == end) return level;
						chk[nextV] = 1;
						q.offer(nextV);
					}
				}
			}
		}
		
		return -1;
	}
}
