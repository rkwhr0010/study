package algorithm.dynamicprogramming.practise;

import java.util.Arrays;

public class 중요_최대부분증가수열연습 {
	static int dy[];
	static int dp[];
	static int dy2[];
	
 	public static void main(String[] args) {
 		//수열
		int[] arr = {5, 3, 7, 8, 6, 2, 9, 4};
		dy = new int[arr.length];
		dy2 = dy.clone();
		dp = dy.clone();
		//4
		System.out.println(sol3(arr));
		System.out.println(so1(arr));
		
	}
	public int solution(int[] arr){
		int answer=0;
		dy=new int[arr.length];
		dy[0]=1;
		for(int i=1; i<arr.length; i++){
			int max=0;
			for(int j=i-1; j>=0; j--){
				if(arr[j]<arr[i] && dy[j]>max) max=dy[j];
			}
			dy[i]=max+1;
			answer=Math.max(answer, dy[i]);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(dy));
		return answer;
	}
	static int sol3(int[] arr) {
		int ans = 0;
		dy = new int[arr.length];
		dy[0] = 1;
		for(int i=1/*1부터*/;i<arr.length;i++) {
			int max = 0;
			for(int j=i-1/*이전탐색*/;j>=0;j--) {
				if(arr[i]>arr[j]//최대는 현재가 항상커야함
						&& dy[j] > max // 이전 값들 중 최대값 찾기(부분증가수열)
						) {
					max = dy[j];
				}
			}
			dy[i] = max+1; // 카운트 증가;
			ans = Math.max(ans,dy[i]);
		}
		
		return ans;
	}
	
	static int so(int[] arr) {
		//결과
		int answer = 0;
		dy2[0] = 1; //첫 시작은 항상 1일수밖에 없으니 초기화 
		//최대 부분 증가 수열 순회용
		for(int i=1;i<arr.length;i++) {
			//이전 탐색 중 가장 큰 값저장용 (동적계획핵심)
			int max = 0;
			//현 지점 이전탐색
			for(int j=i-1;j>=0;j--) {
				//최대 부분 증가 수열이니 현 지점보다 작아야한다.
				if(arr[i]<arr[j]) continue;
				//현 지점보다 작은 수중 가장 큰 수열 횟수 구함(동적계획핵심)
				max = Math.max(max,dy2[j]);
			}
			dy2[i] = max+1;//횟수 증가.
			//arr[i] 숫자 부분증가수열 횟수가 가장 크냐
			answer = Math.max(answer,dy2[i]);
		}
		return answer;
	}
	
	static int so1(int[] arr) {
		//최대 횟수 저장 
		int result = 0;
		dp[0] = 1;//시작은 항상 1,이로인해 출발은 1부터
		//최대 부분 증가 수열 탐색
		for(int i=1;i<arr.length;i++) {
			//현 지점 이전 중에 가장 큰 부분증가수열 횟수 저장공간
			int max = 0; //동적계획
			//나 이전 탐색
			for(int j=i-1;j>=0;j--) {
				//나보다 더큰 수는 대상이 아니다.
				if(arr[i]<arr[j]) continue;
				max = Math.max(max, dy[j]);
			}
			//가장 큰 부분증가수열 찾음
			//이제 이때까지 구한 부분증가 수열 중 가장 횟수 많은 값과 비교해 큰 걸로 교체
			result = Math.max(result, max+1);
		}
		
		
		return result;
	}
	
	
}