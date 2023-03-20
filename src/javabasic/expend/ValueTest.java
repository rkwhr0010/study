package javabasic.expend;

public class ValueTest {
	static class Super {
		String value = "부모";
	}
	static class Sub extends Super{
		String value = "자식";
		String getValue() {return super.value;}
	}
	
	public static void main(String[] args) {
		Sub sub = new Sub();
		System.out.println(sub.value +" "+sub.getValue());
		System.out.println(((Super)sub).value);
		
		
	}
}
