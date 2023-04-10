package algorithm.dfs.pratise;

public class 조합수메모연습 {
	static int n = 6;
	static int r = 4;
	static int[][] dy = new int[n+1][r+1];
	
	public static void main(String[] args) {
		System.out.println(DFS5(n, r));
	}
	static int DFS2(int n, int r) {
		if(n==r|| r==0) return 1;
		else if(dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS2(n-1, r-1) + DFS2(n-1, r);
	}
	static int DFS3(int n, int r) {
		if(n==r || r==0) return 1;
		else if(dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS3(n-1,r-1)+ DFS3(n-1,r);
		
	}

	private static int DFS(int n, int r) {
		if(n == r || r == 0) return 1;
		else if (dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS(n-1,r-1)+DFS(n-1,r);
	}
	
	static int DFS4(int n,int r) {
		if(n==r || r == 0) return 1;
		else if (dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS4(n-1,r-1)+ DFS(n-1,r);
		
	}
	static int DFS5 (int n, int r) {
		if(n==r || r == 0) return 1;
		else if(dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = DFS5(n-1,r-1) + DFS5(n-1,r);
	}
	
	
}
