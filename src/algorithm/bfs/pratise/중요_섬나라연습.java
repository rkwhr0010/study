package algorithm.bfs.pratise;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
/*
N*N의 섬나라 아일랜드의 지도가 격자판의 정보로 주어집니다. 각 섬은 1로 표시되어 상하좌
우와 대각선으로 연결되어 있으며, 0은 바다입니다. 섬나라 아일랜드에 몇 개의 섬이 있는지
구하는 프로그램을 작성하세요.
 */
public class 중요_섬나라연습 {
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	//8방향
	static enum Direction {
		//Left, Right, Up, Down 
		U(0,1), RU(1,1),R(1,0),RD(1,-1),D(0,-1),LD(-1,-1),L(-1,0),LU(-1,1);
		int x;
		int y;
		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int answer=0, n;
	static int[][] input;
	static Queue<Point> q = new LinkedList<>();
	
	static {
		 int[][] input2 ={
				{1, 1, 0, 0, 0, 1, 0},
				{0, 1, 1, 0, 1, 1, 0},
				{0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 1, 1},
				{1, 1, 0, 1, 1, 0, 0},
				{1, 0, 0, 0, 1, 0, 0},
				{1, 0, 1, 0, 1, 0, 0}
		};
		 input = input2.clone();
		n= input.length;
		
	}
	
	static void findIsland(int[][] board) {
		//섬탐색
		for(int x=0;x<board.length;x++) {
			for(int y=0;y<board[x].length;y++) {
				//섬발견
				if(board[x][y]==1) {
					//지운 섬의 개수
					answer++;
					//지워버린다.
					deleteIsland(x,y, board);
				}
			}
		}
	}
	
	private static void deleteIsland(int x, int y,int[][] board) {
		//이제 이섬은 없는 섬이다.
		board[x][y] = 0 ;
		//현재 기준 섬면적 탐색 후 지운다.
		q.offer(new Point(x, y));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			//주변 면적 탐색
			for(Direction next : Direction.values()) {
				int nx = now.x+next.x;
				int ny = now.y+next.y;
				//다음 섬 면적에 땅이 있냐?
				if(valid(nx, ny)&& board[nx][ny]==1) {
					//있으면 지운다.
					board[nx][ny] = 0;
					//지운 위치 값 저장
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	//섬찾기
	static void f1(int[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				//섬찾기 알고리즘
				if(board[i][j] == 1) {
					//찾은 섬 지우기
					d1(i,j,board);
				}
			}
		}
	}
	
	private static void d1(int x, int y, int[][] board) {
		answer++;//섬 하나 지웠다.
		q.offer(new Point(x, y)); //현재 섬 상태 저장
		board[x][y] = 0; //현재 위치는 지운다.
		while(!q.isEmpty()) {
			//현재 위치
			Point cur = q.poll();
			//다음 위치 탐색
			for(Direction next : Direction.values()) {
				int nx = cur.x + next.x;
				int ny = cur.y + next.y;
				//다음 위치에 땅이있니?
				if(valid(nx, ny)&&board[nx][ny]==1) {
					//땅 지우기
					board[nx][ny] = 0;
					//현재 상태값 저장
					q.offer(new Point(nx, ny));
				}
				
			}
			
		}
	}
	//점 지도 받음
	static void f2(final int[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				//섬찾기 알고리즘
				if(board[i][j] == 1) {
					//찾은 섬 지우기
					d2(i,j,board);
				}
			}
		}
	}
	
	static void d2(int x,int y, int[][] board) {
		q.offer(new Point(x, y));//현재 상태 저장
		board[x][y] = 0; // 현위치는 지운다.
		answer++; //섬발견
		while(!q.isEmpty()) {
			//현 위치
			Point now = q.poll();
			for(Direction next/*다음경로*/:Direction.values()) {
				//다음 경로
				int nx = now.x+next.x;
				int ny = now.y+next.y;
				//유효성 , 땅이 더 존재하냐
				if(valid(nx,ny) && board[nx][ny] ==1) {
					board[nx][ny] = 0; //잔여땅 지우기
					q.offer(new Point(nx, ny));//현재 상태값 저장
				}
				
			}
			
		}
	}
	

	private static boolean valid(int nx, int ny) {
		return nx>=0 && nx<n && ny>=0 && ny<n;
	}
	
	public static void main(String[] args){
		f2(input);
		System.out.println(answer);
	}
}
/*
5
*/