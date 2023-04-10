package algorithm.dfs.pratise;

import java.util.Arrays;

public class 조합구하기연습 {
	static int size  = 6;
	static int sel  = 4;
	static int cnt = 0;
	static int[] com = new int[sel];
	
	public static void main(String[] args) {
		DFS(0,1);
		System.out.println(cnt);
	}
	
	static void DFS3(int lv, int s) {
		if(lv == sel) {
			cnt++;
			System.out.println(Arrays.toString(com));
		}else {
			for(int i=s;i<=size;i++) {
				com[lv] = i;
				DFS3(lv+1,i+1);
			}
		}
	}
	
	static void DFS(int lv, int s) {
		if(lv == sel) {
			cnt++;
			System.out.println(Arrays.toString(com));
		}else {
			for(int i=s;i<=size;i++) {
				com[lv] = i;
				DFS(lv+1,i+1);
			}
		}
	}
}
