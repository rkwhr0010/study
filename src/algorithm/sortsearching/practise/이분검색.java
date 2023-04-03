package algorithm.sortsearching.practise;

import java.util.Arrays;

import algorithm.sortsearching.RandomUtils;

public class 이분검색 {
	public static void main(String[] args) {
		int[] arr = RandomUtils.이분검색();
		int target = arr[10];
		
		binarySearch2(arr, target);
		
	}
	
	static int binarySearch2(int[] arr, int target) {
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		int lt=0;
		int rt=arr.length-1;
		while(lt<=rt) {
			int mid = (lt+rt)/2;
			if(arr[mid]==target) {
				System.out.println(mid + "  "+target);
				return mid;
			} else if (arr[mid] > target) {
				rt = mid-1;
			} else {
				lt = mid+1;
			}
		}
		
		
		return -1;
	}
	static int binarySearch(int[] arr, int target) {
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
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
