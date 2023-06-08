package algorithm.sortsearching.practise;

import java.util.Arrays;

import algorithm.sortsearching.RandomUtils;

public class 이분검색연습 {
	public static void main(String[] args) {
		int[] arr = RandomUtils.이분검색();
		int target = arr[10];
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(target);
		System.out.println(binarySearch2(arr, target));
		System.out.println(bi(arr, target));
		
	}
	
	static int binarySearch2(int[] arr, int target) {
		Arrays.sort(arr);
		int lt = 0;
		int rt = arr.length-1;
		
		while(lt<=rt) {
			int mid = (lt+rt)/2;
			if(arr[mid]==target) return mid;
			else if(arr[mid]<target) lt = mid+1;
			else rt = mid-1;
		}
		return -1;
	}
	static int bi(int[] arr , int target) {
		//정렬 필수
		Arrays.sort(arr);
		int lt = 0;
		int rt = arr.length-1;
		while(!(lt>rt)) {
			int mid = (lt+rt)/2;
			if(arr[mid] == target) return mid;
			if(arr[mid]>target) rt = mid-1;
			else lt = mid +1;
		}
		return -1;
	}
	
	static int binarySearch(int[] arr, int target) {
		Arrays.sort(arr);
		
		int lt = 0;
		int rt = arr.length-1;
		while(lt<=rt) {
			int mid = (lt+rt)/2;
			if(arr[mid]==target) {
				System.out.println(mid + " "+target);
				return mid;
			}else if(arr[mid]> target) rt = mid-1;
			else lt = mid +1;
		}
		return -1;
	}
	
	
	
}
