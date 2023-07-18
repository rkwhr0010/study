package designpattern.behavioral.strategy;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortEx {
	public static void main(String[] args) {
		Client client = new Client() {
			void run() {
				SortMachine machine = new SortMachine();
				int[] array = createIntArr();
				
				//이미 정렬이 거의 다되어 있네?
				machine.setSortable(new InsertSort());
				machine.sort(array);
				System.out.println(Arrays.toString(array));
				//일반적인 n long n 정렬
				machine.setSortable(new MergeSort());
				array = createIntArr();
				machine.sort(array);
				System.out.println(Arrays.toString(array));
				
			}

			private int[] createIntArr() {
				return ThreadLocalRandom.current().ints(1, 50).distinct().limit(10).toArray();
			}
		};
		client.run();
	}
	
	
	
	//전략
	static interface Sortable{
		void sort(int[] arr);
	}
	//전략 구현체
	static class InsertSort implements Sortable{
		@Override
		public void sort(int[] arr) {
			//0 하나는 정렬돼 있다.
			for(int i = 1; i < arr.length; i++) {
				//새로운 멤버
				int newMember = arr[i];
				int j = i - 1;
				//논리 배열에 새로운 멤버 추가 자리 마련
				//j가 더 크면 한자리씩 민다.
				while(j >= 0 && newMember < arr[j]) {
					arr[j + 1] = arr[j];
					j--;
				}
				//핵심, 다 밀었다고 가정, 그 다음 앞에 대입
				arr[++j] = newMember;
			}
		}
	}
	static class MergeSort implements Sortable{
		int[] arr;
		int[] swapArr;
		@Override
		public void sort(int[] arr) {
			this.arr = arr;
			this.swapArr = arr.clone();
			split(0, arr.length-1);
		}
		public void split(int lt, int rt) {
			//핵심 논리 배열 두 개을 구분하는 중간 인덱스
			int mid = lt + rt >> 1;
			if(lt != mid) split(lt, mid);
			if(rt != mid+1) split(mid + 1, rt);
			
			merge(lt, mid, rt);
		}
		
		private void merge(int lt, int mid, int rt) {
			System.arraycopy(arr, lt, swapArr, lt, 1 + rt - lt);
			int ltStart = lt;
			int ltEnd = mid;
			int rtStart = mid + 1;
			int rtEnd = rt;
			
			int i = lt;
			while(ltStart <= ltEnd && rtStart <= rtEnd) {
				arr[i++] = swapArr[ltStart] < swapArr[rtStart] 
						? swapArr[ltStart++] : swapArr[rtStart++];
			}
			while(ltStart <= ltEnd) {
				arr[i++] = swapArr[ltStart++];
			}
			while(rtStart <= rtEnd) {
				arr[i++] = swapArr[rtStart++];
			}
			
		}
	}
	
	
	//문맥
	static class SortMachine{
		Sortable sortable;
		public SortMachine() {}
		
		public SortMachine(Sortable sortable) {
			this.sortable = sortable;
		}
		public void setSortable(Sortable sortable) {
			this.sortable = sortable;
		}
		void sort(int[] arr) {
			sortable.sort(arr);
		}
	}
	//클라이언트
	abstract static class Client{
		abstract void run();
	}
	
	
}
