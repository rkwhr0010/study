package algorithm.dfs.pratise;
import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Scanner;

public class 피자배달거리연습 {
	static class Point{
		public int x, y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	/*N개 정사각형 도시 크기, 그 안 피자가게 M개를 제외하고 다 폐업, 집들과 피자가게 거리가 최소인 피자가게만 생존*/
	static int n; //도시 크기 정사각형
	static int m; //피자가게 선택 수
	static int len; // 피자가게 수
	static int answer=Integer.MAX_VALUE;
	static int[] combi;
	static ArrayList<Point> hs, pz;
	
	void DFS4(int lv, int s) {
		if(lv == m) {
			int citySum = 0;
			for(Point h : hs) {
				int houseSum = Integer.MAX_VALUE;
				for(int p : combi) {
					houseSum = min(houseSum, abs(h.x-pz.get(p).x)+abs(h.y-pz.get(p).y));
				}
				citySum += houseSum;
			}
			answer = min(answer,citySum);
		}else {
			for(int i=s;i<len;i++) {
				combi[lv] = i;
				DFS4(lv+1, i+1);
			}
		}
	}
	
	
	public static void main(String[] args){
		피자배달거리연습 T = new 피자배달거리연습();
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
		kb.close();
		len=pz.size();
		combi=new int[m];
		T.DFS4(0, 0);
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