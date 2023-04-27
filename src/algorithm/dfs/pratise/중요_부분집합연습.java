package algorithm.dfs.pratise;

import java.util.Arrays;

/*
 * 주어진 요소 수에서 최대 조합수
 */
public class 중요_부분집합연습 {
	static int len;
	static int[] chk;
	static int cnt = 0;
	
	public static void main(String[] args) {
		len = 3;
		chk = new int[len+1];//index 0 자리 사용하기 싫음
		DFS2(1);
		
		System.out.println("\n" +cnt);
	}
	private static void DFS(int lv) {
		if(lv == len+1) {
			for (int i = 1; i <= len; i++) {
				if(chk[i]== 1) {
					System.out.print(i+" ");
				}
			}
			System.out.println();
		}else {
			chk[lv] = 1;
			DFS(lv+1);
			chk[lv] = 0;
			DFS(lv+1);
		}
	}
	
	static void DFS2(int lv) {
		if(lv == len +1) {
			chkPrint();
		}else {
			chk[lv] = 1;
			DFS2(lv+1);
			chk[lv] = 0;
			DFS2(lv+1);
		}
	}
	
	private static void chkPrint() {
		System.out.print(Arrays.toString(chk) + "   ");
		for (int i = 1; i <= len; i++) {
			if(chk[i]==1) System.out.print(i+" ");
		}
		System.out.println();
	}
}
