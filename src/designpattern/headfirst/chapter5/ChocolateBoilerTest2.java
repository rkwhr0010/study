package designpattern.headfirst.chapter5;

public class ChocolateBoilerTest2 {
	static class ChocolateBoiler{
		private boolean empty;
		private boolean boiled;
		private static ChocolateBoiler boiler;
		
		
		private ChocolateBoiler() {
			empty = true;
			boiled = false;
		}
		//가장 간단한 방법, 성능 저하 약 100배, 이유는 매번 호출 마다 동기화하기 때문
		public static synchronized ChocolateBoiler getInstance() {
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
