package algorithm.dfs;

import java.util.Arrays;

public class 순열중복 {
	static int size = 3;
	static int max = 5;
	static int[] arr = new int[size];
//	static int[] chk = new int[size];
	
	
	public static void main(String[] args) {
		DFS(0);
	}
	private static void DFS(int lv) {
		if(lv==arr.length) {
			System.out.println(Arrays.toString(arr));
		}else {
			for (int i = 1; i <= max; i++) {
				arr[lv] = i;
				DFS(lv+1);
			}
		}
	}
	
	
}
