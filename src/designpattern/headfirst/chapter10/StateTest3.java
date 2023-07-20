package designpattern.headfirst.chapter10;

public class StateTest3 {
	static interface State{
		public abstract void insertQuarter();//동전 넣기
		public abstract void ejectQuarter(); // 동전 반환
		public abstract void turnCrank(); //손잡이 돌리기
		public abstract void dispense();  //내보내기
	}
	static abstract class BaseState implements State{
		private final GumballMachine gumballMachine;
		
		public BaseState(GumballMachine gumballMachine) {
			this.gumballMachine = gumballMachine;
		}
	}
	
	static class SoldOutState extends BaseState{
		public SoldOutState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
			System.out.println("매진되었습니다. 다음 기회에 이용해 주세요.");
		}
		public void ejectQuarter() {
			System.out.println("동전을 넣지 않으셨습니다. 동전이 반환되지 않습니다.");
		}
		public void turnCrank() {
			System.out.println("매진되었습니다.");
		}
		public void dispense() {
			System.out.println("매진입니다.");
		}
		
	}
	static class NoQuarterState extends BaseState{
		
		public NoQuarterState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
			System.out.println("동전을 넣으셨습니다.");
		    super.gumballMachine.setState(super.gumballMachine.getNoQuarterState());
		}
		public void ejectQuarter() {
		}
		public void turnCrank() {
		}
		public void dispense() {
		}
	}
	static class HasQuarterState extends BaseState{
		public HasQuarterState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
		}
		public void ejectQuarter() {
		}
		public void turnCrank() {
		}
		public void dispense() {
		}
	}
	static class SoldState extends BaseState{
		public SoldState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
		}
		public void ejectQuarter() {
		}
		public void turnCrank() {
		}
		public void dispense() {
		}
	}
//	
	static class GumballMachine implements State{
		private State state;
		private int count = 0;
		
		private final BaseState soldOutState = new SoldOutState(this);
		private final BaseState noQuarterState = new NoQuarterState(this);
		private final BaseState hasQuarterState = new HasQuarterState(this);
		private final BaseState soldState = new SoldState(this);

		GumballMachine(int numberGumballs){
			this.count = numberGumballs;
			state = numberGumballs > 0 ? noQuarterState : soldOutState;
		}
	
		public void insertQuarter() {
			state.insertQuarter();
		}
		public void ejectQuarter() {
			state.ejectQuarter();
		}
		public void turnCrank() {
			state.turnCrank();
		}
		public void dispense() {
			state.dispense();
		}
		public State getState() {
			return state;
		}
		public void setState(State state) {
			this.state = state;
		}

		public BaseState getSoldOutState() {
			return soldOutState;
		}

		public BaseState getNoQuarterState() {
			return noQuarterState;
		}

		public BaseState getHasQuarterState() {
			return hasQuarterState;
		}

		public BaseState getSoldState() {
			return soldState;
		}
		
	}
}
