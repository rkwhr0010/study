import java.util.*;
//후위연산
class 후위연산_ {	
	public static void main(String[] args){
		String str = "573*+5-323*++";
		int answer=0;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(char cha : str.toCharArray()) {
			int lt = 0;
			int rt = 0;
			if(!Character.isDigit(cha)) {
//			if(cha =='+'||cha =='-'||cha =='/'||cha =='*') {
				rt = stack.pop();
				lt = stack.pop();
				answer = cha=='+'? Integer.sum(lt, rt) 
						:cha=='-'? Math.subtractExact(lt, rt)
						:cha=='/'? Math.floorDiv(lt, rt) 
								 : Math.multiplyExact(lt, rt);
				stack.push(answer);
			}else {
				System.out.println("char:"+cha + " char아스키:"+ Integer.valueOf(cha));
				stack.push(cha-'0');
				// char형 숫자에 '0'을 빼면 우리가 생각하는 숫자(int)가 된다.
				// -48도 가능하다.
			}
		}
		System.out.println("char: 0 char아스키:"+ Integer.valueOf('0'));
		System.out.println(answer);
	}
}

