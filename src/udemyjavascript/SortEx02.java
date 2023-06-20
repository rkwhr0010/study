package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import udemyjavascript.SortEx02.QuickSort;

public class SortEx02 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(0,50).distinct().limit(8).boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array));
		
		List<SortStategy<Integer>> list = new ArrayList<>();
		
		list.add(new MergeSort());
		list.add(new QuickSort());
		
		for(SortStategy<Integer> sort : list) 
			System.out.println(sort.getClass().getSimpleName()+"  "+ 
					Arrays.toString(sort.sort(array)));
		
		
		
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
	    	return sort(arr.clone(), 0, arr.length-1);
	    }

	    Integer[] sort(Integer[] arr, int left, int right) {
	    	//입력값 훼손하지 않기 위한 로컬변수
	    	int lt = left; 
	    	int rt = right;
	    	int pivot = arr[(left+right)/2];
	    	
	    	//do-while 종료가 한 회차 완료 
	    	do {
		    	//lt는 피봇보다 작아야지! 통과
		    	while(arr[lt]<pivot) lt++;
		    	//rt는 피봇보다 커야지! 통과
		    	while(arr[rt]>pivot) rt--;
		    	//정상 조건일 때만 스왑
		    	if(lt<=rt) swap(arr, lt++, rt--);//정렬 후 볼일 끝난 위치는 갱신한다 --, ++
	    	}while(lt<=rt);//lt가 rt보다 더 커질때까지 반복(크로스 된다)
	    	/*
	    	while(lt<=rt) {
	    		while(arr[lt]<pivot) lt++;
	    		while(arr[rt]>pivot) rt--;
	    		if(lt<=rt) swap(arr, lt++, rt--);
	    	}
	    	System.out.println(Arrays.toString(arr));
	    	*/
	    	
	    	//재귀 종료조건, left 기준 rt가 점점 다가올 것, 그래서 rt가 클 때까지 재귀
	    	if(left<rt) sort(arr, left, rt);
	    	//재귀 종료조건, right 기준 lt가 점점 다가올 것, 그래서 lt가 작을 때까지 재귀
	    	else if(right>lt) sort(arr, lt, right);
	    	
	    	return arr;
	    }
	    
		/* 항상 좌측이 pivot일 때
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
				if(pivot>arr[i] ) 
					swap(arr, ++ swapIdx, i);
			swap(arr,swapIdx,start);
			System.out.println("lt = "+start+", rt = "+end+"\n"+Arrays.toString(arr));
			
			return swapIdx;
		}*/
	}
	
}
