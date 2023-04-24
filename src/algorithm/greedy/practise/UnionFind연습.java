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
	
	
	static int f(int v) {
		//초기화된 값이랑 찾는 값이 같으면, 그냥 그값 그대로 리턴해도됨
		if(v == u[v]) return u[v];
		//재귀적으로 종단까지 찾는다. 종단 값이 현재 v와 같으면 다음 그룹화된 값이 없는 것이다.
		else return u[v] = f(u[v]);
	}
	static void u(int a,int b) {
		int fa = f(a);
		int fb = f(b);
		if(fa != fb) u[fa] = fb;
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
		//초기화, 자기 인덱스에 자기 값으로 초기화
		for(int i=1; i<=n; i++) u[i]=i;
		for(int i=1; i<=m; i++){
			int a=node[i][0];
			int b=node[i][1];
			//그룹짓기
			u(a, b);
		}
		//a와 b는 친구니?
		int fa=f(3);
		int fb=f(8);
		if(fa==fb) System.out.println("YES");
		else System.out.println("NO");		
	}
}