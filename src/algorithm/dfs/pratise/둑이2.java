package algorithm.dfs.pratise;

public class 둑이2 {
	static int[] arr = {81, 58, 42, 33, 61};
	static int max = 259;
	static int answer = 0;
	
	public static void main(String[] args) {
		DFS(0,0);
		System.out.println(answer);
	}

	private static void DFS(int lv,int sum) {
		if(sum>max);
		else if(lv==arr.length) {
			answer = Integer.max(answer, sum);
		}else {
			DFS(lv+1,sum+arr[lv]);
			DFS(lv+1,sum);
		}
	}
}
