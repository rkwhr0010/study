package algorithm.dynamicprogramming;

import java.util.Scanner;

public class 최대부분증가수열 {
static int dy[];
	
 	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		dy = new int[arr.length];
		
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(sol1(arr));
		sc.close();
	}

	private static int solution(int[] arr) {
		int answer = 0;
		dy[0] = 1;
		
		for(int i=1; i<arr.length;i++) {
			int max = 0;
			
			for(int j = i-1;j>=0;j--) {
				if(arr[j] < arr[i] && dy[j]>max ) {
					max = dy[j];
				}
			}
			dy[i] = max +1;
			answer = Math.max(answer, dy[i]);
		}
		return answer;
	}
	
	static int sol(int[] arr) {
		int ans = 0;
		dy[0] = 1;
		
		for(int i = 1 ; i < arr.length;i++) {
			int max = 0;
			for(int j = i-1; j>= 0;j--) {
				if(arr[i]>arr[j] && dy[j] >max)
					max = dy[j];
			}
			dy[i] = max+1;
			ans = Math.max(ans, dy[i]);
		}
		return ans;
	}
	
	static int sol1(int[] arr) {
		int ans = 0 ;
		dy[0] = 1;
		//메인, 입력배열 순회
		for(int i=1;i<arr.length;i++) {
			int cnt = 0;
			//현지점 이전비교
			for(int j=i-1;j>=0;j--) {
				//현재가 이전보다 커야한다.
				if(!(arr[i]>arr[j])) continue; 
				//이전 수열 갯수가 크면, 현재 갯수와 바꾼다.
				if(dy[j] > cnt) cnt = dy[j];
			}
			dy[i] = ++cnt;
			ans = Math.max(ans, cnt);
		}
		return ans;
	}
}



/*
8
5 3 7 8 6 2 9 4

4


*/