package algorithm.dfs.pratise;

import java.util.Arrays;

public class 중순열2 {
	static int n = 3;
	static int m = 2;
	static int[] pm = new int[m];
	
	public static void main(String[] args) {
		DFS(0);
	}

	private static void DFS(int lv) {
		if(lv == pm.length) {
			System.out.println(Arrays.toString(pm));
		}else {
			for (int i = 1; i <= n; i++) {
				pm[lv] = i;
				DFS(lv+1);
			}
		}
	}
}
