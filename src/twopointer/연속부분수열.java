package twopointer;

import java.util.Arrays;
import java.util.Random;

public class 연속부분수열 {
	public static void main(String[] args) {
		int goal = 6;
		
		int[] array = new Random().ints(20, 1, 4).toArray();
		
		System.out.println(Arrays.toString(array));
		
		int answer = 0;
		int sum = 0;
		//값이 변경되면 그 즉시, 같은지 비교를 해야 한다.
		for (int lt = 0,rt=0; rt < array.length; rt++) {
			sum+= array[rt];
			if(sum == goal) answer++;
			//한 번만 빼서는 부족할 수 있다.
			while(sum>=goal) {
				sum-=array[lt++];
				if(sum == goal) answer++;
			}
		}
		
		System.out.println(answer);
	}
}
