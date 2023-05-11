package designpattern.structural.decorator.practise;

public class Practise {
	//구성요소
	static interface Componenet{
		void something();
	}
	static class ComponenetImpl implements Componenet{
		public void something() {
			System.out.println("몬가...몬가 이루어지고 있음!!");
		}
	}
	
	//데코레이터, 꼭 추상클래스가 아니여도 됨
	abstract static class Decorator implements Componenet{
		final Componenet componenet;
		public Decorator(Componenet componenet) {
			this.componenet = componenet;
		}
		@Override
		public void something() {
			componenet.something();
			System.out.println("몬가...꾸미고 싶음!");
		}
	}
	public static void main(String[] args) {
		Componenet impl = new ComponenetImpl();
		Decorator implDeco = new Decorator(impl) {};
		implDeco.something();
	}
}
