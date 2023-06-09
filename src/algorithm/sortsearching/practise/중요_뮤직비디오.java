package algorithm.sortsearching.practise;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import algorithm.sortsearching.RandomUtils;
/*
파라매트릭 서치는 조합 최적화를 위한 알고리즘의 설계 및 분석에서 
결정 알고리즘을 최적화 알고리즘으로 변환하기 위해 Nimrod Megiddo가 발명한 기술입니다. 
계산 기하학에서 최적화 문제를 해결하는 데 자주 사용됩니다.

파라매트릭 서치는 매개변수 값을 조정하여 최적화 문제를 해결합니다. 
매개변수 값을 조정하는 방법은 다음과 같습니다.

1. 매개변수 값의 범위를 정의합니다.
2. 매개변수 값을 범위 내에서 순차적으로 탐색합니다.
3. 매개변수 값을 탐색하면서 최적의 값을 찾습니다. (핵심)

파라매트릭 서치는 결정 알고리즘을 최적화 알고리즘으로 변환하여 최적화 문제를 해결하는 데 효율적입니다.
 */
public class 중요_뮤직비디오 {
	public static void main(String[] args) {
		int[] arr = RandomUtils.뮤직비디오();
		int albumCnt = 5; // 5개 앨범에 담을려면 최적의 곡 수
		
		System.out.println(Arrays.toString(arr));
		
		IntSummaryStatistics statistics = Arrays.stream(arr).summaryStatistics();
		//하나의 앨범에 최소한 가장 긴 길이의 노래 한곡은 들어가야 한다.
		int lt = statistics.getMax();
		//하나의 앨범에 최대한으로 들어갈 수 있다면 모든 곡이 들어갈 것이다.
		int rt = (int) statistics.getSum();
		int answer = 0;
		
		while(lt<=rt) {
			int minute = (lt+rt)/2;
			
			//앨범 개수와 같거나 작다 부등호를 사용한 이유는 일단 답이 가능하다는 것
			if(count(arr,minute) <= albumCnt ) {
				answer = minute;
				rt = minute -1;
			}else {
				lt = minute+1;
			}
		}
		System.out.println(statistics);
		System.out.println(answer);
		
		int[] arr2 = arr.clone();
		
	}
	
	private static class ParametricSearch{
		int search(int[] arr, int albumCnt) {
			IntSummaryStatistics statistics = Arrays.stream(arr).summaryStatistics();
			int result = -1;
			//앨범 하나에 들어갈 최소 노래길이은 가장 긴 곡 한개다.
			int lt = statistics.getMax();
			//앨범 하나에 들어갈 최대 노래길이은 모든 곡 합이다.
			int rt = (int) statistics.getSum();
			
			while(lt<=rt) {
				//현 시점 최적의 노래 길이
				int mid = (lt+rt)/2;
				if(albumCount(arr, mid) <= albumCnt ) result = mid;
				
			}
			
			
			return result;
		}
		
		int albumCount(int[] arr, int mid) {
			int result = -1;
			
			return result;
		}
		
	}
	
	
	
	
	
	
	//앨범 갯수 카운트
	private static int count(int[] arr, int minute) {
		int cnt = 1; //일단 하나의 앨범으로 시작
		int sum = 0;
		
		for(int song : arr) {
			sum+= song;
			if(sum> minute) {
				sum = song;
				cnt++;
			}
		}
		System.out.println("cnt : " + cnt +" sum : " + sum);
		return cnt;
	}
}

