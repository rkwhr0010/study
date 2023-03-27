package algorithm.dfs.pratise;

import java.util.Arrays;

public class 순열구 {
	static int[] arr = {3,6,9};
	static int size = 2;
	static int[] chk = new int[arr.length];
	static int[] box = new int[size];
	
	public static void main(String[] args) {
		DFS(0);
	}

	private static void DFS(int lv) {
		if(lv==box.length) {
			System.out.println(Arrays.toString(box));
		}else {
			for (int i = 0; i < arr.length; i++) {
				if(chk[i]==0) {
					chk[i] = 1;
					
					box[lv] = arr[i];
					DFS(lv+1);
					
					chk[i] = 0;
				}
			}
		}
	}
	
}
