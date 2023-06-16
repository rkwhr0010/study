package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class SortEx02 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(-20,20).distinct().limit(15).boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array));
		
		List<SortStategy<Integer>> list = new ArrayList<>();
		
		list.add(new MergeSort());
		
		for(SortStategy<Integer> sort : list) {
			System.out.println(Arrays.toString(sort.sort(array)));
		}
		
		
		
		
	}
	
	static interface SortStategy<T>{
		T[] sort(T[] arr);
		
		default void swap(Integer[] clone, int i, int j) {
			Integer tmp = clone[i];
			clone[i] = clone[j];
			clone[j] = tmp;
		}
		
	}
	
	static class MergeSort implements SortStategy<Integer>{
		public Integer[] sort(Integer[] arr) {
			//두 개의 배열로 나눈다.
			int mid = arr.length/2;
			Integer[] ltArr = Arrays.copyOfRange(arr, 0, mid);
			Integer[] rtArr = Arrays.copyOfRange(arr,mid,arr.length);
			
			//각 배열은 길이가 1~0일 때까지 계속 재귀호출한다.
			if(ltArr.length>1) ltArr = sort(ltArr);
			if(rtArr.length>1) rtArr = sort(rtArr);
			return merge(ltArr,rtArr);
		}
		
		
		//두 배열은 정렬되어있다고 가정
		private Integer[] merge(Integer[] arr1, Integer[] arr2) {
			Integer[] newArr = new Integer[arr1.length+arr2.length];
			int i=0,j=0,index=0;
			while(i<arr1.length && j<arr2.length) {
				if(arr1[i]>arr2[j]) newArr[index++] = arr2[j++];
				else newArr[index++] = arr1[i++];
			}
			while(i<arr1.length)
				newArr[index++] = arr1[i++];
			while(j<arr2.length)
				newArr[index++] = arr2[j++];
			return newArr;
		}
	}
	
	
}
