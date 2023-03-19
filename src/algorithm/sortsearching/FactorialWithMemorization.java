package algorithm.sortsearching;

import java.util.Arrays;

public class FactorialWithMemorization {
	static int[] memo;
	static int input = 40;
	
	public static void main(String[] args) {
		
		memo = new int[input+1];
		DFS(40);
		System.out.println(Arrays.toString(memo));
	}

	private static int DFS(int i) {
		if(memo[i]!=0) return memo[i];
		if(i == 1 || i ==2) return memo[i]=1;
		else return memo[i] = DFS(i-1)+DFS(i-2);
	}
}
