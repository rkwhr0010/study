package algorithm.sortsearching;

import java.util.Arrays;
import java.util.Scanner;

/*
이분검색
임의의 N개의 숫자가 입력으로 주어집니다. N개의 수를 오름차순으로 정렬한 다음 N개의 수
중 한 개의 수인 M이 주어지면 이분검색으로 M이 정렬된 상태에서 몇 번째에 있는지 구하는
프로그램을 작성하세요. 단 중복값은 존재하지 않습니다.
▣ 입력설명
첫 줄에 한 줄에 자연수 N(3<=N<=1,000,000)과 M이 주어집니다.
두 번째 줄에 N개의 수가 공백을 사이에 두고 주어집니다.
▣ 출력설명
첫 줄에 정렬 후 M의 값의 위치 번호를 출력한다.
▣ 입력예제 1
8 32
23 87 65 12 57 32 99 81
▣ 출력예제 1
3
 */
public class 이분검색 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int target = sc.nextInt();
		
		int[] arr = new int[size];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
		
		//정렬 필수
		Arrays.sort(arr);
		int lt = 0;
		int rt = size-1;
		
		while(lt <= rt) {
			int mid = (lt+rt) >> 1;
			if(target == arr[mid]) {
				System.out.println(mid+1);
				break;
			} else if (target < arr[mid]) {
				rt = mid - 1;
			} else {
				lt = mid + 1;
			}
		}
	}
}
