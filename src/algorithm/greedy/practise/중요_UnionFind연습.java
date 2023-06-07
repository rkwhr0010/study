package algorithm.greedy.practise;

import java.util.Arrays;

/*
연합 및 찾기(Union-Find) 알고리즘은 동적 연결 구조를 유지하는 데 사용되는 자료구조입니다. 
동적 연결 구조는 컴퓨터 과학에서 데이터를 저장하는 방법 중 하나로, 노드와 링크로 이루어진 그래프를 사용하여 데이터를 표현합니다. 
연합 및 찾기 알고리즘은 노드 간의 연결 관계를 유지하는 데 사용됩니다.

연합 및 찾기 알고리즘은 두 가지 연산을 제공합니다. 
첫 번째 연산은 두 노드를 연결하는 연산입니다. 
두 번째 연산은 두 노드가 연결되어 있는지 확인하는 연산입니다.

연합 및 찾기 알고리즘은 다음과 같은 단계로 작동합니다.

1. 각 노드는 자신과 연결된 노드의 리스트를 가지고 있습니다.
2. 두 노드를 연결하려면 두 노드의 리스트에서 다른 노드를 제거하고 두 노드를 연결합니다.
3. 두 노드가 연결되어 있는지 확인하려면 두 노드의 리스트에서 다른 노드를 찾습니다.

연합 및 찾기 알고리즘은 동적 연결 구조를 유지하는 데 사용되는 효율적인 알고리즘입니다.
 */
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
//		int fa=find(3);
//		int fb=find(8);
		int fa=find(3);
		int fb=find(8);
		if(fa==fb) System.out.println("YES");
		else System.out.println("NO");		
		
		unionFind(node);
		
		System.out.println(same(3, 8));
		System.out.println(same(3, 4));
	}
	
	//그룹 배열
	private static int[] parents;
	
	private static void unionFind(int[][] input) {
		int kind = Arrays.stream(input)
			.flatMapToInt(Arrays::stream)
			.max()
			.getAsInt();
		
		//0번 숫자가 없음을 고려 +1, 0번 숫자가 만약 존재한다면 +1 제거
		parents = new int[kind+1]; 
		//그룹 개수 크기만큼 배열을 각각 고유 그룹 ID로 초기화
		for(int i=1;i<parents.length;i++) {
			parents[i] = i; 
		}
		for(int i = 1; i<input.length;i++) {
			union2(input[i][0], input[i][1]);
		}
	}
	
	//그룹 찾기
	static int find2(int n) {
		//자신 그룹ID가 자신과 같으면 같은 그룹
		if(parents[n] == n) return n;
		//자신 그룹ID가 자신과 다르면, 어딘가 그룹에 속해있는 것, 재귀적으로 끝단까지 탐색
		else return (parents[n] = find2(parents[n]));//parents[n] 내가 속한 그룹 ID로 재탐색
	}
	static void union2(int x, int y) {
		//같은 그룹이 아니면 그룹 맺기
		if(find2(x) != find2(y)) parents[x] = y;
	}
	static boolean same(int x, int y) {
		return find2(x) == find2(y);
	}
	
	
	
	
	
	
}