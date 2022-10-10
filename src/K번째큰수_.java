
import java.util.*;
class K번째큰수_ {	
	public static void main(String[] args){
		int[] arr = new Random().ints(100, 1, 101)
		            .toArray();
		
		int k = 4; //번째 큰수
		int n = arr.length;
		int count =0;
		
		// 역순 정렬을 위해 직접 Comparator 구현
		TreeSet<Integer> set = new TreeSet<>((a,b)->Integer.compare(b, a));
		
		for(int i = 0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				for(int z=j+1;z<n;z++) {
					set.add(arr[i]+arr[j]+arr[z]);
					count++;
				}
			}
		}
		//사이즈를 넘어선 k는 예외발생
		if(set.size()<k) {
			System.out.println("-1 not found");
		}
		int cnt = 1;
		int sum = 0;
		
		for(Iterator<Integer> it = set.iterator();it.hasNext() ;cnt++) {
			sum = it.next();
			System.out.print(sum+",");
			if(cnt==k) {
				System.out.println("\n"+k+"번째 큰 수 : "+ sum);;
				break;
			}
		}
		System.out.println("경우의 수 : " +count);
	}
}