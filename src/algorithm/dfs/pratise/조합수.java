package algorithm.dfs.pratise;

public class 조합수 {
	static int n = 30;
	static int r = 10;
	
	static int[][] dy = new int[n+1][r+1];
	
	public static void main(String[] args) {
		System.out.println(DFS(n,r));
	}

	private static int DFS(int n2, int r2) {
		if(n2 == r2 || r2 == 0) return 1;
		else if(dy[n2][r2]>0) return dy[n2][r2];
		else {
			return dy[n2][r2] = DFS(n2-1,r2-1)+DFS(n2-1,r2);
		}
		
	}
	
}
