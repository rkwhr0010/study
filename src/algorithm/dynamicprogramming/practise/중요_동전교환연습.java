package algorithm.dynamicprogramming.practise;

import java.util.Arrays;
import static java.lang.Math.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class 중요_동전교환연습{
	static int n, m;
	static int[] dy;
	static int solution(int[] coin){
		Arrays.fill(dy, Integer.MAX_VALUE);
		IntStream.range(0, coin[0])
			.forEach(index->dy[index]=0);
		for(int i=0; i<n; i++){
			for(int j=coin[i]; j<=m; j++){
				dy[j]=Math.min(dy[j], dy[j-coin[i]]+1);
			}
		}
		return dy[m];
	}
	static int sol(int[] coin) {
		Arrays.fill(dy, Integer.MAX_VALUE);
		IntStream.range(0, coin[0])
			.forEach(index->dy[index]=0);
		for(int i=0;i<coin.length;i++) {
			for(int j=coin[i];j<=m;j++) {
				dy[j] = Math.min(dy[j],dy[j-coin[i]]+1);
			}
		}
		return dy[m];
	}
	
	static int sol2(int[] coin) {
		//배열을 전부 최대값으로 채운다. (가장 많이 소요되는 동전개수)
		Arrays.fill(dy, Integer.MAX_VALUE);
		//정렬된 코인 첫번째 값 이전까지는 전부 0으로 초기화한다.
		IntStream.range(0, coin[0])
			.forEach(i->dy[i] = 0);
		//코인만큼 반복
		int[] tmp = {0};
		for(int i=0;i<coin.length;i++) {
			//코인개수 배열은 코인 값부터 시작
			for(int j=coin[i];j<=m;j++) {
				tmp[0]= j;
				//핵심로직
				dy[j] = Math.min(dy[j], dy[j-coin[i]]+1); 
				IntStream.range(0, dy.length)
					.forEach(index -> {
						if(tmp[0] == index) System.out.print("cursor => ["+dy[index]+"] ");
						else System.out.printf(" %3s ",format(index));
					});
				System.out.println();
			}
		}
		
		return dy[m];
	}
	static int sol3(int[] coin) {
		//동전 개수 최대로 초기화
		Arrays.fill(dy, Integer.MAX_VALUE);
		//정렬된 동전 배열에 가장 작은 동전 전까지 0 으로 초기화
		for(int i = 0;i<coin[0];i++) {
			dy[i] = 0 ;
		}
		//동전 종류별로 그리디 시작
		for(int i=0;i<coin.length;i++) {
			//동전 개수 배열 순회
			for(int j =coin[i];j<dy.length;j++) {
				//현위치 동전개수가 전에 계산된 동전+1보다 작냐
				dy[j] = min(dy[j], dy[j-coin[i]]+1);
			}
		}
		return dy[m];
	}
	
	private static String format(int index) {
		String tmp = String.valueOf(dy[index]);
		return tmp.substring(0,tmp.length()>=3 ? 3 : tmp.length());
	}
	
	
	public static void main(String[] args){
		int[] arr = IntStream.generate(()->ThreadLocalRandom.current().nextInt(1,11))
			.distinct()
			.limit(3)
			.sorted() //필수
			.toArray();
		m=30;
		dy=new int[m+1];
		n=arr.length;
		System.out.println(Arrays.toString(arr));
		System.out.println("money : "+m + "/"+sol3(arr));
		System.out.println(Arrays.toString(dy));
	}
}
/*
3
1 2 5
15

3
 */
