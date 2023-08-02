package algorithm.etc;

import java.util.ArrayList;
import java.util.List;

public class 에라토스테네스체_소수합계구하기 {
	public static void main(String[] args) {
		sol(100);
	}
	
	static List<Integer> list = new ArrayList<>();
	static int end;
	static int[] chkArr;
	
	static void sol(int end) {
		에라토스테네스체_소수합계구하기.end = end;
		chkArr = new int[end + 1];
		
		for(int i = 2; i <= end; i++) {
			if(isPrime(i)) {
				list.add(i);
				checkArr(i);
			}
			
		}
		System.out.println(list);
		System.out.println(list.stream().reduce(0, Integer::sum));
		
	}

	public static boolean isPrime(int i) {
		return chkArr[i] == 0;
	}

	public static void checkArr(int i) {
		for(int j = i; j <= end; j += i) {
			chkArr[j] = 1;
		}
	}
	
}
