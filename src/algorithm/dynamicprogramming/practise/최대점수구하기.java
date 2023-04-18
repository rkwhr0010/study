package algorithm.dynamicprogramming.practise;

import java.util.Scanner;

public class 최대점수구하기 {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int n=kb.nextInt();
		int m=kb.nextInt();
		int[] dy=new int[m+1];
		for(int i=0; i<n; i++){
			int ps=kb.nextInt();
			int pt=kb.nextInt();
			for(int j=m; j>=pt; j--){
				dy[j]=Math.max(dy[j], dy[j-pt]+ps);
			}
		}
		System.out.print(dy[m]);
	}
}
/*
5 20
10 5
25 12
15 8
6 3
7 4


41
*
*/
