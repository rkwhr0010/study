package algorithm.dynamicprogramming.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class 최대부분증가수열연습 {
	static int dy[];
	
 	public static void main(String[] args) {
 		//수열
		int[] arr = {5, 3, 7, 8, 6, 2, 9, 4};
		dy = new int[arr.length];
		
		solution(arr);
		
	}
	static int solution(int[] arr) {
		int answer = 0;
		dy = new int[arr.length];
		dy[0] = 1;
		//수열 순회
		for(int i=1;i<arr.length;i++) {
			//수열 수 저장
			int max = 0;
			//현재 수열 이전 값 중 작은 값있는지 탐색
			for(int j=i;j>=0;j--) {
				//현재 수열보다 작은 값 있으면, 거기에 저장된 수열 횟수 저장
				if(arr[i]>arr[j]) {
					max = Math.max(max, dy[j]);
				}
			}
			//구해진 수열 수에 +1
			dy[i] = max+1;
			//최종적으로 가장 횟수 많은 부분 수열을 구함
			answer = Math.max(answer, dy[i]);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(dy));
		return answer;
	}
	
	
	
	static int sol(int[] arr) {
		
		dy[0] = 1;
		
		for(int i=1;i<arr.length;i++) {
			int max = 0;
			for(int j=i-1;j>=0;j--) {
				if(arr[i]>arr[j] && dy[j]>max) {
					max = dy[j];
				}
			}
			dy[i] = max+1;
		}
		return Arrays.stream(dy)
				.max()
				.getAsInt();
	}
	
	static <T extends Comparable<T> > int sol2(List<T> arr, BiPredicate<T, T> predi) {
		
		
		for(int i=1;i<arr.size();i++) {
			int max = 0;
			for(int j=i-1;j>=0;j--) {
				if(predi.test(arr.get(i), arr.get(j)) && dy[j]>max) {
					max = dy[j];
				}
			}
			dy[i] = max+1;
		}
		return Arrays.stream(dy)
				.max()
				.getAsInt();
	}
	
	
}


