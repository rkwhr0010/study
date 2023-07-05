package algorithm.twopointer.practise;

import java.util.Scanner;

public class 연속된자연수의합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		
		System.out.println(solution(input));
	}

	private static int solution(int input) {
		int result = 0;
		for(int low = 1, high = 1, sum = 0; high < input; high++) {
			sum += high; 
			if(sum == input) result++;
			
			while(input < sum) {
				sum -= low++;
				if(sum == input) result++;
			}
		}
		
		return result;
	}
}
