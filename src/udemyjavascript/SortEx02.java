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
		
		for(SortStategy<Integer> sort : list) {
			System.out.println(Arrays.toString(sort.sort(array)));
		}
		
		Integer[] array2 = current.ints(1, 100).distinct().limit(10).boxed().sorted().toArray(Integer[]::new);
		Integer[] array3 = current.ints(1, 100).distinct().limit(10).boxed().sorted().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));
		
		MergeSort mergeSort = new MergeSort();
		System.out.println(Arrays.toString(mergeSort.merge(array2, array3)));
		
		
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
			Integer[] clone = arr.clone();
			
			
			
			return clone;
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
