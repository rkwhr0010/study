package algorithm.dfs.pratise;

public class 부분집합연습 {
	static int len;
	static int[] chk;
	
	public static void main(String[] args) {
		len = 5;
		chk = new int[len+1];//index 0 자리 사용하기 싫음
		DFS(1);
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
		if(lv == len+1) {
			for (int i = 0; i <= len; i++) {
				if(chk[i]==1) System.out.print(i+" ");
			}
			System.out.println();
		}else {
			chk[lv] = 1;
			DFS2(lv+1);
			chk[lv] = 0;
			DFS2(lv+1);
		}
	}
	
	
}
