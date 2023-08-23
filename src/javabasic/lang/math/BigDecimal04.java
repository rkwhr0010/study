package javabasic.lang.math;

import java.math.BigDecimal;

public class BigDecimal04 {
	public static void main(String[] args) {
		//안전한 사용
		BigDecimal v = new BigDecimal("10000");
		BigDecimal v2 = v;
		//이유는 final이라 값을 절대 변경할 수 없다
		BigDecimal v3 = v2.add(new BigDecimal("-10000"));
		System.out.println(v);
		System.out.println(v2);
		System.out.println(v3);
		
		System.out.println(v.divide(v));
		System.out.println(v.remainder(v));
	}
	
}
