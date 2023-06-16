package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class SortEx01 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(-20,20).distinct().limit(15).boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(array));
		
		List<SortStategy<Integer>> list = new ArrayList<>();
		list.add(new BubbleSort());
		list.add(new SelectionSort());
		list.add(new InsertSort());
		
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
						swap(clone, j-1, j);
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
						swap(clone, j-1, j);
					}
				}
			}
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
		//따라서 첫 순회 [0]은 이미 정렬됐다고 가정한다.
		//그 다음 [0][1]이 되야 정렬 비교할게 생긴다.
		//1부터 시작
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			for(int i=1;i<clone.length;i++) {
				Integer newValue = clone[i];//데이터 임시보관
				Integer j = i-1;
				for(;j>=0 && newValue<clone[j];j--)
					//신규 값이 들어갈 자리 만들기
					clone[j+1] = clone[j];
				//신규값보다 더 작은 위치 [j]에 그 앞자리 +1 이 신규값자리
				clone[j+1] = newValue;
			}
			return clone;
		}
	}
}
