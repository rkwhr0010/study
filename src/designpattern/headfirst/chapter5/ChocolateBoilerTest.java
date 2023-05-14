package designpattern.headfirst.chapter5;

public class ChocolateBoilerTest {
	//고전적인 싱글턴, 문제를 멀티스레딩 시 발생
	static class ChocolateBoiler{
		private boolean empty;
		private boolean boiled;
		//static
		private static ChocolateBoiler boiler;
		//생성자 private 이 클래스에서만 접근가능
		private ChocolateBoiler() {
			empty = true;
			boiled = false;
		}
		//static 
		public static ChocolateBoiler getInstance() {
			//이렇게 객체를 필요할 때 생성하는 방법을 지연로딩이라 한다.
			if(boiler == null) { // <=== 이 부분에서 멀티 쓰레드 시 문제가 발생한다.
				boiler = new ChocolateBoiler();
			}
			return boiler;
		}
		public void fill() {
			if(isEmpty()) {
				empty = false;
				boiled = false;
			}
		}
		public void drain() {
			if(!isEmpty()&& isBoiled()) {
				empty = true;
			}
		}
		public void boil() {
			if(!isEmpty()&& !isBoiled()) {
				boiled = true;
			}
		}
		public boolean isEmpty() {
			return empty;
		}
		public boolean isBoiled() {
			return boiled;
		}
	}
}
