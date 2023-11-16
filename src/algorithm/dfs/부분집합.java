package algorithm.dfs;

/**
 * 깊이 우선 탐색은 무조건 끝까지 탐색한다. 
 * 깊이를 10 지정 시 10까지 다 탐색 후 그 결과를 기반으로 값을 도출
 */
public class 부분집합 {
	public static void main(String[] args) {
		new Subset(3).subset(1);
		System.out.println();
		new Subset(3).subset2(1);
	}
	
	static class Subset {
		private final int number;
		private final int[] numberChk;
		
		public Subset(int number) {
			this.number = number;
			this.numberChk = new int[number + 1];
		}
		
		void subset(int start) {
			if (start > number) {
				for (int i = 1; i < numberChk.length; i++) {
					if (numberChk[i] != 0) {
						System.out.print(numberChk[i] + " ");
					}
				}
				System.out.println();
			} else {
				//경우의 수 1
				subset(start + 1);
				numberChk[start] = start;
				//경우의 수 2
				subset(start + 1);
				numberChk[start] = 0;
			}
		}
		
		void subset2(int start) {
			if (start > number) {
				for (int i = 1; i < numberChk.length; i++) {
					if (numberChk[i] != 0) {
						System.out.print(numberChk[i] + " ");
					}
				}
				System.out.println();
			} else {
				//경우의 수 1
				numberChk[start] = start;
				subset2(start + 1);
				//경우의 수 2
				numberChk[start] = 0;
				subset2(start + 1);
			}
		}
	}
}
