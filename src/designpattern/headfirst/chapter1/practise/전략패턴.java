package designpattern.headfirst.chapter1.practise;

public class 전략패턴 {
	static interface Strategy {
		void execute();
	}

	static class ReadStrategy implements Strategy {
		public void execute() {
			System.out.println("read");
		}
	}

	static class WriteStrategy implements Strategy {
		public void execute() {
			System.out.println("write");
		}
	}

	static class StrategyExecutor {
		Strategy strategy;

		public StrategyExecutor(Strategy strategy) {
			this.strategy = strategy;
		}
		
		public void setStrategy(Strategy strategy) {
			this.strategy = strategy;
		}
		
		void execute() {
			strategy.execute();
		}
	}
	public static void main(String[] args) {
		StrategyExecutor executor = new StrategyExecutor(new ReadStrategy());
		executor.execute();
		executor.setStrategy(new WriteStrategy());
		executor.execute();
		
		
	}

}
