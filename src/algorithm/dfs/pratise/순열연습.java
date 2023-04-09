package algorithm.dfs.pratise;

import java.util.Arrays;

public class 순열연습 {
	static int[] arr = {3,6,9,12};
	static int chk[] = new int[arr.length];
	
	static int size = 2;
	static int[] pm = new int[size];
	
	public static void main(String[] args) {
		DFS2(0);
	}
	
	static void DFS(int lv) {
		if(lv == size) {
			System.out.println(Arrays.toString(pm));
		}else {
			for(int i=0;i<arr.length;i++) {
				if(chk[lv] == 0 ) {
					chk[lv] = 1;
					pm[lv] = arr[i];
					DFS(lv+1);
					chk[lv] = 0;
				}
			}
		}
	}
	
	static void DFS2(int lv) {
		if(lv == size) {
			System.out.println(Arrays.toString(pm));
		} else {
			for(int i=0;i<arr.length;i++) {
				if(chk[lv] == 0) {
					chk[lv] = 1;
					pm[lv] = arr[i];
					DFS2(lv+1);
					chk[lv] = 0;
				}
			}
		}
	}
	
	
	
}
