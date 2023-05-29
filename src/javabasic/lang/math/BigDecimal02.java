package javabasic.lang.math;

import java.math.BigDecimal;

public class BigDecimal02 {
	public static void main(String[] args) {
		BigDecimal d1= new BigDecimal("123.456");
		BigDecimal d2= new BigDecimal("1.0");
		print(d1);
		print(d2);
		
		print(d1.multiply(d2));
		
		
	}
	static void print(BigDecimal b) {
		System.out.println(b+" "+b.scale()+" "+b.precision()+" ");
	}
}
