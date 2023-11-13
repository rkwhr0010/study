package algorithm.sortsearching;

import java.util.Arrays;
import java.util.Scanner;

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

이분검색
임의의 N개의 숫자가 입력으로 주어집니다. N개의 수를 오름차순으로 정렬한 다음 N개의 수
중 한 개의 수인 M이 주어지면 이분검색으로 M이 정렬된 상태에서 몇 번째에 있는지 구하는
프로그램을 작성하세요. 단 중복값은 존재하지 않습니다.
▣ 입력설명
첫 줄에 한 줄에 자연수 N(3<=N<=1,000,000)과 M이 주어집니다.
두 번째 줄에 N개의 수가 공백을 사이에 두고 주어집니다.
▣ 출력설명
첫 줄에 정렬 후 M의 값의 위치 번호를 출력한다.
▣ 입력예제 1
8 32
23 87 65 12 57 32 99 81
▣ 출력예제 1
3
 */
public class 이분검색 {
	static int[] numberArr;
	static int target;
	
	static void input() {
		Scanner sc = new Scanner(System.in);

		int length = sc.nextInt();
		target = sc.nextInt();
		numberArr = new int[length];

		for (int i = 0; i < length; i++) {
			numberArr[i] = sc.nextInt();
		}

		sc.close();
	}

	public static void main(String[] args) {
		input();
		System.out.println(new BiSearch(numberArr, target).search());
	}

	static class BiSearch {
		private final int[] numberArr;
		private final int target;
		private int lt;
		private int rt;
		private int mid;

		private BiSearch(int[] numberArr, int target) {
			//이분검색은 반드시 정렬되야 동작한다.
			Arrays.sort(numberArr);

			this.numberArr = numberArr;
			this.target = target;
			this.lt = 0;
			this.rt = numberArr.length - 1;
			this.mid = rt >> 1;
		}

		private int search() {
			switch (Integer.compare(numberArr[mid], target)) {
				case -1:
					lt = mid + 1;
					return search();
				case 1:
					rt = mid - 1;
					return search();
				default:
					return mid;
			}
		}
	}

	
	
	private static class BinarySearch {
		private final int[] arr ;
		private final int target ;
		
		private BinarySearch(int[] arr, int target) {
			this.arr = arr;
			this.target = target;
		}
		
		public int binarySearch() {
			Arrays.sort(arr);//정렬 필수
			int lt = 0 ;
			int rt = arr.length - 1;
			
			while(lt <= rt) {
				int mid = lt + rt >> 1;
			
				switch (compare(mid)) {
					case 0:
						return mid + 1;
					case 1:
						rt = mid - 1;
						break;
					case -1:
						lt = mid + 1;
						break;
				}
			}
			return -1;
		}
		int sol() {
			Arrays.sort(arr);
			
			int lt = 0;
			int rt = arr.length + 1;
			
			while(lt <= rt) {
				int mid = lt + rt >> 1;
				
				//같으면 0 , 작으면 -1, 크면 1
				switch (Integer.compare(arr[mid], target)) {
				case 0: //같다
					return mid + 1;
				case 1: //크다
					rt = mid - 1;
					break;
				case -1://작다
					lt = mid + 1;
					break;
				}
			}
			return -1;
		}
		
		
		private int compare(int mid) {
			return Integer.compare(arr[mid], target);
		}
	}
	
}
