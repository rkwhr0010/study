package algorithm.dfs;

public class 조합수메모이 {
	static int n = 33;
	static int r = 19;
	static int[][] dy = new int[n+1][r+1];
	
	public static void main(String[] args) {
		System.out.println(DFS(n, r));
	}

	private static int DFS(int n, int r) {
		if(n==r||r==0) return 1;
		else if(dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS(n-1,r-1)+ DFS(n-1,r);
	}
	
}
