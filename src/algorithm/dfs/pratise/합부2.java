package algorithm.dfs.pratise;

public class 합부2 {
	static int[] arr = {1,3,5,6,7,10};
	static String answer="NO";
	static int total=0;
	static boolean flag = false;
	
	public static void main(String[] args) {
		DFS(0,0);
		System.out.println(answer);
	}

	private static void DFS(int lv, int sum) {
		if(flag);
		else if (sum > total/2);
		else if (lv>= arr.length) {
			if((total-sum)== sum) {
				flag = true;
				answer ="YES";
			}
		}else {
			DFS(lv+1, sum+arr[lv]);
			DFS(lv+1, sum);
		}
	}
	
}
