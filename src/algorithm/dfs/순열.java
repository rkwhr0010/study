package algorithm.dfs;

public class 순열 {
	static int[] arr = {3,6,9};
	static int chk[] = new int[arr.length];
	
	static int size = 2;
	static int[] pm = new int[arr.length];
	
	public static void main(String[] args) {
		DFS(0);
	}

	private static void DFS(int lv) {
		if(lv == pm.length) {
			for(int val : pm) System.out.print(val + " ");
			System.out.println();
		}else {
			for (int i = 0; i < arr.length; i++) {
				if(chk[i] == 0) {
					chk[i] = 1;
					pm[lv] = arr[i];
					DFS(lv+1);
					chk[i] = 0;
					
				}
			}
		}
	}
}
