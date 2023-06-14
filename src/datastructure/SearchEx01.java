package datastructure;

public class SearchEx01 {
	
	//배열 요소들이 정렬되지 않았을 때 나쁘지 않은 탐색방법 시간복잡도 n
	static int linearSearch(int[] arr, int target) {
		for(int i = 0;i<arr.length;i++) {
			if(arr[i]==target) return i;
		}
		return -1;
	}
	
	//반드시 배열이 정렬되어 있어야 함, log n 시간복잡도
	static int binarySearch(int[] arr , int target) {
		int lt = 0;
		int rt = arr.length-1;
		
		while(lt<=rt) {
			final int mid = (lt+rt)/2;
			if(arr[mid] == target) return mid;
			else if (arr[mid] > target) rt = mid-1;
			else lt = mid + 1;
		}
		return -1;
	}
	
	
}
