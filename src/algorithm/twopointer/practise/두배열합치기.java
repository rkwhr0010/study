package algorithm.twopointer.practise;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class 두배열합치기 {
	public static void main(String[] args) {
		//입력 데이터
		Integer[] arr1 = IntStream.generate( ()->  (int)(Math.random()*50)+1).distinct().limit(10).boxed().toArray(Integer[]::new);
		Integer[] arr2 = IntStream.generate( ()->  (int)(Math.random()*50+51)).distinct().limit(10).boxed().toArray(Integer[]::new);
		
		//정렬된 두 배열을 병합하는 코드
		//병합 정렬에서 사용되는 핵심 코드 중 한 쪽이다.
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		Integer[] merge = merge(arr1, arr2);
		System.out.println(Arrays.toString(merge));
		
	}
	
	static <T extends Comparable<T>> T[] merge(T[] a, T[] b) {
		@SuppressWarnings("unchecked")
		T[] resultArr = (T[])Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
		
		int i = 0;
		//투 포인터 알고리즘
		int p1 = 0;
		int p2 = 0;
		
		for(; p1 < a.length && p2 < b.length; i++) {
			if(a[p1].compareTo(b[p2]) <= 0) {
				resultArr[i] = a[p1++];
			} else {
				resultArr[i] = b[p2++];
			}
		}
		for(; p1 < a.length; i++, p1++) {
			resultArr[i] = a[p1];
		}
		for(; p2 < b.length; i++, p2++) {
			resultArr[i] = b[p2];
		}
		
		return resultArr;
	}
	
	
}


