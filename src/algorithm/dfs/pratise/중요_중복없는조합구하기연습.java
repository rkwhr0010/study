package algorithm.dfs.pratise;

import java.util.Arrays;
/*
 * 주어진 수와 그 중 선택 수를 이용한 조합 
 */
public class 중요_중복없는조합구하기연습 {
	static int numbers  = 6;
	static int select  = 4;
	static int cnt = 0;
	static int[] com = new int[select];
	static int[][] dy = new int[numbers+1][select+1];
	
	public static void main(String[] args) {
		DFS2(0,1);
		System.out.println(cnt);
		System.out.println(count(numbers,select));
	}
	
	static void DFS(int lv, int s) {
		if(lv == select) {
			cnt++;
			System.out.println(Arrays.toString(com));
		}else {
			for(int i=s;i<=com.length;i++) {
				com[lv] = i;
				DFS(lv+1,i+1);
			}
		}
	}
	static void DFS2(int v, int s) {
		if(v == select) {
			++cnt;
			System.out.println(Arrays.toString(com));
		}else {
			for(int i = s;i<=numbers;i++) {
				com[v] = i;
				DFS2(v+1/*자리수 개행*/, i+1/*이 숫자를 제외한*/);
			}
		}
	}
	
	//조합수
	static int count(int n, int r) {
		if(n==r|| r==0) return 1;
		else if(dy[n][r]>0) return dy[n][r];
		else return dy[n][r] = count(n-1, r-1) + count(n-1, r);
	}
}
