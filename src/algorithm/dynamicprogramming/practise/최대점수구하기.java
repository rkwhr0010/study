package algorithm.dynamicprogramming.practise;

import java.util.Scanner;

public class 최대점수구하기 {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int[][] arr = {
				{10, 5  },
				{25, 12 },
				{15, 8  },
				{6 ,3   },
				{7 ,4   }
		};
		int n= 5;//문제수
		int m= 20;//제한시간
		int[] dy=new int[m+1];
		//문제를 순회한다.
		for(int i=0; i<n; i++){
			int ps=arr[i][0];//풀었을때점수
			int pt=arr[i][1];//소요시간
			//제한시간부터 역순회시작(내 소요시간까지)
			for(int j=m; j>=pt; j--){
				dy[j]=Math.max(dy[j], dy[j-pt]+ps);
			}
		}
		System.out.print(dy[m]);
	}
}
/* 
문제수/제한시간
5 20
좌,점수 우, 시간
10 5
25 12
15 8
6 3
7 4


41
*
*/
