package designpattern.headfirst.chapter3.practise;

public class 데코레이터패턴 {
	static interface HelloWorld{
		String hello();
	}
	static class Hello implements HelloWorld{
		@Override
		public String hello() {
			return "hello";
		}
	}
	
	static class HelloDeco implements HelloWorld{
		HelloWorld helloWorld;
		
		public HelloDeco(HelloWorld helloWorld) {
			super();
			this.helloWorld = helloWorld;
		}


		@Override
		public String hello() {
			return helloWorld.hello()+" world";
		}
	}
	
	public static void main(String[] args) {
		HelloWorld hello = new Hello();
		HelloWorld helloDeco = new HelloDeco(hello);
		System.out.println(hello.hello());
		System.out.println(helloDeco.hello());
		
	}
	
}
