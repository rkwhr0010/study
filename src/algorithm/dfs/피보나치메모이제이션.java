package algorithm.dfs;

public class 피보나치메모이제이션 {
	public static void main(String[] args) {
		System.out.println(NoMemo.fibo(10));
		System.out.println(NoMemo.cnt);
		System.out.println(Memo.fibo(10));
		System.out.println(Memo.cnt);
	}
	
	static class NoMemo {
		static int cnt;
		
		static int fibo(int value) {
			++cnt;
			if (value == 1 || value == 2) {
				return 1;
			}
			
			return fibo(value - 2) + fibo(value - 1);
		}
	}
	
	static class Memo {
		static int cnt;
		static int[] chkArr = new int[100];
		
		static int fibo(int value) {
			++cnt;
			if (chkArr[value] != 0) {
				return chkArr[value];
			}
			if (value == 1 || value == 2) {
				return chkArr[value] = 1;
			}
			
			return chkArr[value] = fibo(value - 2) + fibo(value - 1);
		}
	}
}
