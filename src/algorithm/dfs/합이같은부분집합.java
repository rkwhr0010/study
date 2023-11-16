package algorithm.dfs;

import java.util.stream.Stream;
/**
 * 둘로 나뉘는 두 부분집합은 서로소 집합이며, 두 부분집합을 합하면 입력으로 주어진 원래의 집합이 되어 합니다.
 * 예를 들어 {1, 3, 5, 6, 7, 10}이 입력되면 {1, 3, 5, 7} = {6, 10} 으로 두 부분집합의 합이 16으로 같은 경우가 존재
 */
public class 합이같은부분집합 {
	static Integer[] intArr = {1,3,5,6,7,10};
	static Integer total = Stream.<Integer>of(intArr).reduce(0, Integer::sum);
	static Boolean flag = false;
	static String answer = "";
	
	public static void main(String[] args) {
		DFS(0,0);
		System.out.println(answer);
	}
	private static void DFS(Integer lv, Integer sum) {
		/**
		 * 재귀 구조에서 전역 플래그 변수의 순기능.
		 * 플래그 수정 시 모든 스택프레임에서 접근이 가능하니 
		 * 한방에 전부 빠져나온다.
		 */
		if(flag); 
		else if(sum > total/2); // 전체 값의 절반이 넘으면, 서로소가 될 수 없다.
		else if(lv >= intArr.length) {
			if((total-sum)==sum) {
				flag = true;
				answer = "YES";
			}
		}else {
			DFS(lv+1, sum+intArr[lv]);
			DFS(lv+1, sum);
		}
	}
}
