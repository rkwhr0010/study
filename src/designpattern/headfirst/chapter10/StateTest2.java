package designpattern.headfirst.chapter10;

public class StateTest2 {

	static interface State {
		void insertQuarter();

		void ejectQuarter();

		void turnCrank();

		void dispense();

		void refill();
	}

	static class NoQuarterState implements State {
		final GumballMachine gumballMachine;

		public NoQuarterState(GumballMachine gumballMachine) {
			this.gumballMachine = gumballMachine;
		}

		public void insertQuarter() {
			System.out.println("동전을 넣으셨습니다.");
			gumballMachine.setState(gumballMachine.getHasQuarterState());
		}

		public void ejectQuarter() {
			System.out.println("동전을 넣어 주세요.");
		}

		public void turnCrank() {
			System.out.println("동전을 넣어 주세요.");
		}

		public void dispense() {
			System.out.println("동전을 넣어 주세요.");
		}

		public void refill() {
		}

		public String toString() {
			return "waiting for quarter";
		}
	}

	static class HasQuarterState implements State {
		GumballMachine gumballMachine;

		public HasQuarterState(GumballMachine gumballMachine) {
			this.gumballMachine = gumballMachine;
		}

		public void insertQuarter() {
			System.out.println("You can't insert another quarter");
		}

		public void ejectQuarter() {
			System.out.println("Quarter returned");
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		}

		public void turnCrank() {
			System.out.println("You turned...");
			gumballMachine.setState(gumballMachine.getSoldState());
		}

		public void dispense() {
			System.out.println("No gumball dispensed");
			
		}

		public void refill() {
		}

		public String toString() {
			return "waiting for turn of crank";
		}
	}

	static class SoldOutState implements State {
		GumballMachine gumballMachine;

		public SoldOutState(GumballMachine gumballMachine) {
			this.gumballMachine = gumballMachine;
		}

		public void insertQuarter() {
			System.out.println("You can't insert a quarter, the machine is sold out");
		}

		public void ejectQuarter() {
			System.out.println("You can't eject, you haven't inserted a quarter yet");
		}

		public void turnCrank() {
			System.out.println("You turned, but there are no gumballs");
		}

		public void dispense() {
			System.out.println("No gumball dispensed");
		}

		public void refill() {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		}

		public String toString() {
			return "sold out";
		}
	}

	static class SoldState implements State {

		GumballMachine gumballMachine;

		public SoldState(GumballMachine gumballMachine) {
			this.gumballMachine = gumballMachine;
		}

		public void insertQuarter() {
			System.out.println("Please wait, we're already giving you a gumball");
		}

		public void ejectQuarter() {
			System.out.println("Sorry, you already turned the crank");
		}

		public void turnCrank() {
			System.out.println("Turning twice doesn't get you another gumball!");
		}

		public void dispense() {
			gumballMachine.releaseBall();
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("Oops, out of gumballs!");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}

		public void refill() {
		}

		public String toString() {
			return "dispensing a gumball";
		}
	}

	static class GumballMachine {
		final State soldOutState;
		final State noQuarterState;
		final State hasQuarterState;
		final State soldState;

		State state;
		int count = 0;

		public GumballMachine(int numberGumballs) {
			soldOutState = new SoldOutState(this);
			noQuarterState = new NoQuarterState(this);
			hasQuarterState = new HasQuarterState(this);
			soldState = new SoldState(this);
			
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
			state.dispense();
		}
		int getCount() {
			return count;
		}
	 
		void refill(int count) {
			this.count += count;
			System.out.println("The gumball machine was just refilled; its new count is: " + this.count);
			state.refill();
		}
		void releaseBall() {
			System.out.println("A gumball comes rolling out the slot...");
			if (count > 0) count -= 1;
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
	 
		public String toString() {
			StringBuffer result = new StringBuffer();
			result.append("\nMighty Gumball, Inc.");
			result.append("\nJava-enabled Standing Gumball Model #2004");
			result.append("\nInventory: " + count + " gumball");
			if (count != 1) {
				result.append("s");
			}
			result.append("\n");
			result.append("Machine is " + state + "\n");
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
