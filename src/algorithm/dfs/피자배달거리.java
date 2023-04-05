package algorithm.dfs;
import java.util.*;

public class 피자배달거리 {
	static class Point{
		public int x, y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	static int n; //도시 크기 정사각형
	static int m; //피자가게 선택 수
	static int len; // 피자가게 수
	static int answer=Integer.MAX_VALUE;
	static int[] combi;
	static ArrayList<Point> hs, pz;
	
	public void DFS(int L, int s){
		if(L==m){
			int sum=0;
			for(Point h : hs){
				int dis=Integer.MAX_VALUE;
				for(int i : combi){
					dis=Math.min(dis, Math.abs(h.x-pz.get(i).x)+Math.abs(h.y-pz.get(i).y));
				}
				sum+=dis;
			}
			answer=Math.min(answer, sum);
		}
		else{
			for(int i=s; i<len; i++){
				combi[L]=i;
				DFS(L+1, i+1);
			}
		}
	}

	void DFS2(int lv, int s) {
		if(lv == m) {
			System.out.println(Arrays.toString(combi));
			int sum = 0;
			for(Point house : hs) {
				int dis = Integer.MAX_VALUE;
				for(int i : combi) { // 피자선택
					//피자거리
					dis = Math.min(dis, Math.abs(house.x - pz.get(i).x)+Math.abs(house.y - pz.get(i).y) );
				}
				//피자 도시거리
				sum += dis;
			}
			answer=Math.min(answer, sum);
		} else {
			for (int i = s; i < len; i++) {
				combi[lv] = i;
				DFS2(lv+1,i+1);
			}
		}
	}
	
	public static void main(String[] args){
		피자배달거리 T = new 피자배달거리();
		Scanner kb = new Scanner(System.in);
		n=kb.nextInt();
		m=kb.nextInt();
		pz=new ArrayList<>();
		hs=new ArrayList<>();
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				int tmp=kb.nextInt();
				if(tmp==1) hs.add(new Point(i, j));
				else if(tmp==2) pz.add(new Point(i, j));
			}
		}
		len=pz.size();
		combi=new int[m];
		T.DFS2(0, 0);
		System.out.println(answer);
	}
}
/*
4 4
0 1 2 0
1 0 2 1
0 2 1 2
2 0 1 2

6

0 빈곳, 1 집, 2 피자집

*/