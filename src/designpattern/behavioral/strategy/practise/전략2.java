package designpattern.behavioral.strategy.practise;

public class 전략2 {
	static interface Strategy{
		void execute();
	}
	static class ReadStrategy implements Strategy{
		@Override
		public void execute() {
			System.out.println("ReadStrategy");
		}
	}
	static class WriteStrategy implements Strategy{
		@Override
		public void execute() {
			System.out.println("WriteStrategy");
		}
	}
	
	static abstract class Program {
		Strategy strategy;

		public Program(Strategy strategy) {
			this.strategy = strategy;
		}

		public void setStrategy(Strategy strategy) {
			this.strategy = strategy;
		}
		
		public void execute() {
			strategy.execute();
		}
	}
	static class WordProgram extends Program{
		public WordProgram(Strategy strategy) {
			super(strategy);
		}
	}
	static class ExcelProgram extends Program{
		public ExcelProgram(Strategy strategy) {
			super(strategy);
		}
	}
	
	
	public static void main(String[] args) {
		Strategy readStrategy = new ReadStrategy();
		Strategy writeStrategy = new WriteStrategy();
		Program program1 = new WordProgram(readStrategy);
		Program program2 = new ExcelProgram(readStrategy);
		program1.execute();
		program1.setStrategy(writeStrategy);
		program1.execute();
		program2.execute();
		
	}
}
