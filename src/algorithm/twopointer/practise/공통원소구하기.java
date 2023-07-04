package algorithm.twopointer.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
설명

A, B 두 개의 집합이 주어지면 두 집합의 공통 원소를 추출하여 오름차순으로 출력하는 프로그램을 작성하세요.


입력
첫 번째 줄에 집합 A의 크기 N(1<=N<=30,000)이 주어집니다.

두 번째 줄에 N개의 원소가 주어집니다. 원소가 중복되어 주어지지 않습니다.

세 번째 줄에 집합 B의 크기 M(1<=M<=30,000)이 주어집니다.

네 번째 줄에 M개의 원소가 주어집니다. 원소가 중복되어 주어지지 않습니다.

각 집합의 원소는 1,000,000,000이하의 자연수입니다.


출력
두 집합의 공통원소를 오름차순 정렬하여 출력합니다.


예시 입력 1 

5
1 3 9 5 2
5
3 2 5 7 8

예시 출력 1

2 3 5
 */
public class 공통원소구하기 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr1 = new int[n];
		for(int i=0;i<n;i++) {
			arr1[i] = sc.nextInt();
		}
		int m = sc.nextInt();
		int[] arr2 = new int[m];
		for(int i=0;i<m;i++) {
			arr2[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(solution(arr1, arr2));
	}
	
	static List<Integer> solution(int[] arr1, int[] arr2){
		List<Integer> list = new ArrayList<>();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int p1 = 0;
		int p2 = 0;
		
		for(;p1 < arr1.length && p2 < arr2.length;) {
			if(arr1[p1] == arr2[p2]) {
				list.add(arr1[p1]);
				p1++;
				p2++;
			} else if( arr1[p1] < arr2[p2]) {
				p1++;
			} else {
				p2++;
			}
		}
		return list;
	}
	
}
