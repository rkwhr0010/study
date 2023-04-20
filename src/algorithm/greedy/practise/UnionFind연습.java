package algorithm.greedy.practise;

public class UnionFind연습 {
	
	static int[] u; //집합번호를 저장하는 배열 unf[1] = 5 ; 1은 5 그룹에 속한다.
	
	public static int Find(int v){
		if(v==u[v]) return u[v];
		 else return u[v]=Find(u[v]);
	}
	
	//두 수를 집합 맺어줌
	public static void Union(int a, int b){
		int fa=Find(a);
		int fb=Find(b);
		if(fa!=fb) u[fa]=fb;
	}
	
	
	static int find(int v) {
		if(v == u[v]) return u[v];
		else return u[v] = find(u[v]);
	}
	
	static void uni(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa!= fb) u[fa]=fb; 
	}
	
	

	public static void main(String[] args){
		int n=9; //반 학생수
		int m=7; //숫자 쌍 수
		int[][] node = 
			{{}
			,{1,2}
			,{2,3}
			,{3,4}
			,{1,5}
			,{6,7}
			,{7,8}
			,{8,9}};
		
		u=new int[n+1];
		for(int i=1; i<=n; i++) u[i]=i;
		for(int i=1; i<=m; i++){
			int a=node[i][0];
			int b=node[i][1];
			uni(a, b);
		}
		//a와 b는 친구니?
		int fa=find(3);
		int fb=find(8);
		if(fa==fb) System.out.println("YES");
		else System.out.println("NO");		
	}
}