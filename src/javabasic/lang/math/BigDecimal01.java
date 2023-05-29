package javabasic.lang.math;

import java.math.BigDecimal;

public class BigDecimal01 {
	public static void main(String[] args) {
		BigDecimal decimal = new BigDecimal("100.123456789");
		//실수형으로 객체 생성 자체를 안하는 편이 정확도 측면에서 중요하다.
		System.out.println(
				decimal.subtract(new BigDecimal(1D))
				.equals(new BigDecimal(99.123456789))
				);
		System.out.println(
				decimal.subtract(new BigDecimal("1"))
				.equals(new BigDecimal("99.123456789"))
				);
	}
}
