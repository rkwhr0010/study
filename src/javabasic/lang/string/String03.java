package javabasic.lang.string;

public class String03 {
	public static void main(String[] args) {
		String value = "1,2,3,4,5,6";	
		System.out.println(String.join("-", value.split(",")));
	}
}
