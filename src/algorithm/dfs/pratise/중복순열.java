package algorithm.dfs.pratise;

public class 중복순열 {
	static int size = 2;
	static int max = 5;
	static int[] arr = new int[size];
	
	public static void main(String[] args) {
		DFS(0);
		
	}

	private static void DFS(int lv) {
		if(lv>=size) {
			for(int value : arr) System.out.print(value+" ");
			System.out.println();
		}else {
			for (int i = 1; i <= max; i++) {
				arr[lv] = i;
				DFS(lv+1);
			}
			
		}
	}
}
