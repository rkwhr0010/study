package udemyjavascript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class SortEx02 {
	public static void main(String[] args) {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		Integer[] array = current.ints(0,300).distinct().limit(15).boxed().toArray(Integer[]::new);
		
		List<SortStategy<Integer>> list = new ArrayList<>();
		list.add(new MergeSortLogicalArr());
		list.add(new MergeSortPhysicalArr());
		list.add(new QuickSortLeft());
		list.add(new QuickSortMid());
		list.add(new RadixSort());
		
		System.out.println(array.getClass().getSimpleName()+"  "+ Arrays.toString(array)+"\n");
		for(SortStategy<Integer> sort : list) 
			System.out.printf("%-20s  %s%n",sort.getClass().getSimpleName(),
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
	/*
	 * 물리적 배열로 나누지 않고 논리적 배열로 나눠 처리한다.
	 */
	static class MergeSortLogicalArr implements SortStategy<Integer>{
		Integer[] tmpArr;
		Integer[] arr;
		
		public Integer[] sort(Integer[] arr) {
			tmpArr = new Integer[arr.length]; //배열 정렬 시 사용할 임시 배열
			this.arr = arr.clone();
			return sort(0, arr.length-1); //정렬을 index기준으로 다룰 것
		}
		private Integer[] sort(int left, int right) {
			int mid = (left+right)/2;
			//종료 조건, left 가 mid와 같다면 논리적 배열 길이는 1이다.
			if(left!= mid) sort(left,mid);
			//종료 조건, right 가 mid+1와 같다면 논리적 배열 길이는 1이다.
			if(right!= mid+1) sort(mid+1,right);
			return merge(left,mid,right);
		}
		
		private Integer[] merge(final int left,final int mid, final int right) {
			int lt = left;
			int ltEnd = mid;
			int rt = mid+1 ;
			int rtEnd = right;
			
			//일단 임시배열에 두 논리적 배열 값을 채움, 정렬 배열에 값을 덮어씌울 때 값 손실 때문
			System.arraycopy(arr, lt, tmpArr, lt, rtEnd-lt+1);
			int i = lt;
			while(lt<=ltEnd &&rt<= rtEnd) 
				arr[i++] = tmpArr[lt]>tmpArr[rt] ? tmpArr[rt++] : tmpArr[lt++];
			while(lt<=ltEnd)
				arr[i++] = tmpArr[lt++];
			while(rt<= rtEnd)
				arr[i++] = tmpArr[rt++];
			
			return arr;
		}
	}
	
	static class MergeSortPhysicalArr implements SortStategy<Integer>{
		public Integer[] sort(Integer[] arr) {
			/* 
			 * 종료 조건
			 * 길이가 1일 때까지 배열을 두 개로 나눈다.
			 */
			if(arr.length == 1) return arr;
			Integer mid = arr.length/2;
			Integer[] ltArr = Arrays.copyOfRange(arr, 0, mid);
			Integer[] rtArr = Arrays.copyOfRange(arr, mid, arr.length);
			
			return merge(sort(ltArr), sort(rtArr));
		}
		/*
		 * 정렬된 두 배열을 정렬된 하나의 배열로 합친다.
		 * 재미난 것은 길이가 1인 배열은 원소가 하나이기 때문에 정렬된 상태다.
		 * 따라서 길이가 1인 배열 두 개를 합치는 것은 정렬된 배열 두 개를 합치는 것과 같다.
		 * 이를 이용해 병합 정렬은 길이가 1이 될 때까지 배열을 나눈 후 합치는 과정을 거친다.
		 */
		private Integer[] merge(Integer[] lArr, Integer[] rArr) {
			Integer[] mArr = new Integer[lArr.length+rArr.length];
			Integer i=0;
			Integer lt=0;
			Integer rt=0;
			while(lt<lArr.length&&rt<rArr.length) 
				mArr[i++] = lArr[lt]< rArr[rt] ? lArr[lt++] : rArr[rt++];
			while(lt < lArr.length)
				mArr[i++] = lArr[lt++];
			while(rt < rArr.length)
				mArr[i++] = rArr[rt++];
			return mArr;
		}
		
	}
	
	static class QuickSortLeft implements SortStategy<Integer>{
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
	}
	
	static class QuickSortMid implements SortStategy<Integer>{
	    public Integer[] sort(Integer[] arr) {
	    	return sort(arr.clone(), 0, arr.length-1);
	    }

	    Integer[] sort(final Integer[] arr,final Integer left,final Integer right) {
	    	//left, right 파라미터는 훼손되선 안된다.
	    	Integer lt = left;
	    	Integer rt = right;
	    	Integer pivot = arr[(lt+rt)/2];
	    	
	    	//out while이 끝나면 1회 끝, 
	    	while(lt<=rt) {
	    		while(arr[lt]<pivot) lt++;
	    		while(pivot<arr[rt]) rt--;
	    		//같은 자리인 경우도 스왑한다. 이 경우 lt-rt가 rt-lt 와 같이 크로스된다.
	    		if(lt<=rt) swap(arr, lt++, rt--);
	    		//lt==rt   =>  lt++, rt-- => rt lt 
	    	}
	    	
	    	//종료 조건
	    	//left == rt 위치가 같아질 때까지 범위를 접어가며 재귀호출한다. log N
	    	if(left<rt) sort(arr, left, rt);
	    	//lt == right 위치가 같아질 때까지 범위를 접어가며 재귀호출한다. log N
	    	if(lt<right) sort(arr, lt, right);
	    	
	    	return arr;
	    }
	}
	
	
}
