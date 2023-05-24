package algorithm.dynamicprogramming.practise;

import java.util.Arrays;

public class 중요_최대점수구하기 {
	static int[] dy;
	static int timeLimit;
	
	public static void main(String[] args){
		int[][] input = {
				{10, 5  },
				{25, 12 },
				{15, 8  },
				{6 ,3   },
				{7 ,4   }
		};
		timeLimit= 20;//제한시간
		dy=new int[timeLimit+1];
		//문제를 순회한다.
		sol3(input);
		System.out.print(Arrays.stream(dy).max().getAsInt());
	}
	//dy 의 각 위치는 시간을 의미한다 i가 10이면 10분이 주어졌을때 가장 최상의 점수를 의미
	private static void sol(int[][] input) {
		for(int i=0; i<input.length; i++){
			int ps=input[i][0];//풀었을때점수
			int pt=input[i][1];//소요시간
			//제한시간부터 역순회시작(내 소요시간까지)
			for(int j= timeLimit; j>=pt; j--){
				dy[j]=Math.max(dy[j], dy[j-pt]+ps);
			}
		}
	}
	static void sol1(int[][] input) {
		//문제, 점수 배열 순회
		for(int i = 0;i<input.length;i++) {
			int score = input[i][0];
			int time = input[i][1];
			//문제는 단 한개, 뒤에서부터 나의 소요시간까지 순회, 
			for(int j=timeLimit;j>=time;j--) {
				//dy[j] 주어진 시간이 j 일때 최대 점수, dy[j-time] 이전에 푼 문제+지금 점수
				dy[j] = Math.max(dy[j], dy[j-time]+score);
			}
		}
	}
	static void sol2(int[][] input) {
		//문제 순회
		for(int i=0;i<input.length;i++) {
			int score = input[i][0];
			int time = input[i][1];
			//유한한 입력이면 뒤에서부터
			for(int j=dy.length-1;j>=time;j--) {
				dy[j] = Math.max(dy[j], dy[j-time]+score);
			}
		}
	}
	static void sol3(int[][] input) {
		for(int i = 0; i<input.length;i++) {
			int score = input[i][0];
			int time = input[i][1];
			for(int j=dy.length-1;j>=time;j--) {
				dy[j] = Math.max(dy[j], dy[j-time]+score);
			}
		}
	}
}


/* 
문제수/제한시간
5 20
좌,점수 우, 시간
10 5
25 12
15 8
6 3
7 4


41
*
*/
