package algorithm.dynamicprogramming.practise;


public class 계단오르기연습 {
	static int[] dy;
	static int[] memo;
	
	static int cnt = 0;
	
	public static void main(String[] args) {
		int goal = 10;
		goal +=1;
		
		dy = new int[goal+1];
		memo = new int[goal+1];
		
		long start = System.nanoTime();
		System.out.println("answer = " +dy(goal));
		System.out.println(cnt);
		System.out.println(System.nanoTime()-start);
		
		start = System.nanoTime();
		cnt=0;
		System.out.println("answer = "+noDy(goal));
		System.out.println(cnt);
		System.out.println(System.nanoTime()-start);
		
		start = System.nanoTime();
		cnt=0;
		System.out.println("answer = "+noDyMemo(goal));
		System.out.println(cnt);
		System.out.println(System.nanoTime()-start);
		
	}

	private static int dy(int goal) {
		dy[1] = 1;
		dy[2] = 2;
		for(int i=3;i<=goal;i++,cnt++) {
			dy[i] = dy[i-1]+dy[i-2];
		}
		return dy[goal];
	}
	
	static int noDy(int goal) {
		cnt++;
		if(goal==1) return 1;
		else if (goal==2) return 2;
		else return noDy(goal-1)+noDy(goal-2);
	}
	
	static int noDyMemo(int goal) {
		cnt++;
		if(goal==1) return 1;
		else if (goal==2) return 2;
		else if(memo[goal]>0) return memo[goal];
		else return memo[goal]=noDyMemo(goal-1)+noDyMemo(goal-2);
	}
}
