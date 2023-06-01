package algorithm.bfs.pratise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import algorithm.bfs.pratise.중요_미로최단연습.Route;
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
		@Override
			public String toString() {
				return "["+x+","+y+"]";
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
	static void f3(int[][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				//땅이 존재하면 1
				if(board[i][j] == 1) {
					//찾은 섬 지우기
					d3(i,j,board);
				}
			}
		}
	}
	static void d3(int x,int y,int[][] board) {
		//지금 위치 섬 제거
		board[x][y] = 0;
		++answer; //제거한 섬 카운트 증가
		
		//섬 주변을 동시에 퍼져나가면서 탐색할 정보를 저장할 컬렉션
		LinkedList<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		while(!q.isEmpty()) {
			//내가 선 위치
			Point c = q.poll();
			for(Direction n : Direction.values()) {
				//다음 위치
				int nx = c.x + n.x;
				int ny = c.y + n.y;
				//다음 위치가 유효범위내면서 땅이 존재하냐
				if(valid(nx, ny)&& board[nx][ny]==1) {
					list.add(new Point(nx, ny)); //부가 로직 단순히 제거된 땅 좌표저장용
					board[nx][ny]=0;//땅 제거
					q.offer(new Point(nx, ny));//다음 위치를 저장
				}
			}
		}
	}
	static List<Point> list =new ArrayList<>();

	private static boolean valid(int nx, int ny) {
		return nx>=0 && nx<n && ny>=0 && ny<n;
	}
	
	public static void main(String[] args){
		f3(input);
		System.out.println(answer);
		System.out.println(list);
	}
}
/*
5
*/