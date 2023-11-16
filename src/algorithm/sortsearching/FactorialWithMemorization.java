package algorithm.sortsearching;

import java.util.Arrays;

public class FactorialWithMemorization {
	static int[] memo;
	static int input;
	
	public static void main(String[] args) {
		input = 40;
		memo = new int[input+1];
		DFS(40);
		System.out.println(Arrays.toString(Arrays.copyOfRange(memo, 1, memo.length)));
	}

	private static int DFS(int i) {
		if(memo[i]!=0) return memo[i];
		if(i == 1 || i ==2) return memo[i]=1;
		// i가 1~2 는 여기까지 도달 안한다. 따라서 익덱스 범위를 벗어날 수 없다.
		else return memo[i] = DFS(i-1)+DFS(i-2);
	}
}
