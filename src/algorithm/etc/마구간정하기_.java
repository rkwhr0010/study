package algorithm.etc;
import java.util.*;
//마구간 정하기(결정알고리즘)
/*
5 3
1 2 8 4 9
*/
class 마구간정하기_ {

	private int count(int[] arr, int mid) {
		System.out.println("======"+mid+"======");
		int cnt = 1; // 맨 처음을 당연히 배치하니 1을 둔다.
		int position = arr[0];
		
		System.out.print(position+",");
		for(int i = 1 ; i < arr.length ; i++){
			if(arr[i]-position >= mid ) {
				System.out.print(arr[i]+",");
				cnt++;
				position = arr[i];
			}
		}
		System.out.println();
		
		return cnt;
	}

	public static void main(String[] args){
		
		마구간정하기_ T = new 마구간정하기_();
		int[] arr = new Random().ints(1, 30)
				                .distinct()
				                .limit(10)
				                .sorted()
				                .toArray();
		int c = 5;
		
		System.out.println(Arrays.toString(arr));
		int answer = 0;
		int lt = 1; // 주의할 것 arr[0]로 하면 답이 안 나올 수 있음
		int rt = arr[arr.length-1];
		
		while(lt<=rt) {
			int mid = (lt+rt)/2;
			if(T.count(arr, mid) >= c ) {
				//가능하니 거리를 넓여본다.
				answer = mid;
				lt = mid+1;
			}else {
				//불가능하니 거리를 좁혀본다.
				rt = mid-1;
			}
			
			
		}
		System.out.println(c+" 배치 가능한 적절한 거리 = "+answer);
		
	}
}