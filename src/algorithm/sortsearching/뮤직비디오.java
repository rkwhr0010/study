package algorithm.sortsearching;

import java.util.Arrays;
import java.util.Random;

public class 뮤직비디오 {
	public static void main(String[] args) {
		int[] arr = new Random().ints(20, 1, 6).toArray();
		int musicLen = 20;
		Arrays.sort(arr);
		
		//최소는 가장큰 한곡
		int lt = Arrays.stream(arr).max().getAsInt();
		//최대는 모든 곡길이 합
		int rt = Arrays.stream(arr).sum();
		
		int answer = 0;
		while(lt<=rt) {
			//중각 곡 길이
			int mid = (lt+rt)/2;
			if(count(arr,mid)<=musicLen) {
				// <= 부등호, 현재로선 최선의 답
				answer = mid;
				rt = mid-1;
			}else {
				lt = mid+1;
			}
			
		}
		System.out.println(answer);
		
		
	}

	private static int count(int[] arr, int capacity) {
		int cnt = 1; 
		int sum = 0;
		for(int x : arr) {
			if(sum+x>capacity) {
				cnt++;
				sum=x;
			}else {
				sum+=x;
			}
		}
		
		return cnt;
	}
}
