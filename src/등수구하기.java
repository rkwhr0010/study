

import java.util.*;
// 핵심은 중복 점수 등수가 왜 문제가 없는
class 등수구하기 {	
		
	public static void main(String[] args){
		등수구하기 T = new 등수구하기();
		int[] scoreArr = {90,75,70,80,80,60,75,95};
		final int LENGTH = scoreArr.length;
		int[] answer = new int[LENGTH];
		int rank = 1;
		
		for(int i=0;i<LENGTH;i++) {
			for(int j=0;j<LENGTH ;j++) {
				//나와 나를 비교하면 참이 안될 것 == 나와 동점은 참이 안될 것
				//결과적으로 동점은 같은 등수를 가지게 된다.
				if(scoreArr[i]<scoreArr[j]) {
					rank++;
				}
			}
			answer[i] = rank;
			rank = 1;
		}
		
		System.out.println(Arrays.toString(answer));
		/*
		int[] array = Arrays.stream(arr)
		      .distinct()
		      .sorted()
		      .toArray();
		
		for(int j=array.length-1;j>=0;j--) {
			for(int i=0; i<n;i++) {
				if(arr[i] == array[j]) {
					answer[i] = rank;
					cnt++;
				}
			}
			rank+= cnt;
			cnt = 0;
		}*/
		
	}
}