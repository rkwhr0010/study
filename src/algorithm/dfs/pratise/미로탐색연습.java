package algorithm.dfs.pratise;

public class 미로탐색연습 {
	static int[] dx={-1, 0, 1, 0};
	static int[] dy={0, 1, 0, -1};
	static int[][] board;
	static int answer=0;
	
	public static void main(String[] args){
		board = new int[][]
				{{0, 0, 0, 0, 0, 0, 0, 0}
				,{0, 0, 0, 0, 0, 0, 0, 0}
				,{0, 0, 1, 1, 1, 1, 1, 0}
				,{0, 0, 0, 0, 1, 0, 0, 0}
				,{0, 1, 1, 0, 1, 0, 1, 1}
				,{0, 1, 1, 0, 0, 0, 0, 1}
				,{0, 1, 1, 0, 1, 1, 0, 0}
				,{0, 1, 0, 0, 0, 0, 0, 0} };
		
		board[1][1]=1;
		DFS(1, 1);
		System.out.print(answer);
	}

	static void DFS2(int x, int y) {
		if(x == 7 && y == 7) answer ++;
		else {
			for (int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(between(nx, 1, 7) && between(ny, 1, 7) && board[nx][ny] == 0) {
					board[nx][ny] = 1;
					DFS2(nx,ny);
					board[nx][ny] = 0;
					
				}
			}
		}
	}
	
	static void DFS3(int x, int y) {
		if(x==7 && y==7) answer++;
		else {
			for(int i = 0; i< dx.length;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(between(nx, 1, 7) && between(ny, 1, 7) && board[nx][ny]==0) {
					DFS3(nx,ny);
				}
			}
		}
		
	}
	
	private static  void DFS(int x, int y) {
		if(x==7 && y ==7) answer++;
		else {
			for (int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(between(nx, 1, 7) && between(ny, 1, 7) && board[nx][ny]==0 ) {
					board[nx][ny]= 1;
					DFS(nx,ny);
					board[nx][ny]= 0;
				}
			}
		}
	}
	static boolean between(int value,int from, int to) {
		return value>=from && value <= to;
	}
	
}
/**
0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 0 0 0 1
1 1 0 1 1 0 0
1 0 0 0 0 0 0 
8
*/