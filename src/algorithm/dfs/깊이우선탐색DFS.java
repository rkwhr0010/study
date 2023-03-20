package algorithm.dfs;

/**
 * 깊이 우선 탐색은 무조건 끝까지 탐색한다. 
 * 깊이를 10 지정 시 10까지 다 탐색 후 그 결과를 기반으로 값을 도출
 */
public class 깊이우선탐색DFS {
	static int n;
	static int[] ch;
	
	public static void main(String[] args) {
		n = 3;
		ch = new int[n+1];
		DFS(1);
	}
	static void DFS(int L) {
		//L까지가 끝점, 따라서 L+1 은 끝점 이상 탐색을 하는 것
//		if(L==n+1) {
		if(L>n) {
			for (int i = 1; i <= n; i++) {
				if(ch[i] == 1) {
					System.out.print(i+" ");
				}
			}
			System.out.println();
		} else {
			ch[L] = 1;
			DFS(L+1);
			ch[L] = 0 ;
			DFS(L+1);
		}
	}
}
