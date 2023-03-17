package algorithm.sortsearching.practise;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

import algorithm.sortsearching.RandomUtils;

public class 뮤직비디오 {
	public static void main(String[] args) {
		int[] arr = RandomUtils.뮤직비디오();
		int album = 5;
		//앨범 4장에 균등하게 노래 균등하게 분배하면 최소 몇분인가?
		
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
			if(count(arr,minute) <= album ) {
				answer = minute;
				rt = minute -1;
			}else {
				lt = minute+1;
			}
		}
		System.out.println(statistics);
		System.out.println(answer);
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

