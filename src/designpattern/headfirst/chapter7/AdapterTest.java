package designpattern.headfirst.chapter7;

public class AdapterTest {
	//기존 코드
	static interface Duck{
		void quack();
		void fly();
	}
	static class MallardDuck implements Duck{
		@Override
		public void quack() {
			System.out.println("꽥!");
		}
		@Override
		public void fly() {
			System.out.println("날고 있어요!!");
		}
	}
	
	//새로 제공된 코드
	static interface Turkey{
		void gobble();
		void fly();
	}
	static class WildTurkey implements Turkey{
		@Override
		public void gobble() {
			System.out.println("골골");
		}
		@Override
		public void fly() {
			System.out.println("짧은 거리를 날고 있어요!");
		}
	}
	
	//어댑터
	static class TurkeyAdapter implements Duck{
		Turkey turkey;
		
		public TurkeyAdapter(Turkey turkey) {
			this.turkey = turkey;
		}

		public void quack() {
			turkey.gobble();
		}

		public void fly() {
			for (int i = 0; i < 5; i++) {
				turkey.fly();
			}
		}
	}
	static class DuckAdapter implements Turkey{
		Duck duck;
		public DuckAdapter(Duck duck) {
			this.duck = duck;
		}
		public void gobble() {
			duck.quack();
		}
		public void fly() {
			duck.fly();
		}
	}
	
	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		Turkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);
		Turkey duckAdapter = new DuckAdapter(duck);
		
		System.out.println("칠면조---");
		turkey.gobble();
		turkey.fly();
		System.out.println("\n오리---");
		testDuck(duck);
		System.out.println("\n칠면조 어댑터---");
		testDuck(turkeyAdapter);
		System.out.println("\n오리 어댑터---");
		testTurkey(duckAdapter);
		testTurkey(turkey);
		
		
	}
	static void testTurkey(Turkey turkey){
		turkey.gobble();
		turkey.fly();
	}
	static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
