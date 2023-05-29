package javabasic.lang.math;

import java.math.BigInteger;

public class BigInteger01 {
	public static void main(String[] args) {
		BigInteger integer = new BigInteger("100");
		BigInteger integer2 = new BigInteger("101");
		System.out.println(integer.testBit(0));//0==1  짝수
		System.out.println(integer2.testBit(0));//1==1 홀수
		System.out.println(integer.shiftLeft(1));//2제곱
		System.out.println(integer.shiftLeft(2));//3제곱
		System.out.println(integer2.shiftLeft(1));
		BigInteger integer3 = new BigInteger("123");
		System.out.println(integer3.toString(2));
		System.out.println(integer3.shiftRight(1));//오른쪽 쉬프트는 홀수 버려짐
		System.out.println(integer3.shiftRight(2));//오른쪽 쉬프트는 홀수 버려짐
	}
}
