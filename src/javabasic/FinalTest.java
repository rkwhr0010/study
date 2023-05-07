package javabasic;

public class FinalTest {
	static class Box{
		final int data = 100;
	}
	
	public static void main(String[] args) {
		final String data = "123";
		String data2 = data;
		data2 = "456";
		
		final Box d1 = new Box();
		Box d2 = new Box();
		
		
		System.out.println(data);
		System.out.println(data2);
		
	}
}
