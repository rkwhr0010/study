package algorithm.bfs.pratise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 토마토연습 {
	static class Point{
		public int x, y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	static int[] dx={-1, 0, 1, 0};
	static int[] dy={0, 1, 0, -1};
	static int[][] board, dis;
	static int n, m;
	
	static void B(Queue<Point> Q) {
		while(!Q.isEmpty()) {
			//현재 위치
			Point now = Q.poll();
			//다음 위치 탐색
			for(int i=0;i<dx.length;i++) {
				//다음 위치 후보
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				//다음위치 유효성
				if(valid(nx,ny) && board[nx][ny] == 0) {
					//방명록 작성
					board[nx][ny] = 1;
					//값 계산 핵심, 현위치 값에 +1
					dis[nx][ny] = dis[now.x][now.y] + 1;
					Q.offer(new Point(nx, ny));
				}
			}
		}
	}
	

	private static boolean valid(int nx, int ny) {
		return nx>=0 && nx<n && ny>=0 && ny<m;
	}
	
	public static void main(String[] args){
		int[][] input = {
				{0, 0, -1, 0, 0, 0},
				{0, 0, 1 ,0 ,-1, 0},
				{0, 0, -1, 0, 0, 0},
				{0, 0, 0 ,0 ,-1, 1}
		};
		board= input.clone();
		n= board.length;
		m= board[0].length;
		dis=new int[n][m];
		
		Queue<Point> queue = init(new LinkedList<>());
		
		B(queue);
		for(int[] arr : dis) {
			System.out.println(Arrays.toString(arr));
		}
		boolean flag=true;
		int answer=Integer.MIN_VALUE;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(board[i][j]==0) flag=false;
			}
		}
		if(flag){
			for(int i=0; i<n; i++){
				for(int j=0; j<m; j++){
					answer=Math.max(answer, dis[i][j]);
				}
			}
			System.out.println(answer);
		}
		else System.out.println(-1);
	}

	private static Queue<Point> init(Queue<Point> Q) {
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				//시작점이 여러 개라 여기서 처리한다.
				if(board[i][j]==1) Q.offer(new Point(i, j));
			}
		}
		return Q;
	}
}
/*
6 4
0 0 -1 0 0 0
0 0 1 0 -1 0
0 0 -1 0 0 0
0 0 0 0 -1 1

4
*/