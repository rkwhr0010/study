package designpattern.behavioral.strategy.practise;

public class 전략 {
	static interface Strategy{
		void attack();
	}
	static class GunStrategy implements Strategy{
		public void attack() {
			System.out.println("빵야빵야!");
		}
	}
	static class BowStrategy implements Strategy{
		public void attack() {
			System.out.println("쓕슊슈슈슛!");
		}
	}
	
	static class AttackContext {
		private Strategy strategy;
		public AttackContext(Strategy strategy) {
			this.strategy = strategy;
		}
		public void setStrategy(Strategy strategy) {
			this.strategy = strategy;
		}
		
		public void attack() {
			strategy.attack();
		}
		
	}
	
	public static void main(String[] args) {
		
		AttackContext context = new AttackContext(new BowStrategy());
		context.attack();
		context.setStrategy(new GunStrategy());
		context.attack();
		
		
	}
}
