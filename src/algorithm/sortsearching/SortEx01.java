package algorithm.sortsearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class SortEx01 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(0,300).distinct().limit(10).boxed().toArray(Integer[]::new);
		System.out.println(array.getClass().getSimpleName()+"   "+ Arrays.toString(array));
		
		//정렬들 저장용
		List<SortStategy<Integer>> list = new ArrayList<>();
		list.add(new BubbleSort());
		list.add(new BubbleSortOp());
		list.add(new SelectionSort());
		list.add(new InsertSort<>());
		
		for(SortStategy<Integer> sort : list) 
			System.out.printf("%-20s  %s%n",sort.getClass().getSimpleName(),
					Arrays.toString(sort.sort(array)));
	}
	
	static interface SortStategy<T extends Comparable<T>>{
		T[] sort(T[] arr);
		//자리 바꿈 메서드
		default void swap(Integer[] clone, int i, int j) {
			Integer tmp = clone[i];
			clone[i] = clone[j];
			clone[j] = tmp;
		}
	}
	
	static class BubbleSort implements SortStategy<Integer>{
		static int cnt = 0;
		
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			
			for(int i=0;i<clone.length-1;i++) {// n -1 번 수행
				for(int j=1;j<clone.length-i;j++) {
					cnt++;
					if(clone[j]<clone[j-1]) {
						swap(clone, j-1, j);
					}
				}
			}
			System.out.print("버블소트 cnt("+cnt+")");
			return clone;
		}
	}
	static class BubbleSortOp implements SortStategy<Integer>{
		Boolean noSwap = false;
		static int cnt = 0;
		
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			
			for(int i=0;i<clone.length-1;i++) {// n -1 번 수행용 반복문
				noSwap = true;
				for(int j=1;j<clone.length-i;j++) {//자리바꿈 루프
					cnt++;
					if(clone[j]<clone[j-1]) {
						noSwap = false; // 자리바꿈이 한 번이라도 수행됐다.
						swap(clone, j-1, j);
					}
				}
				if(noSwap) break; //자리 바꿈이 수행된적이 없으면 수행용 반복은 의미없음
			}
			System.out.print("버블소트 최적화 cnt("+cnt+")");
			return clone;
		}
	}
	
	//버블 정렬보다 나은 점은 스왑수 최소화, i루프 마다 단 한번의 스왑만 발생
	static class SelectionSort implements SortStategy<Integer>{
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			for(int i=0;i<clone.length-1;i++) {
				for(int j=i+1;j<clone.length;j++) {
					if(Integer.compare(clone[i], clone[j])>0) {
						swap(clone, i, j);
					}
				}
			}
			return clone;
		}
	}
	//앞에서 부터 미리 정렬된 배열 부분과 비교하여, 자신의 위치를 찾아 삽입한다.
	//거의 정렬된 배열에서 빠른 속도를 보인다.
	static class InsertSort<T extends Comparable<T>> implements SortStategy<T>{
		T[] clone;
		
		public T[] sort(T[] arr) {
			return cloneArrSort(arr);
		}
		//기존 배열에 복사본으로 작업을 하여 리턴한다.
		public T[] cloneArrSort(T[] arr) {
			clone = arr.clone();
			
			for(int i = 0; i < clone.length; i++) {
				shiftArr(i, clone[i]);
			}
			
			return clone;
		}
		//신규 값이 들어갈 자리까지 배열을 한 칸씩 반복적으로 밀어, 신규 값을 넣는다.
		//신규값이 비교값보다 커지는 지점이 들어갈 자리
		public void shiftArr(int i, T newVal) {
			int j = i - 1;
			for(;greaterThanNewVal(newVal, j); j--) {
				clone[j + 1] = clone[j];
			}
			//반복문 탈출 시 j-- 값을 보정해 그 자리에 넣는다.
			clone[j + 1] = newVal;
		}
		//j가 유효한 인덱스인지, 클론값이 신규값보다 큰지 판단한다.
		public boolean greaterThanNewVal(T newVal, int j) {
			return j >= 0 && clone[j].compareTo(newVal) > 0;
		}
	}
}
