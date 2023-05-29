package javabasic.lang.math;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class BigInteger02 {
	static Queue<BigInteger> q = new LinkedList<>();
	public static void main(String[] args) {
		System.out.println(fac(new BigInteger("21")));;
		System.out.println(q);
	}
	static BigInteger fac(BigInteger num) {
		if(num.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		}else {
			BigInteger tmp = num.multiply(fac(num.subtract(BigInteger.ONE)));
			q.add(tmp);
			return tmp;
		}
	}
}
