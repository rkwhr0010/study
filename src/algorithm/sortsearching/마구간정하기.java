package algorithm.sortsearching;

import java.util.Arrays;
import java.util.Scanner;

/*
마구간 정하기(결정알고리즘)
N개의 마구간이 수직선상에 있습니다. 각 마구간은 x1, x2, x3, ......, xN의 좌표를 가지며, 마
구간간에 좌표가 중복되는 일은 없습니다.
현수는 C마리의 말을 가지고 있는데, 이 말들은 서로 가까이 있는 것을 좋아하지 않습니다.
각 마구간에는 한 마리의 말만 넣을 수 있고, 가장 가까운 두 말의 거리가 최대가 되게 말을
마구간에 배치하고 싶습니다.
C마리의 말을 N개의 마구간에 배치했을 때 가장 가까운 두 말의 거리가 최대가 되는 그 최대
값을 출력하는 프로그램을 작성하세요.
▣ 입력설명
첫 줄에 자연수 N(3<=N<=200,000)과 C(2<=C<=N)이 공백을 사이에 두고 주어집니다.
둘째 줄에 마구간의 좌표 xi(0<=xi<=1,000,000,000)가 차례로 주어집니다.
▣ 출력설명
첫 줄에 가장 가까운 두 말의 최대 거리를 출력하세요.
▣ 입력예제 1
5 3
1 2 8 4 9
▣ 출력예제 1
3
 */
public class 마구간정하기 {
	static int n; // 마구간 수
	static int c; // 말 수
	static int[] arr;// 마구간
	
	static class Decision {
		private final int stallCnt;
		private final int horseCnt;
		private final int[] stallArr;
		
		public Decision(int horse, int[] stallArr) {
			this(stallArr.length, horse, stallArr);
		}
		
		public Decision(int stall, int horse, int[] stallArr) {
			this.stallCnt = stall;
			this.horseCnt = horse;
			this.stallArr = stallArr;
		}
		
		public int search() {
			int result = 0;
			// 정렬은 필수
			Arrays.sort(stallArr);
			
			// 마구간 사이 최소 거리는 1, stallArr[0] 안된다
			// 해당 값으로 lt <= rt 시 해당하는 값이 하나도 없으면 결과 자체가 않나온다.
			int lt = 1;
			// 제일 멀리 있는 마구간 거리
			int rt = stallArr[stallCnt - 1];
			
			while (lt <= rt) {
				int mid = lt + rt >> 1;
				//배치할 수 있는 말이 더 많다.
				if (count(mid) >= horseCnt) {
					result = mid;
					lt = mid + 1;
				} else {
					rt = mid - 1;
				}
			}
			
			return result;
		}
		
		private int count(int distance) {
			//말 하나 배치하고 시작
			int cnt = 1;
			for (int i = 1, prev = stallArr[0]; i < stallArr.length; i++) {
				if (stallArr[i] - prev >= distance) {
					prev = stallArr[i];
					cnt++;
				}
			}
			return cnt;
		}
	}
	
	public static void main(String[] args) {
		input();
		
		System.out.println(new Decision(n, c, arr).search());
	}

	private static void input() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		c = sc.nextInt();
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
	}
}
