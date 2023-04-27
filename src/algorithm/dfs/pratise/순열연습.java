package algorithm.dfs.pratise;

import java.util.Arrays;

public class 순열연습 {
	static int[] arr = {3,6,9,12};
	static int chk[] = new int[arr.length];
	
	static int size = 2;
	static int[] pm = new int[size];
	
	public static void main(String[] args) {
		DFS4(0);
	}

	static void DFS3(int lv) {
		if(lv == size) {
			System.out.println(Arrays.toString(pm));
		}else {
			for (int i = 0; i < arr.length; i++) {
				if(chk[i]==0) {
					chk[i] = 1;
					pm[lv] = arr[i];
					DFS3(lv+1);
					chk[i] = 0;
				}
			}
		}
	}
	static void DFS4(int lv) {
		if(lv == size) {
			System.out.println(Arrays.toString(pm));
		}else {
			for (int i = 0; i < arr.length; i++) {
				if(chk[i] == 0) {
					chk[i] = 1;
					pm[lv] = arr[i];
					DFS4(lv+1);
					chk[i] = 0;
				}
			}
		}
	}
	
	
}
