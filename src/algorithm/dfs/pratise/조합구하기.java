package algorithm.dfs.pratise;

import java.util.Arrays;

public class 조합구하기 {
	static int n  = 4;
	static int m  = 2;
	
	static int[] com = new int[m];
	
	public static void main(String[] args) {
		DFS(0,1);
	}

	private static void DFS(int lv, int s) {
		if(lv==m) {
			System.out.println(Arrays.toString(com));
		}else {
			for (int i = s; i <= n; i++) {
				com[lv] = i;
				DFS(lv+1,i+1);
			}
		}
				
		
	}
}
