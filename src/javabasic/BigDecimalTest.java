package javabasic;

import java.math.BigDecimal;

public class BigDecimalTest {
	public static void main(String[] args) {
		BigDecimal decimal = new BigDecimal(1000);
		
		System.out.println(decimal.toString());
		
		System.out.println( 10000 - (10000/31)*31 );
		
		System.out.println(BigDecimal.valueOf(1231231231L).toString());
		
		
	}
}
