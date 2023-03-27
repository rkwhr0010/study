package algorithm.dfs.pratise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class 동전 {
	static Integer[] kind = {1, 8,  20, 25,50};
	static Integer money = 129;
	static Integer answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Arrays.sort(kind, Comparator.reverseOrder());
		DFS(0,0);
		System.out.println(answer);
	}

	private static void DFS(Integer cnt, Integer sum) {
		if(sum > money || cnt > answer) ;
		else if(Objects.equals(sum, money)) {
			answer = Integer.min(answer, cnt);
		}
		else {
			for(Integer coin : kind) {
				DFS(cnt+1,sum+coin);
			}
		}
	}
	
}
