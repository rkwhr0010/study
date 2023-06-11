package algorithm.etc;

import java.util.*;
//오름차순 기준
//삽입정렬은 i 크기만큼 배열만 정렬하고 i를 점점 증가시킨다.
//그래서 맨 처음은 인위적으로 0인덱스 자리를 비운다. i=1 
//1번 째 순회는 길이가 2인 배열 일부를 비교,
//n번 째 순회는 길이가 n+1인 배열 일부를 비교,
//최종으로는 입력받은 배열 전체를 비교하게 된다.
//핵심은 배열의 크기가 점점 커지면서 최종적으로 입력받은 배열 크기를 비교하게 되는데
//이때 이미 내 앞의 요소는 정렬이 되어있음을 보장한다.
//이 구조는 마치 정렬된 배열에 요소를 추가하는 것과 같다.
//이미 정렬이 되어 있는 배열에 새로운 요소를 추가하는데, 순서를 지키고 싶다고 생각해보자
//뒤에서 부터 비교하면서, 나보다 작은 수가 안나올 때까지 비교할 것이다.
//만약 나보다 작은 수가 나온다면, 비교를 멈추고 그 자리에 나를 넣을 것이다.
//따라서 새로운 요소가 가장 작은 수가 아니라면, 전체를 다 순회하지 않는다. 
class 삽입정렬_ {
	public static void main(String[] args) {
		// 최대치를 높여가며 뒤부터 앞으로 정렬을 반복
		int[] arr = new Random().ints(10, 50, 100).toArray();
		for(int i = 1;i<arr.length;i++) {
			for(int j = i;j>0;j--) {
				//arr[j]가 기존 배열에 새로 추가할 요소라고 생각해보자.
				System.out.println("i 순회 중 = "+ i);
				if(arr[j]<arr[j-1] ) {
					System.out.println("arr["+j+"-1]="+arr[j-1]+" <==> arr["+j+"]="+arr[j]);
					for(int z=0;z<j-1;z++) System.out.print("    ");
					System.out.println("  *");
					System.out.println(Arrays.toString(arr));
					int tmp= arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
					System.out.println(Arrays.toString(arr));
					System.out.println("========================================");
				}else {
					//기존 배열은 정렬을 유지하고 있는 상태이다.
					//따라서, false가 나오면 그만 순회해도 되는 것이다.
					break;
				}
			}
		}
	}
}