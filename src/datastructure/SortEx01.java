package datastructure;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortEx01 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(1,2000).distinct().limit(1000).boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array));
		BubbleSort bubbleSort = new BubbleSort();
		Integer[] sort = bubbleSort.sort(array);
		Integer[] sort2 = bubbleSort.sort2(array);
		System.out.println(Arrays.toString(sort));
		System.out.println(BubbleSort.cnt1+"  "+BubbleSort.cnt2);
	}
	
	static interface SortStategy<T>{
		T[] sort(T[] arr);
	}
	
	static class BubbleSort implements SortStategy<Integer>{
		Boolean noSwap = false;
		static int cnt1 = 0;
		static int cnt2 = 0;
		
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			
			for(int i=0;i<clone.length-1;i++) {// n -1 번 수행용 반복문
				noSwap = true;
				for(int j=1;j<clone.length-i;j++) {//자리바꿈 루프
					cnt1++;
					if(clone[j]<clone[j-1]) {
						noSwap = false; // 자리바꿈이 한 번이라도 수행됐다.
						Integer tmp = clone[j-1];
						clone[j-1] = clone[j];
						clone[j] = tmp;
					}
				}
				if(noSwap) break; //자리 바꿈이 수행된적이 없으면 수행용 반복은 의미없음
			}
			return clone;
		}
		public Integer[] sort2(Integer[] arr) {
			Integer[] clone = arr.clone();
			
			for(int i=0;i<clone.length-1;i++) {// n -1 번 수행
				for(int j=1;j<clone.length-i;j++) {
					cnt2++;
					if(clone[j]<clone[j-1]) {
						Integer tmp = clone[j-1];
						clone[j-1] = clone[j];
						clone[j] = tmp;
					}
				}
			}
			return clone;
		}
	}
	
}
