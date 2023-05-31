package algorithm.bfs.pratise;

import java.util.LinkedList;
import java.util.Queue;

public class 중요_미로최단연습 {
	static class Route{
		public int x, y;
		Route(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	static int[] dx={-1, 0, 1, 0};
	static int[] dy={0, 1, 0, -1};
	static int[][] board, dis;
	
	static {
		int[][] input = {
				{0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 1, 0, 0, 0},
				{1, 1, 0, 1, 0, 1, 1},
				{1, 1, 0, 1, 0, 0, 0},
				{1, 0, 0, 0, 1, 0, 0},
				{1, 0, 1, 0, 0, 0, 0}
		};
		
		
		board=new int[8][8];
		dis=new int[8][8];
		for(int i=1; i<=7; i++){
			for(int j=1; j<=7; j++){
				board[i][j]=input[i-1][j-1];
			}
		}
	}
	
	static void B(int x, int y) {
		LinkedList<Route> list = new LinkedList<>();
		//현재 시작점
		list.add(new Route(x, y));
		//현재 방문했다.
		board[x][y] = 1;
		
		//안비었다
		while(!list.isEmpty()) {
			//현 위치
			Route now = list.poll();
			//다음 위치
			for(int i=0;i<dx.length;i++) {
				//다음으로 갈 위치 얻기
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				//유효한 위치인가?, 방문한적 있는가?
				if(fromClosedTo(nx, ny) && board[nx][ny] == 0) {
					//방명록 작성
					board[nx][ny] = 1;
					dis[nx][ny] = dis[now.x][now.y]+1; //좀 더 시간이 걸린다.
					list.add(new Route(nx, ny));
				}
			}
		}
	}
	
	static void BB(int x, int y) {
		//선입 선출을 위함
		Queue<Route> q = new LinkedList<>();
		//방명록 작성
		board[x][y] = 1;
		q.offer(new Route(x, y));
		
		//비어있냐
		while(!q.isEmpty()) {
			//현재 위치
			Route now = q.poll();
			//다음으로 갈 위치값 
			for(int i=0; i<dx.length;i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				//타당성 검사
				if(fromClosedTo(nx, ny) && board[nx][ny] == 0) {
					//방명록 작성
					board[nx][ny]  = 1;
					dis[nx][ny]  = dis[now.x][now.y] +1;
					q.offer(new Route(nx, ny));
				}
			}
		}
	}
	static void B2(int x,int y) {
		Queue<Route> q = new LinkedList<>();
		q.offer(new Route(x, y));
		board[x][y] = 1;
		while(!q.isEmpty()) {
			Route now = q.poll();
			for(int i=0;i<dx.length;i++) {
				int nx = dx[i]+now.x;
				int ny = dy[i]+now.y;
				if(fromClosedTo(nx, ny) && board[nx][ny] == 0) {
					board[nx][ny] = 1;
					dis[nx][ny] = dis[now.x][now.y] + 1;
					q.offer(new Route(nx, ny));
				}
			}
		}
	}
	
	//간선 정보
	static void B3(final int x, final int y) {
		//간선 정보를 저장할 큐
		Queue<Route> q = new LinkedList<>();
		//현재까지 간성 정보 저장
		q.offer(new Route(x, y));
		//방명록 체크
		board[x][y] = 1;
		//현재 상태정보 탐색
		while(!q.isEmpty()) {
			//현까지 상태값 
			Route now = q.poll();
			for(int i=0;i<dx.length;i++) {
				//다음 경로 좌표얻기
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				//유효성 검사 && 첫 방문인지
				if(fromClosedTo(nx, ny) && board[nx][ny] == 0) {
					//방문 체크
					board[nx][ny] = 1;
					//값 계산 핵심@@ 다음 경로까지 현재경로 값 +1
					dis[nx][ny] = dis[now.x][now.y] +1;
					//상태 갱신
					q.offer(new Route(nx, ny));
				}
			}
		}
	}
	static void B4(int x, int y) {
		Queue<Route> q = new LinkedList<>();
		q.offer(new Route(x, y));
		board[x][y] = 1;
		while(!q.isEmpty()) {
			Route c = q.poll();
			for(int i=0;i<dx.length;i++) {
				int nx = c.x+dx[i];
				int ny = c.y+dy[i];
				if(fromClosedTo(nx, ny) && board[nx][ny]==0) {
					board[nx][ny]=1;
					dis[nx][ny] = dis[c.x][c.y] +1;
					q.offer(new Route(nx, ny));
				}
			}
		}
	}
	
	private static boolean fromClosedTo(int nx, int ny) {
		return nx>=1 && nx <=7 && ny>=1 && ny<=7;
	}
	
	public static void main(String[] args) {
//		BFS2(1, 1);
		B4(1, 1);
		if(dis[7][7]==0) System.out.println(-1);
		else System.out.println(dis[7][7]);
	}
}
/*
12
*/