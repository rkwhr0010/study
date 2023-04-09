package algorithm.dfs.pratise;

import java.util.stream.Stream;

public class 합부분집합연습 {
	static Integer[] intArr = {1,3,5,6,7,10};
	static Integer total = Stream.<Integer>of(intArr).reduce(0, Integer::sum);
	static Boolean flag = false;
	static String answer = "";
	
	public static void main(String[] args) {
		DFS(0,0);
		System.out.println(answer);
	}

	private static void DFS(int lv, int sum) {
		if(flag) ;
		else if((total-sum)<sum);
		else if(lv >= intArr.length ) {
			if((total-sum)== sum) {
				flag = true;
				answer = "YES";
			}
		} else {
			DFS(lv+1, sum+intArr[lv]);
			DFS(lv+1, sum);
		}
	}
	
}
