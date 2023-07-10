package algorithm.sortsearching;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Scanner;

/*
마구간 정하기(결정알고리즘)
N개의 마구간이 수직선상에 있습니다. 각 마구간은 x1, x2, x3, ......, xN의 좌표를 가지며, 마
구간간에 좌표가 중복되는 일은 없습니다.
현수는 C마리의 말을 가지고 있는데, 이 말들은 서로 가까이 있는 것을 좋아하지 않습니다.
각 마구간에는 한 마리의 말만 넣을 수 있고, 가장 가까운 두 말의 거리가 최대가 되게 말을
마구간에 배치하고 싶습니다.
C마리의 말을 N개의 마구간에 배치했을 때 가장 가까운 두 말의 거리가 최대가 되는 그 최대
값을 출력하는 프로그램을 작성하세요.
▣ 입력설명
첫 줄에 자연수 N(3<=N<=200,000)과 C(2<=C<=N)이 공백을 사이에 두고 주어집니다.
둘째 줄에 마구간의 좌표 xi(0<=xi<=1,000,000,000)가 차례로 주어집니다.
▣ 출력설명
첫 줄에 가장 가까운 두 말의 최대 거리를 출력하세요.
▣ 입력예제 1
5 3
1 2 8 4 9
▣ 출력예제 1
3
 */
public class 마구간정하기 {
	static int n; // 마구간 수
	static int c; // 말 수
	static int[] arr;// 마구간
	
	public static void main(String[] args) {
		input();
		
//		System.out.println(solution());
		System.out.println(solution3());
		
	}
	static int solution3() {
		int result = 0;
		//이분 정렬 응용, 결정 알고리즘은 정렬 필수
		Arrays.sort(arr);
		int lt = 1; //말 간 최소 거리는 1
		int rt = arr[n-1];//말간 최대 거리는 최대 마구간 거리
		
		while(lt <= rt) {
			int mid = lt + rt >> 1;
			//현재 값(mid) 거리 간격으로 말을 배치 가능하냐?
			if(count3(mid) >= c) {
				//일단 결정, 이후 최선의 값으로 갱신
				result = mid;
				//가능하니 거리를 벌려본다.
				lt = mid + 1;
			} else {
				//불가능 하니 거리를 좁혀 본다.
				rt = mid - 1;
			}
		}
		
		return result;
	}
	
	static int count3(int val) {
		//일단 말 하나 배치 후 시작
		int cnt = 1;
		int prev = arr[0];
		
		for(int i = 1; i < n; i++) {
			//현재 val 보다 간격이 넓으니 배치 가능 
			if(arr[i] - prev >= val) {
				cnt++;
				prev = arr[i];
			}
		}
		return cnt;
	}
	
	
	
	static int solution2() {
		int result = 0;
		//이분 검색은 정렬이 필수
		Arrays.sort(arr);
		
		//마구간 거리 최소는 1
		int lt = 1;
		//최대는 가장 먼 마구간 거리
		int rt = arr[n-1];
		
		while(lt <= rt) {
			int mid = lt + rt >> 1;
			//마구간 거리에 배치 가능한 말 수 검증
			//mid는 최소이 것보다 넓게 배치가능하냐
			if(count2(mid) >= c) {
				//일단 된다. 점차 최적값을 탐색
				result = mid;
				//최대한 넓게 배치 가능한 값 탐색
				lt = mid + 1;
			} else {
				//안되니 좁게 배치 가능한 값 탐색
				rt = mid - 1;
			}
		}
		return result;
	}
	
	static int count2(int distance) {
		//첫 말은 배치하고 시작
		int cnt = 1;
		int prev = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] - prev >= distance) {
				prev = arr[i];
				cnt++;
			}
		}
		
		
		return cnt;
	}
	
	

	private static int solution() {
		//이분 검색은 정렬이 필수
		Arrays.sort(arr);
		int answer = 0;
		//1이 최소거리
		int lt = 1;
		//가장 큰 마구간 거리가 최대 길이
		int rt = arr[n-1];
		
		while(lt <= rt) {
			//중간 거리
			int mid = lt + rt >> 1;
			
			//중간 거리로 말 개수만 큼 배치가 가능한지 
			if(count(mid) >= c) {
				//가능하니 일단 답으로
				answer = mid;
				//범위 증가
				lt = mid + 1;
			} else {
				//범위 감소
				rt = mid - 1;
			}
		}
		return answer;
	}

	private static int count(int mid) {
		//첫 말 배치하고 시작
		int result = 1;
		int prev = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			//지금 마구간 거리가 이전 배치해둔 마두간 거리가 목표 거리보다 크거나 같은지
			if(arr[i] - prev >= mid) {
				prev = arr[i];
				result++;
			}
		}
		return result;
	}

	private static void input() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		c = sc.nextInt();
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
	}
}
