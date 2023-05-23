package algorithm.dynamicprogramming.practise;

import static java.lang.Math.min;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class 중요_동전교환연습{
	static int n, m;
	static int[] dy;

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
	static int so(int[] coin) {
		Arrays.fill(dy, Integer.MAX_VALUE);
		for(int i=0;i<coin[0];i++) dy[i]=0;
		for(int i=0;i<coin.length;i++) {
			for(int j=coin[i];j<=m;j++) {
				dy[j] = min(dy[j], dy[j-coin[i]]+1);
			}
		}
		return dy[m];
	}
	static int so123(final int[] coin) {
		//최대 개수로 초기화
		Arrays.fill(dy, Integer.MAX_VALUE);
		//단, 첫 코인 전까지는 0을 유지한다.
		IntStream.range(0, coin[0]).forEach((i)->dy[i]=0);
		//메인 순회
		
		return dy[m];
	}
	
	
	private static String format(int index) {
		String tmp = String.valueOf(dy[index]);
		return tmp.substring(0,tmp.length()>=3 ? 3 : tmp.length());
	}
	
	
	static Integer[] kind = {1, 8,  20, 25,50};
	static Integer money = 129;
	static Integer answer = Integer.MAX_VALUE;
	private static void DFS(int cnt, int sum) {
		if(sum> money || cnt > answer ) ;
		else if(sum == money) {
			answer = Math.min(cnt, answer);
		}
		else {
			for (int i = 0; i < kind.length; i++) {
				DFS(cnt+1, sum+kind[i]);
				
			}
		}
	}
	static void D2(int cnt, int sum) {
		if(cnt>money ||sum>money) return;
		else if(sum == money) answer = Math.min(answer, cnt);
		else {
			for(int i=0;i<kind.length;i++) {
				D2(cnt+1,sum+kind[i]);
			}
		}
	}
	
	public static void main(String[] args){
		int[] arr = IntStream.generate(()->ThreadLocalRandom.current().nextInt(1,11))
			.distinct()
			.limit(5)
			.sorted() //필수
			.toArray();
//		arr = new int[]{1, 8,  20, 25,50};
		m=129;
		dy=new int[m+1];
		n=arr.length;
//		DFS(0,0);
		System.out.println(Arrays.toString(arr));
		System.out.println("money : "+m + "/"+so(arr));
		System.out.println(Arrays.toString(dy));
//		System.out.println(answer);
	}
}
/*
3
1 2 5
15

3
 */
