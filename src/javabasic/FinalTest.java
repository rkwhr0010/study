package javabasic;

public class FinalTest {
	static class Box{
		String data = "123123";
		String getData() {
			return data;
		}
	}
	
	public static void main(String[] args) {
		final String data = "123";
		String data2 = data;
		data2 = "456";
		
		final Box d1 = new Box();
		Box d2 = new Box();
		
		String tmp = d2.data;
		tmp = "asdasd";
		
		System.out.println(d2.data);
		
		System.out.println(data);
		System.out.println(data2);
		
	}
}
