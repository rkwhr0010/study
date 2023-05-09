package algorithm.greedy.practise;

public class 중요_UnionFind연습 {
	static int[] u; //집합번호를 저장하는 배열 unf[1] = 5 ; 1은 5 그룹에 속한다.
	static int find(int v) {
		//현재 값과 현재값에 그룹값이 같나? 
		if(v == u[v]) return u[v];//종료 조건
		else return u[v] = find(u[v]);
	}
	//그룹 맺기
	static void uni(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		//서로 다르면 그룹을 맺어준다.
		if(fa != fb) u[fa] = fb;//fa는 fb에 속한다.(그룹맺기)
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
			uni(a, b);
		}
		//a와 b는 친구니?
		int fa=find(3);
		int fb=find(8);
		if(fa==fb) System.out.println("YES");
		else System.out.println("NO");		
	}
}