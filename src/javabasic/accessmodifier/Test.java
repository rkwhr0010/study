package javabasic.accessmodifier;

import javabasic.accessmodifier.otherpackage.Sub;

public class Test {
	public static void main(String[] args) {
		Super sub = new Sub();
		sub.call();
	}
}
