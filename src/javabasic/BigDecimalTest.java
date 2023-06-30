package javabasic;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalTest {
	public static void main(String[] args) {
//		BigDecimal bd = new BigDecimal("987654321.123456789e10");
		BigDecimal bd = new BigDecimal("12345.6789");
		System.out.println(bd.toPlainString());
		
		System.out.println(bd.precision());
		System.out.println(bd.scale());
		System.out.println(bd.unscaledValue());
		System.out.println(bd.divide(new BigDecimal(100)));
		System.out.println(bd.round(new MathContext(1)).toPlainString());
		
		
	}
}
