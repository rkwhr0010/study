package algorithm.dfs.pratise;

import java.util.Arrays;

public class 중요_순열연습 {
	static int[] arr = {3,6,9,12};
	static int ch[] = new int[arr.length];
	static int chk[] = new int[arr.length];
	
	static int size = 2;
	static int m = 2;
	static int[] pm = new int[size];
	
	public static void main(String[] args) {
		origin(0);
		System.out.println("\n");
		DFS(0);
		System.out.println("\n");
		all(0);
	}
	
	
	static void origin(int L){
		if(L==m){
			System.out.print(Arrays.toString(pm));
		}
		else{
			for(int i=0; i<arr.length; i++){
				if(ch[i]==0){
					ch[i]=1;
					pm[L]=arr[i];
					origin(L+1);
					ch[i]=0;
				}
			}
		}
	}
	
	static void DFS(int lv) {
		if(lv == size) {
			System.out.print(Arrays.toString(pm));
		}else {
			for (int i = 0; i < arr.length; i++) {
				if(ch[i] == 0) {
					ch[i] = 1;
					pm[lv] = arr[i];
					DFS(lv+1);
					ch[i] = 0;
				}
				
			}
		}
	}
	
	//중복허용
	static void all(int lv) {
		if(lv == size) {
			System.out.print(Arrays.toString(pm));
		}else {
			for(int el : arr) {
				pm[lv] = el;
				all(lv+1);
			}
		}
	}
	
	
}
