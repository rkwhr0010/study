package algorithm.etc;
import java.util.*;

// n개 == arr.length , 연속된 arr 배열 수 합이 m인 경우가 몇회인가?
class 연속부분수열_ {

	public static void main(String[] args) {
		int 배열크기 = 50;
		int 합계 = 30;
		int[] arr = new Random().ints(배열크기, 1, 11).toArray();//1~10
		int answer = 0;
		System.out.println(Arrays.toString(arr));
		//핵심은 값을 증감 후 같을 수 있으니 검증로직을 한 번 더 태우는 것이다.
		for (int rt = 0, sum = 0, lt = 0; rt < arr.length;) {
			if (sum <= 합계) {
				if (sum == 합계) {
					prettyPrint(arr, lt, rt);
					answer++;
					sum -= arr[lt++];
				}
				
				sum += arr[rt++];
				
				if (sum == 합계) {
					prettyPrint(arr, lt, rt);
					answer++;
					sum -= arr[lt++];
				}
			} else {
				sum -= arr[lt++];
			}
		}
		System.out.println("총 카운트 = "+answer);
	}
	static void prettyPrint(int[] arr, int lt, int rt) {
		System.out.print("[lt="+lt+" rt="+(rt-1)+"]");
		Arrays.stream(arr, lt, rt).forEach(n->System.out.print(n+","));
		System.out.println();
	}
	
}