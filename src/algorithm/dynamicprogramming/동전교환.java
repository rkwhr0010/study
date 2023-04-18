package algorithm.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class 동전교환{
	static int n, m;
	static int[] dy;
	public int solution(int[] coin){
		Arrays.fill(dy, Integer.MAX_VALUE);
		dy[0]=0;
		for(int i=0; i<n; i++){
			for(int j=coin[i]; j<=m; j++){
				dy[j]=Math.min(dy[j], dy[j-coin[i]]+1);
			}
		}
		return dy[m];
	}
	
	int sol(int[] coin) {
		Arrays.fill(dy, Integer.MAX_VALUE);
		dy[0] = 0;
		
		for(int i=0;i<coin.length;i++) {
			for(int j=coin[i]; j<=m ;j++) {
				dy[j] = Math.min(dy[j], dy[j-coin[i]]+1);
			}
		}
		return dy[m];
	}

	int sol1(int[] coin) {
		Arrays.fill(coin, Integer.MAX_VALUE);
		dy[0] = 0;
		
		for(int i=0;i<coin.length;i++) {
			for(int j=coin[i];j<=m;j++) {
				dy[j] = Math.min(dy[j], dy[j-coin[j]]+1);
			}
		}
		
		return dy[m];
	}
	
	public static void main(String[] args){
		동전교환 T = new 동전교환();
		Scanner kb = new Scanner(System.in);
		n=kb.nextInt();
		int[] arr=new int[n];
		for(int i=0; i<n; i++){
			arr[i]=kb.nextInt();
		}
		m=kb.nextInt();
		dy=new int[m+1];
		System.out.print(T.sol(arr));
	}
}
/*
 * 
 * 
 * 
3
1 2 5
15

3
 * 
 * 
 */
