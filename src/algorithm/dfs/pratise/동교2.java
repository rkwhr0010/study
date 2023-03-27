package algorithm.dfs.pratise;

import java.util.Arrays;
import java.util.Comparator;

public class 동교2 {
	static Integer[] arr = {1,2,5};
	static int money = 15;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Arrays.sort(arr, Comparator.reverseOrder());
		DFS(0,0);
		System.out.println(answer);
	}

	private static void DFS(int cnt, int sum) {
		if(sum>money);
		else if(cnt>answer);
		else if(sum == money) {
			answer= Integer.min(cnt, answer);
		}else {
			for(int m : arr) {
				DFS(cnt+1,sum+m);
			}
		}
	}
}
