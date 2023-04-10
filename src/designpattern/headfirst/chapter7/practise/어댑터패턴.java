package designpattern.headfirst.chapter7.practise;

public class 어댑터패턴 {
	static interface TargetInterface {
		void doSomething();
	}
	
	static class TargetClass implements TargetInterface{
		@Override
		public void doSomething() {
			System.out.println("몬가...몬가 하고 있음");
		}
	}
	
	static interface MyInter {
		void dododo();
	}
	static class MyClass implements MyInter{
		@Override
		public void dododo() {
			System.out.println("하핳ㅎ");
		}
	}
	
	static class MyAdapter implements MyInter{
		TargetInterface targetInterface;
		
		public MyAdapter(TargetInterface targetInterface) {
			super();
			this.targetInterface = targetInterface;
		}

		@Override
		public void dododo() {
			targetInterface.doSomething();
		}
		
	}
	
	
	
}
