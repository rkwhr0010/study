package algorithm.dfs.pratise;
import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Scanner;

public class 피자배달거리연습_조합구하기응용 {
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
			int citySum = cal();
			answer = min(answer,citySum);
		}else {
			for(int i=s;i<len;i++) {
				combi[lv] = i;
				DFS4(lv+1, i+1);
			}
		}
	}
	
	void DFS(int lv, int s) {
		//피자 선택 경우의 수가 다 찼다.
		if( lv == m ) {
			int city = cal();
			answer = min(answer, city);
		} else {
			for(int i=s;i<len;i++) {
				combi[lv] = i;
				DFS(lv+1,i+1);
			}
		}
	}

	private int cal() {
		//도시의 피자배달 거리
		int city = 0 ;
		//선택된 피자 가게 수와 집집 마다 비용 계산
		for(Point h : hs) {
			//집의 피자배달 거리
			int house = Integer.MAX_VALUE;
			//선택된 피자가게들, 중 가장 작은 집배달 거리를 구한다. 
			for(int pizza : combi) {
				//가장 적은 피자 <=> 집 간 거리
				house = min(house,abs(h.x-pz.get(pizza).x)+abs(h.y-pz.get(pizza).y));
			}
			//여기 도달 시 가장 작은 집 배달거리가 구해진다.
			city += house;
		}
		return city;
	}
	
	public static void main(String[] args){
		피자배달거리연습_조합구하기응용 T = new 피자배달거리연습_조합구하기응용();
		Scanner kb = new Scanner(System.in);
		n= 4; //도시 크기(정사각형)
		m= 4; //살아남을 피자 가게 수
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
		T.DFS(0, 0);
		System.out.println(answer);
	}
}
/*
0 1 2 0
1 0 2 1
0 2 1 2
2 0 1 2

6

0 빈곳, 1 집, 2 피자집

*/