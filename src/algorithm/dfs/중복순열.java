package algorithm.dfs;

public class 중복순열 {
	static int size = 3;
	static int max = 5;
	static int[] arr = new int[size];
	static int[] chk = new int[size];
	
	
	public static void main(String[] args) {
		DFS2(0);
	}
	private static void DFS(int num) {
		if(num>=arr.length) {
			for(int value : arr) {
				System.out.print(value+" ");
			}
			System.out.println();
		}else {
			for (int i = 1; i <= max; i++) {
				arr[num] = i;
				DFS(num+1);
			}
		}
	}
	private static void DFS2(int num) {
		if(num>=arr.length) {
			for(int value : arr) {
				System.out.print(value+" ");
			}
			System.out.println();
		}else {
			for (int i = 1; i <= max; i++) {
				if(chk[num]==0) {
					arr[num] = i;
					chk[num] = 1;
					DFS(num+1);
				}
			}
		}
	}
	
	
}
