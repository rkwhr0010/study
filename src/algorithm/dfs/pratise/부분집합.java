package algorithm.dfs.pratise;

public class 부분집합 {
	static int len;
	static boolean[] chk;
	
	public static void main(String[] args) {
		len = 10;
		chk = new boolean[len+1];//index 0 자리 사용하기 싫음
		DFS(1);
	}

	private static void DFS(int lv) {
		if(lv>len) {
			for(int i=1;i<=len;i++) if(chk[i])System.out.print(i+" ");
			System.out.println();
		}else {
			chk[lv] = true;//경우의 수 true
			DFS(lv+1);
			chk[lv] = false;//경우의 수 false
			DFS(lv+1);
			
		}
	}
}
