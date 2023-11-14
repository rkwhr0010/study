package algorithm.sortsearching;

public class 결정알고리즘 {
	void execute() {
		int answer = 0;
		// 구하고자하는 경우의 수 중 최소값
		int lt = 0;
		// 구하고자하는 경우의 수 중 최대값
		int rt = 0;
		// 탐색할 배열
		int[] arr = new int[] {};
		// 목표 값
		int target = 0;
		
		//필수 포맷
		while (lt <= rt) {
			// 접어서 나아간다. long N
			int mid = lt + rt >> 1;
			
			if (logic(arr[mid]) <= target) {
				answer = mid; // 일단 조건만 충족하면 답
				// 더 나은 답을 계속 찾는다.
				rt = mid - 1;
			} else {
				lt = mid + 1;
			}
		}
		
	}
	//얻고자하는 값을 구하는 로직
	private int logic(int mid) {
		return mid;
	}
	
}
