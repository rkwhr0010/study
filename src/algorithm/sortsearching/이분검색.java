package algorithm.sortsearching;

import java.util.Arrays;
import java.util.Random;

public class 이분검색 {
	
	public static void main(String[] args) {
		int[] arr = new Random().ints(20, 1, 100).distinct().toArray();
		
		int value = arr[10];
		System.out.println(value);
		//이분검색은 정렬이 필수
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		int lt = 0;
		int rt = arr.length-1;
		
		while(lt<=rt) {
			//중간 절삭
			int mid = (lt+rt)/2;
			if(arr[mid]==value) {
				System.out.println(mid);
				return;
			}
			if(arr[mid]<value) lt = mid+1;
			else rt = mid-1;
		}
		
	}
	
}
