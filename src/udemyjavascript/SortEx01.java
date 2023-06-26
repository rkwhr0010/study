package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
//		list.add(new InsertSort());
		
		for(SortStategy<Integer> sort : list) 
			System.out.printf("%-20s  %s%n",sort.getClass().getSimpleName(),
					Arrays.toString(sort.sort(array)));
	}
	
	static interface SortStategy<T>{
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
	
	static class InsertSort implements SortStategy<Integer>{
		//논리적으로 순회 마다 작은 서브배열을 만든다고 가정한다.
		//1부터 시작
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			//배열이 단 한개는 이미 정렬된 것이다. (논리적 배열)
			for(int i = 1;i<clone.length;i++) {
				//배열 shift 하는 동안 값 손실 때문이 임시 저장
				Integer newMember = clone[i];
				Integer j = i-1;
				//새로운 멤버 clone[i]
				for(;j>=0 && newMember < clone[j];j--) {
					clone[j+1] = clone[j];
				}
				//들어갈 자리 j-- 보정 값 +1
				clone[j+1] = newMember;
			}
			return clone;
		}
	}
}
