package algorithm.sortsearching.practise;

import java.util.Arrays;
import java.util.Random;

public class 뮤직비디오3 {
	public static void main(String[] args) {
		int[] arr = new Random().ints(20, 1, 6).toArray();
		int musicLen = 20;
		int answer = 0;
		
		Arrays.sort(arr);
		int lt = arr[arr.length-1];
		int rt = Arrays.stream(arr).sum();
		
		while(lt<= rt) {
			int mid = (lt+rt)/2;
			if(count(arr,mid)<=musicLen) {
				answer = mid;
				rt = mid -1;
			}else {
				lt = mid +1;
				
			}
		}
		System.out.println(answer);
	}

	private static int count(int[] arr, int mid) {
		int cnt = 1;
		int sum = 0;
		
		for(int song : arr) {
			if(sum > mid) {
				sum = song;
				cnt++;
			}else {
				sum+=song;
			}
		}
		
		return cnt;
	}
}
