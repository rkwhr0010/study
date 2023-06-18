package javabasic.toby;

public class SuperTypeToken02 {
	
	static class Sup<T>{
		T value;
	}
	
	public static void main(String[] args) throws Exception {
		Sup<String> sup = new Sup<>();
		sup.value = "asd";
		
		System.out.println(sup.getClass().getDeclaredField("value").getType());
		//Object
	}
}
