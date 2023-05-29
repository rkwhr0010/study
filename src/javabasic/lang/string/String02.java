package javabasic.lang.string;

public class String02 {
	public static void main(String[] args) {
		String v1 = new String("123");
		String v2 = new String("123");
		System.out.println(v1 == v2);
		v1.intern();
		v2.intern();
		System.out.println(v1.toString() == v2.toString());
		System.out.println(v1.intern() == v2.intern());
	}
}
