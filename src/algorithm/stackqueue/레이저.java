package algorithm.stackqueue;

import java.util.ArrayDeque;

public class 레이저 {
	public static void main(String[] args) {
		String input = "(((()(()()))(())()))(()())";
		ArrayDeque<Character> stack = new ArrayDeque<>();
		int answer = 0;
		char[] cs = input.toCharArray();
		for(int i = 0 ; i < cs.length;i++) {
			if(cs[i]== '(') stack.push('(');
			else {
				stack.pop();
				if(cs[i-1] == ')') answer++;
				else answer += stack.size();
			}
		}
		System.out.println(answer);
		
	}
}
