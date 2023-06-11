package algorithm.etc;
import java.util.*;
//뮤직비디오(결정알고리즘)

class 뮤직비디오_ {
	private static int count(int[] arr, int mid) {
		int sum = 0, count =1; // 기본 배치를 한 개 하고 시작
		System.out.println("======="+mid+"=======");
		for(int num : arr) {
			sum+=num;
			if(mid<sum) {
				sum = num;
				count++;
			}
		}
		System.out.println("앨범 수 "+count);
		return count;
	}
	
	public static void main(String[] args){
		int answer=0;
		int[] arr = new Random().ints(10, 1,10)
//								.sorted()
				                .toArray();
		System.out.println(Arrays.toString(arr));
		int lt = arr[arr.length-1]; //가장 큰 노래 길이가 최소 한 1곡은 다 들어감
		int rt = Arrays.stream(arr).sum();//모든 노래 길이 합이 가장 큰 경우의 수
		int size = 5; // 앨범 개수
		
		
		while(lt<=rt) {
			int mid = (lt+rt)/2;
			if(count(arr, mid) <= size) {
				answer = mid;
				rt = mid-1;
			}else {
				lt = mid+1;
			}
		}
		System.out.println("앨범 당 합 곡길이 :"+answer);
		
	}
}