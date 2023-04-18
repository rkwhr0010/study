package designpattern.etc.compositepattern;

public class Exam001Basic {
	//인터페이스
	static interface Quackable{
		void quack();
	}
	static class MallardDuck implements Quackable{
		public void quack() {
			System.out.println("꽥꽥");
		}
	}
	static class RedheadDuck implements Quackable{
		public void quack() {
			System.out.println("꽥꽥");
		}
	}
	static class DuckCall implements Quackable{
		public void quack() {
			System.out.println("꽉꽉");			
		}
	}
	static class RubberDuck implements Quackable{
		public void quack() {
			System.out.println("삑삑");			
		}
	}
	
	static class DuckSimulator{
		void simulate() {
			//인터페이스로 변수 다루기
			Quackable mallardDuck = new MallardDuck();
			Quackable redheadDuck = new RedheadDuck();
			Quackable duckCall = new DuckCall();
			Quackable rubberDuck = new RubberDuck();
			
			System.out.println("\n오리 시뮬레이션 게임");
			
			simulate(mallardDuck);
			simulate(redheadDuck);
			simulate(duckCall);
			simulate(rubberDuck);
		}
		
		//인터페이스로 메서드 인자로 다루기
		private void simulate(Quackable duck) {
			duck.quack();
		}
	}
	
	public static void main(String[] args) {
		DuckSimulator duckSimulator = new DuckSimulator();
		duckSimulator.simulate();
		duckSimulator.simulate(new DuckCall());
	}
	
	
}
