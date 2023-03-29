package algorithm.dfs.pratise;

public class 중수 {
	static int size = 3;
	static int max = 5;
	static int[] arr = new int[size];
	static int[] chk = new int[size];
	
	public static void main(String[] args) {
		DFS(0);
	}

	private static void DFS(int lv) {
		if(lv==size) {
			for(int num : arr) {
				System.out.print(num+" ");
			}
			System.out.println();
			return;
		}else {
			for (int i = 1; i <= max; i++) {
				arr[lv] = i;
				DFS(lv+1);
			}
		}
	}
	
}
