package javabasic.enumtest;

public class EnumTest{
	
	public enum TEST{
		INSTANCE, INSTANCE2;
		
		/*static */String val;
	}
	
	public static void main(String[] args) {
		TEST.INSTANCE.val = "123";
		TEST.INSTANCE2.val = "456";
		System.out.println(TEST.INSTANCE.val);
		System.out.println(TEST.INSTANCE2.val);
	}
}
