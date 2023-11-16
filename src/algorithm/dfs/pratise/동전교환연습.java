package algorithm.dfs.pratise;

import java.util.Arrays;
import java.util.Comparator;

public class 동전교환연습 {
	static Integer[] kind = {1, 8,  20, 25,50};
	static Integer money = 129;
	static Integer answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		DFS2(0,0);
		Arrays.sort(kind, Comparator.<Integer>reverseOrder());
		System.out.println(answer);
	}

	private static void DFS(int cnt, int sum) {
		//경계값
		if(sum>money || cnt > answer)  return ;
		//정답 확인
		if(sum == money) answer = Math.min(cnt, sum);
		else {
			for (int i = 0; i < kind.length; i++) {
				DFS(cnt+1, sum+kind[i]);
			}
		}
	}
	
	private static void DFS2(int cnt, int sum) {
		if(cnt>answer || sum>money) return;
		else if(sum==money) answer = answer > cnt ? cnt : answer;
		else {
			for (int i = 0; i < kind.length; i++) {
				DFS2(cnt+1, Integer.sum(sum, kind[i] ));
			}
		}
	}
	
}
