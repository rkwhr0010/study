package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SortEx02 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(0,50).distinct().limit(8).boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array));
		
		List<SortStategy<Integer>> list = new ArrayList<>();
		
		list.add(new MergeSort());
		list.add(new QuickSort());
		
		for(SortStategy<Integer> sort : list) {
			System.out.println(Arrays.toString(sort.sort(array))+"\n");
		}
		System.out.println(Arrays.toString(new QuickSort().pivot2(array.clone(),0,array.length-1))+"\n");
		
		
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
			//더 이상 나눌 수 없는 배열은 리턴
			if(arr.length<=1) return arr;
			//두 개의 배열로 나눈다.
			int mid = arr.length/2;
			Integer[] ltArr = Arrays.copyOfRange(arr, 0, mid);
			Integer[] rtArr = Arrays.copyOfRange(arr,mid,arr.length);
			return merge(sort(ltArr),sort(rtArr));
		}
		
		
		//두 배열은 정렬되어있다고 가정
		private Integer[] merge(Integer[] arr1, Integer[] arr2) {
			Integer[] newArr = new Integer[arr1.length+arr2.length];
			int i=0,j=0,index=0;
			while(i<arr1.length && j<arr2.length) {
				if(arr1[i]>arr2[j]) 
					newArr[index++] = arr2[j++];
				else  
					newArr[index++] = arr1[i++];
			}
			while(i<arr1.length)
				newArr[index++] = arr1[i++];
			while(j<arr2.length)
				newArr[index++] = arr2[j++];
			return newArr;
		}
	}
	static class QuickSort implements SortStategy<Integer>{
		public Integer[] sort(Integer[] arr) {
			return sort(arr.clone(),0,arr.length-1);
		}
		
		public Integer[] sort(Integer[] arr,Integer lt, Integer rt) {
			if(lt<rt) {
				Integer pivotIndex = pivot(arr, lt, rt);
				sort(arr,lt,pivotIndex-1);
				sort(arr,pivotIndex+1,rt);
			}
			return arr;
		}
		
		private Integer pivot(Integer[] arr, Integer start, Integer end) {
			Integer pivot = arr[start];//좌측부터 시작
			int swapIdx =start;
			for(int i=start+1;i<arr.length;i++) 
				if(pivot>arr[i] ) swap(arr, ++ swapIdx, i);
			swap(arr,swapIdx,start);
			return swapIdx;
		}
		
		private Integer[] pivot2(Integer[] arr, Integer left, Integer right) {
			int pL = left;
			int pR = right;
			int pivot = arr[(pL+pR)/2];
			do {
				while(arr[pL]<pivot) pL++;
				while(arr[pR]>pivot) pR--;
				if(pL<=pR) swap(arr, pL++, pR--); 
			} while(pL<=pR);
			if(left <pR) pivot2(arr,left,pR);
			if(pL<right) pivot2(arr,pL,right);
			
			return arr;
		}
		
		
		
		
	}
	
}
