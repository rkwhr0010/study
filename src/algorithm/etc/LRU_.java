package algorithm.etc;
import java.util.*;
//Least Recently Used
class LRU_ {	
	public static void main(String[] args){
		int[] arr = new Random().ints(20, 1, 11)
		            .toArray();
		int size = 5; //캐시 사이즈
		int[] cache = new int[size];
		
		System.out.println(Arrays.toString(arr));
		//전체 작업을 순회
		for(int i=0;i< arr.length;i++) {
			int tmpIndex = -1;
			int value = arr[i];
			//캐시에 이미 존재하는지
			for(int j=0;j<cache.length;j++) {
				if(cache[j] == value) {
					tmpIndex=j;
					break;
				}
			}
			//캐시에 존재할 경우 그 자리를 제외하고 뒤로 밀기
			int j = tmpIndex != -1 ? tmpIndex : cache.length-1;
			for(; 0<j;j--) {
				cache[j] = cache[j-1];
			}
			cache[0] = arr[i];
			System.out.println(Arrays.toString(cache)+"입력값 =" + arr[i]);
		}
	}
}
