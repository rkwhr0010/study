package algorithm.etc;
import java.util.*;
import java.util.stream.IntStream;
// n 값이 될 수 있는 연속된 자연 수 합 구하기
class 연속된자연수합_ {

	public static void main(String[] args) {
		int n = 1000;
		int answer = 0, sum = 0;
		int m = n / 2 + 1; //2개 이상의 연속된 자연수의 합은 절반 정도만 필요
		int[] arr = IntStream.rangeClosed(1, m).toArray();
		
		//rt를 담당하는 외부 반복문
		for (int rt = 0,lt = 0; rt < m; rt++ ) {
			sum+= arr[rt];
			//lt를 담당하는 내부 반복문
			//n보다 sum이 크거나 같을때.
			while(n<=sum) {
				//sum이 n과 같다.
				if(sum==n) {
					prettyPrint(arr, lt, rt);
					answer++;
					sum-=arr[lt++];
				//sum이 n보다 크다.
				}else {
					sum-=arr[lt++];
				}
			}
		}
		System.out.println(answer);
	}
	static void prettyPrint(int[] arr, int lt, int rt) {
		System.out.print("[lt="+lt+" rt="+(rt)+"]");
		Arrays.stream(arr, lt, rt+1).forEach(n->System.out.print(n+","));
		System.out.println();
	}
}