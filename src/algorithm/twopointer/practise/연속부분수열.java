package algorithm.twopointer.practise;

import java.util.Scanner;

/*
N개의 수로 이루어진 수열이 주어집니다.
이 수열에서 연속부분수열의 합이 특정숫자 M이 되는 경우가 몇 번 있는지 구하는 프로그램을
작성하세요.
만약 N=8, M=6이고 수열이 다음과 같다면
1 2 1 3 1 1 1 2
합이 6이 되는 연속부분수열은 {2, 1, 3}, {1, 3, 1, 1}, {3, 1, 1, 1}로 총 3가지입니다.
▣ 입력설명
첫째 줄에 N(1≤N≤100,000), M(1≤M≤100,000,000)이 주어진다.
수열의 원소값은 1,000을 넘지 않는 자연수이다.
▣ 출력설명
첫째 줄에 경우의 수를 출력한다.
▣ 입력예제 1
8 6
1 2 1 3 1 1 1 2
▣ 출력예제 1
3
 */
public class 연속부분수열 {
	
	public static void main(String[] args) {
		Input input = input();
		System.out.println(solution(input));
	}

	private static int solution(Input in) {
		int result = 0;
		
		for(int low = 0, high = 0, sum = 0
			; high < in.n
			; high++) {
			
			//누산
			sum += in.arr[high];
			//값 변경 시 반드시 체크
			if(sum == in.m) result++;
			
			//누산 값이 목표 값과 크면 작아질 때까지 빼준다.
			while(sum > in.m) {
				sum -= in.arr[low++];
				//값 변경 시 반드시 체크
				if(sum == in.m) result++;
			}
		}
		
		return result;
	}

	private static Input input() {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
		
		return new Input(n, m, arr);
	}
	
	static class Input{
		public final int n; //수열 길이
		public final int m; //목표 수열 합
		public final int[] arr;//수열
		
		public Input(int n, int m, int[] arr) {
			this.n = n;
			this.m = m;
			this.arr = arr;
		}
	}
}
