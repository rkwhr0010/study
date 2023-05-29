package javabasic.lang.string;

public class String01 {
	public static void main(String[] args) {
		String str1 = "리터럴";
		String str2 = "리터럴";
		String str3 = new String("인스턴스");
		String str4 = new String("인스턴스");
		System.out.println(str1 == str2 ? "같다" : "다르다");
		System.out.println(str3 == str4 ? "같다" : "다르다");
	}
}
