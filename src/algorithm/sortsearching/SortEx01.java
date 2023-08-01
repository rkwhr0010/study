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
		public T[] sort3(T[] arr) {
			T[] clone = arr.clone();
			//요소가 1개인 배열은 이미 정렬된 것, 따라서 1부터 시작한다.
			for(int i = 1; i < clone.length; i++) {
				//자리 마련 동안 이 값이 손실될 수 있어 임시 저장
				T newMember = clone[i];
				//정렬 시작
				int j = i-1;
				//newMember가 들어갈 자리를 마련하기 위한 반복문
				for(; j>=0 && greaterThan(clone[j], newMember); j--) {
					clone[j+1] = clone[j];
				}
				//핵심, 다 밀었다고 가정, 그 다음 앞에 대입
				clone[++j] = newMember;
			}
			return clone;
		}
		private boolean greaterThan(T left, T right) {
			return left.compareTo(right) > 0;
		}
		/*
		 * 삽입정렬은 논리적으로 작은 배열부터, 신규 멤버를 추가한다고 생각해야한다.
		 * 즉, 원본배열에서 논리적으로 작은 서브배열이 있다고 생각하고 점차 키워가며 정렬한다.
		 */
		public T[] sort(T[] arr) {
			//같은 크기 임시 저장소
			T[] clone = arr.clone();
			
			//명시적으로 첫 값은 이미 정렬된 것이나 다름 없다.
			//하지만 별차이 없으므로 그냥 0부터 시작한다.
			for(int i = 0; i < clone.length; i++) {
				clone[arrShift(clone, i)] = arr[i];
			}
			
			return clone;
			
		}
		//필요한 만큼 쉬프트한 배열 인덱스를 리턴한다
		public int arrShift(T[] clone, int i) {
			T newValue = clone[i];
			int j = i - 1 ;
			
			while(isArrShift(clone, newValue, j)) {
				clone[j + 1] = clone[j];
				j--;
			}
			return j + 1;
		}
		public boolean isArrShift(T[] clone, T newValue, int j) {
			return j >= 0 && !(newValue.compareTo(clone[j]) > 0);
		}
	}
}
