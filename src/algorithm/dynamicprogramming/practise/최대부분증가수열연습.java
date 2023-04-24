package algorithm.dynamicprogramming.practise;

import java.util.Arrays;

public class 최대부분증가수열연습 {
	static int dy[];
	
 	public static void main(String[] args) {
 		//수열
		int[] arr = {5, 3, 7, 8, 6, 2, 9, 4};
		dy = new int[arr.length];
		//4
		System.out.println(sol3(arr));
		
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
	static int sol2(int[] arr) {
		int ans = 0;
		dy = new int[arr.length];
		dy[0] = 1; 
		
		for(int i=1;i<arr.length;i++) {
			int max = 0;
			for(int j=i-1;j>=0;j--) {
				if(arr[i]>arr[j]&& dy[j] > max ) {max = dy[j];}
//				if(arr[i]>arr[j]) {	max = Math.max(max, dy[j]);	}
			}
			dy[i] = max+1;
			ans = Math.max(ans, dy[i]);
		}
		return ans;
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
			dy[i] = ++max; // 카운트 증가;
			ans = Math.max(ans,dy[i]);
		}
		
		return ans;
	}
	
	
	
}


