package algorithm.sortsearching.practise;

import java.util.Arrays;

import algorithm.sortsearching.RandomUtils;
/*
이분검색은 자료구조의 한 종류로, 자료의 중간값을 기준으로 자료의 탐색을 진행하는 검색 알고리즘입니다. 
이분검색은 자료가 정렬되어 있을 때만 사용할 수 있는 알고리즘입니다.

이분검색의 동작 과정은 다음과 같습니다.

1. 자료의 중간값을 찾습니다.
2. 중간값과 찾고자 하는 값을 비교합니다.
3. 중간값이 찾고자 하는 값보다 크면, 중간값의 왼쪽 부분을 탐색합니다.
4. 중간값이 찾고자 하는 값보다 작으면, 중간값의 오른쪽 부분을 탐색합니다.
5. 자료의 모든 부분을 탐색할 때까지 2~4번의 과정을 반복합니다.

이분검색은 자료의 크기가 n일 때, 최악의 경우에도 O(log n)의 시간 복잡도를 가지기 때문에, 
자료의 크기가 큰 경우에도 효율적으로 탐색할 수 있습니다.
 */
public class 중요_이분검색연습 {
	public static void main(String[] args) {
		int[] arr = RandomUtils.이분검색();
		int target = arr[10];
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(target);
		System.out.println(binarySearch2(arr, target));
		System.out.println(bi2(arr, target));
		
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
	
	static int bi2(int[] arr, int target) {
		int noSearch = -1;
		//반드시 정렬된 상태
		Arrays.sort(arr);
		//최소, 최대 인덱스 정의
		int lt = 0;
		int rt = arr.length-1;
		
		//좌측 인덱스가 더 크면 종료
		while(!(lt>rt)) {
			//탐색할 위치
			int mid = (lt+rt) /2;
			//찾음
			if(arr[mid] == target) return mid;
			//못찾음
			if(arr[mid]>lt) lt = mid +1;
			else rt = mid -1;
		}
		return noSearch;
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
