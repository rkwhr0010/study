package algorithm.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class 후위식 {
	public static void main(String[] args) {
		String input = "573*+5-323*++"; // 30
		Deque<Integer> stack = new ArrayDeque<>();
		
		for(char ch : input.toCharArray()) {
			if(Character.isDigit(ch)) {
				stack.push(Character.getNumericValue(ch));
			} else {
				Integer rt = stack.pop();
				Integer lt = stack.pop();
				
				switch (ch) {
				case '+': stack.push(lt+rt);
					break;
				case '-': stack.push(lt-rt);
					break;
				case '/': stack.push(lt/rt);
					break;
				case '*': stack.push(lt*rt);
					break;
				}
			}
		}
		System.out.println(stack);
	}
}
