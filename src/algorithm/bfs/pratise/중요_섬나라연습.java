package algorithm.bfs.pratise;

import java.util.ArrayDeque;
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
	static ArrayDeque<Point> q = new ArrayDeque<>();
	
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
			Point now = q.pollFirst();
			//주변 면적 탐색
			for(Direction next : Direction.values()) {
				int nx = now.x+next.x;
				int ny = now.y+next.y;
				//다음 섬 면적에 땅이 있냐?
				if(valid(board, nx, ny)) {
					//있으면 지운다.
					board[nx][ny] = 0;
					//지운 위치 값 저장
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	static void find(int[][] board) {
		//섬 탐색
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				//섬발견
				if(board[i][j]==1) {
					delete(i,j,board);
				}
				
			}
		}
	}

	private static void delete(int i, int j, int[][] board) {
		board[i][j] = 0;//발견된 섬 지우기
		q.offer(new Point(i, j));
		++answer; //발견 증가가
		
		//발견된 섬 위치에서 주변 땅 탐색해 지우기
		while(!q.isEmpty()) {
			Point now = q.poll();
			//주변 탐색
			for(Direction next : Direction.values()) {
				int nx = now.x + next.x;
				int ny = now.y + next.y;
				//땅이냐?
				if(valid(board, nx, ny)) {
					//지우기
					board[nx][ny] = 0;
					q.offer(new Point(nx, ny));
				}
				
			}
			
		}
		
	}

	private static boolean valid(int[][] board, int nx, int ny) {
		return nx>=0 && nx<n && ny>=0 && ny<n && board[nx][ny]==1;
	}
	
	public static void main(String[] args){
		find(input);
		System.out.println(answer);
	}
}
/*
5
*/