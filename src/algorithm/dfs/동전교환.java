package algorithm.dfs;

import java.util.Arrays;
import java.util.Comparator;

//가장 적은 동전 수로 교환
public class 동전교환 {
	static Integer[] kind = {1, 8,  20, 25,50};
	static Integer money = 129;
	static Integer answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Arrays.sort(kind, Comparator.<Integer>reverseOrder());
		DFS(0,0);
		System.out.println(answer);
	}

	private static void DFS(int cnt, int sum) {
		if(sum> money || cnt > answer ) ;
		else if(sum == money) {
			answer = Math.min(cnt, answer);
		}
		else {
			for (int i = 0; i < kind.length; i++) {
				DFS(cnt+1, sum+kind[i]);
				
			}
		}
		
	}
}
