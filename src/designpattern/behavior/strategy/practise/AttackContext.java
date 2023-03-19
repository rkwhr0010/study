package designpattern.behavior.strategy.practise;

public class AttackContext {
	Strategy strategy;

	public AttackContext(Strategy strategy) {
		super();
		this.strategy = strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void attack() {
		strategy.attack();
	}
	
}
