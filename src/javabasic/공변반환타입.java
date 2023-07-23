package javabasic;

//상위 클래스 타입을 하위 클래스로 반환하는 것
public class 공변반환타입 {
	public static void main(String[] args) {
		Integer integer = new Sub().get();
	}
	static abstract class Super{
		abstract Number get() ;
	}
	
	static class Sub extends Super{
		Integer get()  {
			return 10;
		}
	}
	
}
