package algorithm.dfs.pratise;

public class 동전교환연습 {
	static Integer[] kind = {1, 8,  20, 25,50};
	static Integer money = 129;
	static Integer answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		DFS2(0,0);
		System.out.println(answer);
	}

	private static void DFS(int cnt, int sum) {
		if(cnt>answer || sum > money) return;
		
		if(sum == money) {
			answer =  Integer.min(cnt, answer);
		}else {
			for(Integer val : kind) {
				DFS(cnt+1, sum+val);
			}
		}
	}
	
	private static void DFS2(int cnt, int sum) {
		if(cnt>answer) return;
		
		if(sum == money) {
			answer = Integer.min(cnt,answer);
		}else {
			for (int i = 0; i < kind.length; i++) {
				DFS(cnt+1,sum+kind[i]);
			}
		}
		
	}
	
	
	
	
}
