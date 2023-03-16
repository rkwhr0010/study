package twopointer.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class 두배열합치기 {
	public static void main(String[] args) {
		//입력 데이터
		int[] arr1 = IntStream.generate( ()->  (int)(Math.random()*50)+1).limit(10).toArray();
		int[] arr2 = IntStream.generate( ()->  (int)(Math.random()*50)+1).limit(10).toArray();
		
		//1 정렬
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		//2 저장소
		List<Integer> list = new ArrayList<>((arr1.length+arr2.length)+(arr1.length+arr2.length)>>2 );  
		
		//3 투포인터
		int p1 = 0;
		int p2 = 0;
		
		while(p1<arr1.length && p2<arr2.length) {
			if(arr1[p1]<arr2[p2]) {
				list.add(arr1[p1++]);
			}else {
				list.add(arr2[p2++]);
			}
		}
		
		while(p1<arr1.length) list.add(arr1[p1++]);
		while(p2<arr2.length) list.add(arr2[p2++]);
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		System.err.println(list);
		
		
		
	}
}
