package twopointer;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class 두배열합치기 {
	
	public static void main(String[] args) {
		//핵심 1 정렬
		int[] arr1 = ThreadLocalRandom.current().ints(20, 1, 100).sorted().toArray();
		int[] arr2 = ThreadLocalRandom.current().ints(20, 1, 100).sorted().toArray();
		
		int size = arr1.length+arr2.length;
		int[] newArr = new int[size];
		
		//메인 포인터
		int p1 = 0;
		int p2 = 0;
		//List 컬렉션으로 대체가능
		int i = 0;
		
		while(p1<arr1.length && p2<arr2.length) {
			if(arr1[p1]<arr2[p2]) {
				newArr[i++]=arr1[p1++];
			}else {
				newArr[i++]=arr2[p2++];
			}
		}
		
		while(p1<arr1.length) 	newArr[i++]=arr1[p1++];
		while(p2<arr2.length) 	newArr[i++]=arr1[p2++];
		
		int[] answer = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
			.sorted()
			.toArray();
		
		System.out.println(Arrays.toString(newArr));
		System.out.println(Arrays.toString(answer));
		
		
		
	}
	
	
}
