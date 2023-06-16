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
		public Integer[] sort(Integer[] arr) {
			Integer[] clone = arr.clone();
			//앞 요소는 정렬되어 있다고 취급
			//횟 수용 반복문
			System.out.println();
			for(int i=1;i<clone.length;i++) {
				System.out.println(Arrays.toString(clone));
				int tmp = clone[i]; // 정렬 대상
				int j=i-1; //의미없는 초기화
				//실제 정렬용 반복문
				for(;j>=0;j--) {
					//tmp 값이 더 클때까지 기존배열 값을 쉬프트한다.
					if(clone[j]>tmp) clone[j+1]=clone[j];
					else break;
				}
				clone[j+1] = tmp;
			}
			return clone;
		}
/*
		for(int i=1; i<n; i++){
			int tmp=arr[i], j;
			for(j=i-1; j>=0; j--){
				if(arr[j]>tmp) arr[j+1]=arr[j];
				else break;
			}
			arr[j+1]=tmp;
		}
		return arr;
 * */
		public Integer[] sort2(Integer[] arr) {
			Integer[] result = new Integer[1];
			result[0] = arr[0];//명시적으로 하나 요소일 땐 이미 정렬된 것
			
			//1부터 시작
			for(int i=1;i<arr.length;i++) {
				result = sortHelper(result, arr[i]);
			}
			return result;
		}
		
		//구조상 이미 정렬된 배열만 들어온다.
		private Integer[] sortHelper(Integer[] arr, Integer newMember) {
			Integer[] newArr = new Integer[arr.length+1];
			int insertIndex = -1;
			for(int i = 0;i<arr.length;i++) {
				//신규값이 더 커지는 지점 탐색
				if(arr[i]<newMember) {
					insertIndex = i;
					break;
				}
			}
			//못찾으면 가장 작은 것
			insertIndex = insertIndex == -1 ? 0 : insertIndex;
			
			for(int i = 0;i<insertIndex;i++) 
				newArr[i] = arr[i];
			
			newArr[insertIndex] = newMember;
			
			for(int i = insertIndex+1;i<newArr.length;i++) {
				newArr[i] = arr[i-1];
			}
			return newArr;
		}
		
	}
	
	
}
