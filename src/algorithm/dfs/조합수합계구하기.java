package algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 조합수합계구하기 {
	static List<Integer> list = Arrays.asList(1,3,5);
	static List<Integer> result = new ArrayList<>();
	static int[] arr = new int[2];
	
	public static void main(String[] args) {
		sol(0, 0);
		System.out.println(result.stream().reduce(0, Integer::sum));
	}
	
	
	static void sol(int lv, int index) {
		if(lv == 2) {
			int max = Math.max(list.get(arr[0]), list.get(arr[1]));
			int min = Math.min(list.get(arr[0]), list.get(arr[1]));
			
			result.add(max * min / gcd(max, min));
			
			return;
		} 
		
		for(int i = index; i < list.size(); i++) {
			arr[lv] = i;
			sol(lv + 1, i + 1);
		}
		
	}
	
    static int gcd(int max, int min) {
        if (min == 0) {
            return max;
        } 
        
        return gcd(min, max % min);
    }
}
