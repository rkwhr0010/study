package designpattern.headfirst.chapter10;

public class StatePattern {
	static interface State {
		void insertQuarter();

		void ejectQuarter();

		void turnCrank();

		void dispense();

		void refill();
	}
	
	static abstract class BaseState implements State{
		GumballMachine gumballMachine;
		public BaseState(GumballMachine gumballMachine) {
			this.gumballMachine = gumballMachine;
		}
	}
	
	static class NoQuarterState extends BaseState{
		public NoQuarterState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
			System.out.println("동전을 넣으셨습니다.");
			gumballMachine.setState(gumballMachine.getHasQuarterState());
		}
		public void ejectQuarter() {
			System.out.println("동전을 넣어 주세요");
		}
		public void turnCrank() {
			System.out.println("동전을 넣어 주세요.");
		}
		public void dispense() {
			System.out.println("동전을 넣어 주세요.");
		}
		public void refill() {
		}
		@Override
		public String toString() {
			return "동전 기다리는 중";
		}
	}
	static class HasQuarterState extends BaseState{
		public HasQuarterState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
			System.out.println("동전은 한 개만 넣어 주세요.");
		}
		public void ejectQuarter() {
			System.out.println("동전이 반환됩니다.");
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		}
		public void turnCrank() {
			System.out.println("손잡이를 돌리셨습니다.");
			gumballMachine.setState(gumballMachine.getSoldState());
		}
		public void dispense() {
			System.out.println("알맹이를 내보낼 수 없습니다.");
		}
		public void refill() {
		}
		@Override
		public String toString() {
			return "손잡이 돌기는 것 기다리는 중";
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
			System.out.println("매진되었습니다.");
		}
		public void turnCrank() {
			System.out.println("손잡이를 돌렸지만, 알맹이는 없습니다.");
		}
	 
		public void dispense() {
			System.out.println("알맹이를 내보낼 수 없습니다.");
		}
		
		public void refill() {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		}
		@Override
		public String toString() {
			return "매진";
		}
	}
	static class SoldState extends BaseState{
		public SoldState(GumballMachine gumballMachine) {
			super(gumballMachine);
		}
		public void insertQuarter() {
			System.out.println("알맹이를 내보내고 있습니다.");
		}
		public void ejectQuarter() {
			System.out.println("이미 알맹이를 뽑으셨습니다.");
		}
		public void turnCrank() {
			System.out.println("손잡이는 한 번만 돌려 주세요.");
		}
		public void dispense() {
		}
		public void refill() {
			gumballMachine.releaseBall();
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("이런, 알맹이가 다 떨어졌어요");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
		@Override
		public String toString() {
			return "알맹이 내보내는 중";
		}
	}
	
	
	static class GumballMachine{
		State state;
		int count = 0;
		
		final State soldOutState;
		final State noQuarterState;
		final State hasQuarterState;
		final State soldState;
		
		public GumballMachine(int numberGumballs) {
			soldOutState = new SoldOutState(this);
			noQuarterState = new NoQuarterState(this);
			hasQuarterState = new HasQuarterState(this);
			soldState = new SoldState(this);
			
			this.count = numberGumballs;
			state = numberGumballs > 0 ? noQuarterState : soldOutState;
		}
		void setState(State state) {
			this.state = state;
		}
	    public State getState() {
	        return state;
	    }

	    public State getSoldOutState() {
	        return soldOutState;
	    }

	    public State getNoQuarterState() {
	        return noQuarterState;
	    }

	    public State getHasQuarterState() {
	        return hasQuarterState;
	    }

	    public State getSoldState() {
	        return soldState;
	    }
		
		public void insertQuarter() {
			state.insertQuarter();
		}
		
		public void ejectQuarter() {
			state.ejectQuarter();
		}
		
		public void turnCrank() {
			state.turnCrank();
			state.dispense();
		}
		
		public void refill(int count) {
			this.count += count;
			System.out.println("겜볼 기계가 방금 리필되었습니다. 개수는 다음과 같습니다." + this.count);
			state.refill();
		}
		
		public void releaseBall() {
			System.out.println("알맹이가 슬롯에서 굴러 나옵니다....");
			if (count > 0) {
				count = count - 1;
			}
		}
		public int getCount() {
			return count;
		}
		
		public String toString() {
			StringBuffer result = new StringBuffer();
			result.append("\n겜블 머신_");
			result.append("\n자바 기반 Gumball Model #2004");
			result.append("\n항목: " + count + " 알맹이");
			if (count != 1) {
				result.append("들");
			}
			result.append("\n");
			result.append("머신 상태 :" + state + "\n");
			return result.toString();
		}
	}
	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(2);

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
		gumballMachine.refill(5);
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
