package algorithm.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class 공통원소구하기 {
	public static void main(String[] args) {
		int[] arr1 = ThreadLocalRandom.current().ints(10, 1, 20).distinct().sorted().toArray();
		int[] arr2 = ThreadLocalRandom.current().ints(10, 1, 20).distinct().sorted().toArray();
		
		int p1=0;
		int p2=0;
		
		List<Integer> list = new ArrayList<>((arr1.length+arr2.length)<<2);
		
		while(p1<arr1.length && p2 < arr2.length) {
			if(arr1[p1]==arr2[p2]) {
				list.add(arr1[p1++]);
				p2++;
			}else if(arr1[p1]<arr2[p2]){
				p1++;
			}else{
				p2++;
			}
		}
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		
		System.out.println(list);
		
	}
}
