package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

import java.lang.reflect.Array;

public class SortEx02 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(0,100).distinct().limit(8).boxed().toArray(Integer[]::new);
		
		List<SortStategy<Integer>> list = new ArrayList<>();
		list.add(new MergeSortLogicalArr<>());
		list.add(new MergeSortPhysicalArr<>());
		list.add(new QuickSortLeft<>());
		list.add(new QuickSortMid<>());
		list.add(new RadixSort());
		
		System.out.println(array.getClass().getSimpleName()+"  "+ Arrays.toString(array)+"\n");
		for(SortStategy<Integer> sort : list) 
			System.out.printf("%-20s  %s%n",sort.getClass().getSimpleName(),
					Arrays.toString(sort.sort(array)));
		
	}
	
	
	static interface SortStategy<T extends Comparable<T>>{
		T[] sort(T[] arr);
		
		default void swap(T[] clone, int i, int j) {
			T tmp = clone[i];
			clone[i] = clone[j];
			clone[j] = tmp;
		}
		
	}
	
	/*
	 * 물리적 배열로 나누지 않고 논리적 배열로 나눠 처리한다.
	 */
	static class MergeSortLogicalArr<T extends Comparable<T>> implements SortStategy<T>{
		T[] swapArr;
		T[] clone;
		
		@Override
		public T[] sort(T[] arr) {
			swapArr = arr.clone(); //스왑용 배열
			clone = arr.clone();   //원본 배열 손상 방지
			return split(0,arr.length-1);
		}
		//배열을 논리적으로 쪼갠다.
		private T[] split(int lt, int rt) {
			int mid = (lt+rt)/2;
			if(lt != mid) split(lt,mid);
			if(rt != mid+1) split(mid+1,rt);
			return mergeSort(lt, mid, rt);
		}
		//논리적으로 쪼개진 두 배열을 입력으로 받아, 병합하면서 정렬한다.
		private T[] mergeSort(int lt, int mid, int rt) {
			//병합 정렬 중 덮어씌워지는 값을 스왑용 배열에 저장해둔다.
			System.arraycopy(clone, lt, swapArr, lt, 1+rt-lt);
			
			int ltS = lt;
			int ltE = mid;
			int rtS = mid+1;
			int rtE = rt;
			
			int i = lt;
			while(ltS <= ltE && rtS <= rtE) {
				clone[i++] = swapArr[ltS].compareTo(swapArr[rtS]) <= 0 
						? swapArr[ltS++] : swapArr[rtS++];
			}
			while(ltS <= ltE) {
				clone[i++] = swapArr[ltS++];
			}
			while(rtS <= rtE) {
				clone[i++] = swapArr[rtS++];
			}
			return clone;
		}
	}
	
	static class MergeSortPhysicalArr<T extends Comparable<T>> implements SortStategy<T>{
		public T[] sort(T[] arr) {
			/* 
			 * 종료 조건
			 * 길이가 1일 때까지 배열을 두 개로 나눈다.
			 */
			if(arr.length == 1) return arr;
			int mid = arr.length/2;
			T[] ltArr = Arrays.copyOfRange(arr, 0, mid);
			T[] rtArr = Arrays.copyOfRange(arr, mid, arr.length);
			
			return merge(sort(ltArr), sort(rtArr));
		}
		/*
		 * 정렬된 두 배열을 정렬된 하나의 배열로 합친다.
		 * 재미난 것은 길이가 1인 배열은 원소가 하나이기 때문에 정렬된 상태다.
		 * 따라서 길이가 1인 배열 두 개를 합치는 것은 정렬된 배열 두 개를 합치는 것과 같다.
		 * 이를 이용해 병합 정렬은 길이가 1이 될 때까지 배열을 나눈 후 합치는 과정을 거친다.
		 */
		private T[] merge(T[] lArr, T[] rArr) {
			Object newArr = Array.newInstance(lArr.getClass().getComponentType(), lArr.length + rArr.length);
			@SuppressWarnings("unchecked")
			T[] mArr = (T[]) newArr;
			int i=0;
			int lt=0;
			int rt=0;
			while(lt < lArr.length && rt < rArr.length) 
				mArr[i++] = lArr[lt].compareTo(rArr[rt]) <= 0 
					? lArr[lt++] : rArr[rt++];
			while(lt < lArr.length)
				mArr[i++] = lArr[lt++];
			while(rt < rArr.length)
				mArr[i++] = rArr[rt++];
			return mArr;
		}
		
	}
	
	static class QuickSortLeft<T extends Comparable<T>> implements SortStategy<T>{
		public T[] sort(T[] arr) {
			return sort(arr.clone(), 0, arr.length - 1);
		}
		
		public T[] sort(T[] arr, int lt, int rt) {
			if(lt < rt) {
				int pivotIndex = pivot(arr, lt, rt);
				sort(arr, lt, pivotIndex - 1);
				sort(arr, pivotIndex + 1, rt);
			}
			return arr;
		}
		
		private int pivot(T[] arr, int start, int end) {
			T pivot = arr[start];//좌측부터 시작
			int swapIdx = start;
			for(int i = start + 1 ; i < arr.length ; i++) 
				if(pivot.compareTo(arr[i]) > 0) swap(arr, ++swapIdx, i);
			swap(arr,swapIdx,start);
			return swapIdx;
		}
	}
	
	static class QuickSortMid<T extends Comparable<T>> implements SortStategy<T>{
		@Override
		public T[] sort(T[] arr) {
			return sort(arr.clone(), 0, arr.length-1);
		}
		private T[] sort(T[] arr, int left, int right) {
			int lt = left;
			int rt = right;
			T pivot = arr[lt + rt >>> 1]; //인덱스 중간 값
			
			//재귀 회차 
			while(lt <= rt) {
				//lt가 피봇보다 크면 멈춘다.(스왑을 위함)
				while(arr[lt].compareTo(pivot) < 0 ) lt++;
				//rt가 피봇보다 작으면 멈춘다.(스왑을 위함)
				while(arr[rt].compareTo(pivot) > 0 ) rt--;
				
				//lt가 rt보다 작거나 같아야 유효하다. 스왑 이후 대상 인덱스는 볼 일이 끝났으므로, 넘어간다.
				if(lt <= rt) swap(arr, lt++, rt--);
				//lt == rt 경우   같은 위치(lt++,rt--) =>  rt , lt  크로스하게 된다.
			}
			//시작 left 값은 고정이고 rt-- 가 점점 가다온다. 이때 left는 무조건 작아야 유효하다.
			//유효한 값 == 적여도 left ~ rt 사이 요소가 2개 이상
			if(left < rt) sort(arr, left, rt);
			//종료 right 값은 고정이고 lt++ 가 점점 가다온다. 이때 right는 무조건 커야 유효하다.
			//유효한 값 == 적여도 lt ~ right 사이 요소가 2개 이상
			if(lt < right) sort(arr, lt, right);
			return arr;
		}
		
	}
	
	

	static class RadixSort implements SortStategy<Integer>{
		public Integer[] sort(Integer[] arr) {
			Integer[] nums = arr.clone();
			
			final int maxDigitCount = getMaxDigitCount(nums);
			//자리수 순회용 1의 자리부터 가장 큰 자리수까지
			for(int k=0;k<maxDigitCount;k++) {
				List<List<Integer>> buckets = getBuckets();
				//메인 배열 순회용
				for(int i=0;i<nums.length;i++) {
					int digit = getDigit(nums[i], k);
					buckets.get(digit).add(nums[i]);
				}
				nums = buckets.stream()
					.flatMap(List::stream)
					.toArray(Integer[]::new);
//				for(int i = 0;i<buckets.size();i++) 
//					System.out.println(i+" = "+buckets.get(i));
//				System.out.println(Arrays.toString(nums));
			}
			return nums;
		}
		private List<List<Integer>> getBuckets(){
			return IntStream.range(0, 10)
						.mapToObj(ArrayList<Integer>::new)
						.collect(Collectors.toList());
		}
		
		//i의 자리수의 숫자를 구한다.
		private int getDigit(int num,int i) {
			return (int)(abs(num)/pow(10,i) % 10);
		}
		//주어진 숫자의 자리수를 구한다.
		private int getDigitCount(int num) {
			if(num==0) return 1;
			return (int)log10(abs(num))+1;
		}
		//주어진 배열에서 가장 큰 자리수를 구한다.
		private int getMaxDigitCount(Integer[] arr) {
			int result = -1;
			for(Integer num : arr)
				result = max(result,getDigitCount(num));
			return result;
		}
		
	}
}
