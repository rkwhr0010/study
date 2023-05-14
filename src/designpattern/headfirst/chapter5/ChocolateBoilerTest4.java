package designpattern.headfirst.chapter5;

public class ChocolateBoilerTest4 {
	static class ChocolateBoiler{
		private boolean empty;
		private boolean boiled;
		//시작시 바로JVM이 정적 초기화
		//단점으로 지연로딩을 원한다면 불가능하다.
		private static ChocolateBoiler boiler = new ChocolateBoiler();
		
		private ChocolateBoiler() {
			empty = true;
			boiled = false;
		}
		
		public static ChocolateBoiler getInstance() {
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
