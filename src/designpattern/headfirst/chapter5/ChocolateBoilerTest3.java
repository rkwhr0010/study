package designpattern.headfirst.chapter5;

public class ChocolateBoilerTest3 {
	static class ChocolateBoiler{
		private boolean empty;
		private boolean boiled;
		private static volatile ChocolateBoiler boiler;
		
		private ChocolateBoiler() {
			empty = true;
			boiled = false;
		}
		
		//DCL 이중확인 락 방법, 맨 처음에만 동기화하고 이 후 동기화 안함.
		public static ChocolateBoiler getInstance() {
			if(boiler == null) {
				synchronized (ChocolateBoiler.class) {
					if(boiler == null) {
						boiler = new ChocolateBoiler();
					}
				}
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
