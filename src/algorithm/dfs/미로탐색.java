package algorithm.dfs;

import java.util.Scanner;

public class 미로탐색 {
	static int[] dx={-1, 0, 1, 0};
	static int[] dy={0, 1, 0, -1};
	static int[][] board;
	static int answer=0;
	
	public static void main(String[] args){
		미로탐색 T = new 미로탐색();
		Scanner kb = new Scanner(System.in);
		board=new int[8][8];
		for(int i=1; i<=7; i++){
			for(int j=1; j<=7; j++){
				board[i][j]=kb.nextInt();
			}
		}
		board[1][1]=1;
		T.DFS2(1, 1);
		System.out.print(answer);
	}

	private void DFS(int x, int y) {
		if(x==7 && y==7) answer++;
		else {
			for (int i = 0; i < dy.length; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx>=1 && nx <=7 && ny>=1 && ny <=7 && board[nx][ny]==0) {
					board[nx][ny]= 1;
					DFS(nx,ny);
					board[nx][ny]=0;
				}
			}
		}
	}
	void DFS2(int x, int y) {
		if(x==7&&y==7)answer++;
		else {
			for (int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>=1 && nx<=7 && ny>=1 && ny<=7 && board[nx][ny]==0) {
					board[nx][ny]=1;
					DFS2(nx,ny);
					board[nx][ny]=0;
				}
			}
		}
		
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