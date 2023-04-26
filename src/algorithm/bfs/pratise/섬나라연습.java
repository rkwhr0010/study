package algorithm.bfs.pratise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 섬나라연습 {
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
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
//	static class Direction implements Iterable<Point>{
//		List<Point> points = new ArrayList<>();
//		public Direction() {
//			int[] dx={-1, -1, 0, 1, 1, 1, 0, -1};
//			int[] dy={0, 1, 1, 1, 0, -1, -1, -1};
//			for (int i = 0; i < dy.length; i++) {
//				points.add(new Point(dx[i], dy[i]));
//			}
//		}
//		@Override
//		public Iterator<Point> iterator() {
//			return points.iterator();
//		}
//	}
//	static Direction direction = new Direction();
	static int answer=0, n;
	static boolean bewteen(int data, int a, int b) {
		return a<=data && data <b;
	}
	
	void sol(int[][] board){
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		board[0][0] = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(board[i][j]==1) {
//					BFS(i,j,board,q);
				}
			}
		}
	}
	void BFS2(int x, int y, int[][]board) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		board[x][y] = 0;
		answer++;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(Direction d : Direction.values()) {
				int nx = p.x+d.x;
				int ny = p.y+d.y;
				if(bewteen(nx, 0, n) && bewteen(ny, 0, n) && board[nx][ny]==1) {
					q.offer(new Point(nx, ny));
					board[nx][ny] = 0;
				}
			}
		}
	}
	
	
	private void BFS(int x, int y, int[][] board,Queue<Point> q) {
		answer ++;
		board[x][y] = 0;
		q.offer(new Point(x, y));
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(Direction next : Direction.values()) {
				int nx = p.x + next.x;
				int ny = p.y + next.y;
				if(nx>=0 && nx<n && ny>=0 && ny<n && board[nx][ny]==1) {
					board[nx][ny]=0;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		섬나라연습 T = new 섬나라연습();
		Scanner kb = new Scanner(System.in);
		n=kb.nextInt();
		int[][] arr=new int[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				arr[i][j]=kb.nextInt();
			}
		}
//		T.sol(arr);
		T.BFS2(0,0,arr);
		System.out.println(answer);
	}
}
/*

7
1 1 0 0 0 1 0
0 1 1 0 1 1 0
0 1 0 0 0 0 0
0 0 0 1 0 1 1
1 1 0 1 1 0 0
1 0 0 0 1 0 0
1 0 1 0 1 0 0

*/