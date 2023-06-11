package algorithm.etc;

import java.util.*;
class 버블정렬_ {	
//뒷자리를 제일 큰 수로 채우면서 줄여간다. 
//따라서 매 순회마다 뒷자리가 차곡차곡 큰 수로 채워진다.
	public static void main(String[] args){
		int[] arr = new Random().ints(10, 50, 100).toArray();
		for(int i=0;i<arr.length-1;i++) {
			System.out.println("i 순회 중 = "+ i);
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j]>arr[j+1]) {
					System.out.println("arr["+j+"]="+arr[j]+" <==> arr["+j+"+1]="+arr[j+1]);
					for(int z=0;z<j-1;z++) System.out.print("    ");
					System.out.println("  *");
					System.out.println(Arrays.toString(arr));
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					System.out.println(Arrays.toString(arr));
					System.out.println("========================================");
				}
			}
		}
		
	}
}