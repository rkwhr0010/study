
import java.util.*;
//오름차순 기준
//선택정렬은 앞자리부터 작은 수를 채워간다.
//즉, 1번째 순회 시 맨 앞자리에 가장 작은 수가 오고
//그 다음은 2 번째 순회 시 그 다음 자리에 그 다음으로 작은 수가 온다.
class 선택정렬_ {	
	public static void main(String[] args){
		int[] arr = new Random().ints(10, 50, 101)
		            .toArray();
		// i의 역할은 인덱스로 순회하면서 가장 작은 수를 채운다.
		for(int i=0;i<arr.length-1;i++) {
			//j 역할은 i 마다 반복하면서 arr[i]보다 작은 수를 검사해 자리를 바꿔치기한다.
			System.out.println("i 순회 중 = "+ i);
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					System.out.println("arr["+i+"]"+arr[i]+" <==> arr["+j+"]="+arr[j]);
					for(int z=0;z<j;z++)System.out.print("    ");
					System.out.println("  *");
					System.out.println(Arrays.toString(arr));
					
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					
					System.out.println(Arrays.toString(arr));
					System.out.println("========================================");
				}
			}
		}
		System.out.println("\n"+Arrays.toString(arr));
	}
}