package algorithm.sortsearching.practise;

import java.util.Arrays;

import algorithm.sortsearching.RandomUtils;

public class 이분검색 {
	public static void main(String[] args) {
		int[] arr = RandomUtils.이분검색();
		
		int target = arr[10];
		
		Arrays.sort(arr);
		
		int lt = 0;
		int rt = arr.length-1;
		System.err.println(Arrays.toString(arr));
		while(lt<=rt) {
			int mid = (lt+rt)/2;
			if(arr[mid]==target) {
				System.out.println(mid + " "+target);
				return;
			}else if(arr[mid]> target) rt = mid-1;
			else lt = mid +1;
		}
		
	}
}
