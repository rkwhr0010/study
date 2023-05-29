package javabasic.lang.math;

import java.math.BigDecimal;
import java.math.MathContext;
import static java.math.RoundingMode.*;

public class BigDecimal03 {
	public static void main(String[] args) {
		BigDecimal d1= new BigDecimal("123.456");
		BigDecimal d2= new BigDecimal("1.01");
		
		System.out.println("d1");
		print(d1);
		System.out.println("d2");
		print(d2);
		System.out.println("d1.multiply(d2)");
		print(d1.multiply(d2));
		System.out.println("d1.multiply(d2)");
		print(d1.divide(d2,2,HALF_UP));
		print(d1.setScale(2, HALF_UP));
		print(d1.divide(d2,new MathContext(2, HALF_UP)));
		
	}
	static void print(BigDecimal b) {
		System.out.println(String.format("%10s=%10s|%10s|%10s"
						,b,b.unscaledValue(),b.scale(),b.precision()));
	}
}
