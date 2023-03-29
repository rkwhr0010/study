package algorithm.dfs.pratise;

public class 조수메 {
	static int n = 33;
	static int r = 19;
	static int[][] dy = new int[n+1][r+1];
	
	public static void main(String[] args) {
		System.out.println(DFS2(n,r));
	}

	private static int DFS(int n2, int r2) {
		if(n2==r2 || r2 == 0) return 1;
		int tmp = dy[n2][r2];
		if(tmp > 0) return tmp;
		else return dy[n2][r2] = DFS(n2-1,r2-1) + DFS(n2-1,r2);
	}
	
	private static int DFS2(int n, int r ) {
		if(n==r||r==0) return 1;
		else if(dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS(n-1,r-1)+ DFS(n-1,r);
	}
	
	
}
