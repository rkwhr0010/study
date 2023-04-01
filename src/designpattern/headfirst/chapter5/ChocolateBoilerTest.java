package designpattern.headfirst.chapter5;

public class ChocolateBoilerTest {
	//고전적인 싱글턴, 문제를 멀티스레딩 시 발생
	static class ChocolateBoiler{
		private boolean empty;
		private boolean boiled;
		private static ChocolateBoiler boiler;
		
		
		private ChocolateBoiler() {
			empty = true;
			boiled = false;
		}
		
		public static ChocolateBoiler getInstance() {
			if(boiler == null) {
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
