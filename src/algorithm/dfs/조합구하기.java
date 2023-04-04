package algorithm.dfs;

import java.util.Arrays;

public class 조합구하기 {
	static int n  = 6;
	static int m  = 4;
	
	static int[] combi = new int[m];
	public static void main(String[] args) {
		DFS(0,1);
	}
	private static void DFS(int lv, int s) {
		if(lv == m) {
			System.out.println(Arrays.toString(combi));
		}else {
			for (int i = s; i <= n; i++) {
				combi[lv] = i;
				DFS(lv+1,i+1);
			}
		}
		
	}
}
