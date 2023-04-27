package javabasic.stream;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class LazyEval {
	
	public static void main(String[] args) {
		
		for(Integer i : Arrays.asList(1,2,3,4,5)) {
			calValue(i, 비용이큰값());
		}
		for(Integer i : Arrays.asList(1,2,3,4,5)) {
			calValue(i, ()->비용이큰값());
		}
		
	}
	
	static String 비용이큰값(){
		try {TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {	}
		return "value";
	}
	
	static void calValue(int num, String value) {
		if(num%2==0) {
			System.out.println("계산~~"+value+"완료");
		}else {
			System.out.println("아무것도 안함");
		}
	}
	
	static void calValue(int num, Supplier<String> supplier) {
		if(num%2==0) {
			System.out.println("계산~~"+ supplier.get() +"완료");
		}else {
			System.out.println("아무것도 안함");
		}
	}
	
	
	
}
