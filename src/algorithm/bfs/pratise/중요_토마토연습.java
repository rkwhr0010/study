package algorithm.bfs.pratise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;

public class 중요_토마토연습 {
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
	static boolean flag=true;
	
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
	
	static void B2(Queue<Point> Q) {
		while(!Q.isEmpty()) {
			//현재 위치
			Point cur = Q.poll();
			
			for (int i = 0; i < dx.length; i++) {
				//다음 경로
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				//첫 방문 토마토
				if(valid(nx, ny) && board[nx][ny] == 0) {
					//방명록 작성
					board[nx][ny] = 1;
					dis[nx][ny] = dis[cur.x][cur.y]+1; // 현위치 +1
					Q.offer(new Point(nx, ny));//현 상태값 저장
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
		
		B2(queue);
		print(false);
		
		
		int[] answer= {Integer.MIN_VALUE};
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(board[i][j]==0) flag=false;
			}
		}
		doubleEach((i,j)-> {if(board[i][j]==0) flag=false;} );
		
		if(flag){
			doubleEach((i,j)-> {answer[0]=Math.max(answer[0], dis[i][j]);});
			System.out.println(answer[0]);
		}
		else System.out.println(-1);
	}
	
	private static void doubleEach(BiConsumer<Integer, Integer> con) {
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				con.accept(i, j);
			}
		}
	}

	private static void print() {
		print(true);
	}
	private static void print(boolean yn) {
		if(yn) {
			for(int[] arr : dis) {
				System.out.println(Arrays.toString(arr));
			}
		}
	}

	private static Queue<Point> init(Queue<Point> Q) {
		doubleEach((i,j)->{if(board[i][j]==1) Q.offer(new Point(i, j));});
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