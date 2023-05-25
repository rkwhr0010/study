package designpattern.headfirst.chapter10;

public class StateTest {
	static class GumballMachine{
		
		//상태 표현 상수
		final static int SOLD_OUT = 0;
		final static int NO_QUARTER = 1;
		final static int HAS_QUARTER = 2;
		final static int SOLD = 3;
		
		//현재 상태 변수
		int state  = SOLD_OUT;
		int count = 0 ;
		
		public GumballMachine(int count) {
			this.count= count;
			if(count>0) {
				state = NO_QUARTER;
			}
		}
		
		public void insertQuarter() {
			if(state == HAS_QUARTER) {
				System.out.println("동전은 한 개만 넣어 주세요.");
			}else if (state == NO_QUARTER) {
				state = HAS_QUARTER;
				System.out.println("동전을 넣으셨습니다.");
			}else if (state == SOLD_OUT) {
				System.out.println("매진되었습니다. 다음 기회에 이용해 주세요.");
			}else if (state == SOLD) {
				System.out.println("알맹이를 내보내고 있습니다.");
			}
		}
		
		public void ejectQuarter() {
			if(state == HAS_QUARTER) {
				System.out.println("동전이 반환됩니다.");
				state = NO_QUARTER;
			}else if (state == NO_QUARTER) {
				state = HAS_QUARTER;
				System.out.println("동전을 넣어 주세요");
			}else if (state == SOLD_OUT) {
				System.out.println("동전을 넣지 않으셨습니다. 동전이 반환되지 않습니다.");
			}else if (state == SOLD) {
				System.out.println("이미 알맹이를 뽑으셨습니다.");
			}
		}
		
		public void turnCrank() {
			if(state == SOLD) {
				System.out.println("손잡이는 한 번만 돌려 주세요.");
			}else if (state == NO_QUARTER) {
				System.out.println("동전을 넣어 주세요.");
			}else if (state == SOLD_OUT) {
				System.out.println("매진되었습니다.");
			}else if (state == HAS_QUARTER) {
				System.out.println("손잡이를 돌리셨습니다.");
				state = SOLD;
				dispense();
			}
		}
		public void dispense() {
			if(state == SOLD) {
				System.out.println("알맹이를 내보내고 있습니다.");
				if(0 == (count -= 1)) {
					System.out.println("더 이상 알맹이가 없습니다.");
					state = SOLD_OUT;
				} else {
					state = NO_QUARTER;
				}
			}else if (state == NO_QUARTER) {
				System.out.println("동전을 넣어 주세요.");
			}else if (state == SOLD_OUT) {
				System.out.println("매진입니다.");
			}else if (state == HAS_QUARTER) {
				System.out.println("알맹이를 내보낼 수 없습니다.");
			}
		}
		public void refill(int numGumBalls) {
			this.count = numGumBalls;
			state = NO_QUARTER;
		}
		public String toString() {
			StringBuffer result = new StringBuffer();
			result.append("\n 주)겜블 머신");
			result.append("\n자바로 개발된 겜블 머신 #2004\n");
			result.append("항목: " + count + " 개");
			if (count != 1) {
				result.append("s");
			}
			result.append("\n갬블머신 현재 상태 ");
			if (state == SOLD_OUT) {
				result.append("매진");
			} else if (state == NO_QUARTER) {
				result.append("동전 투입 대기 중");
			} else if (state == HAS_QUARTER) {
				result.append("손잡이 돌리기 대기 중");
			} else if (state == SOLD) {
				result.append("알맹이 배출 중");
			}
			result.append("\n");
			return result.toString();
		}
	}
	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(5);

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
	
}
