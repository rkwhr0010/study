package designpattern.structural.facade;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <pre>
 * 
 * 하위 시스템의 인터페이스 집합에 통합 인터페이스를 제공합니다. 
 * Facade는 하위 시스템을 사용하기 쉽게 만드는 상위 수준 인터페이스를 정의합니다.
 * 
 * 파사드는 클래스 라이브러리와 같은 더 큰 코드 본문에 대한 단순화된 인터페이스를 제공하는 개체입니다.
 * 
 * 간단히 말해서 파사드는 간단한 인터페이스 뒤에 복잡한 하위 시스템을 캡슐화합니다. 복잡성의 상당 부분을 숨기고 하위 시스템을 사용하기 쉽게 만듭니다.
 * 또한 복잡한 하위 시스템을 직접 사용해야 하는 경우에도 그렇게 할 수 있습니다. 우리는 항상 파사드를 사용하도록 강요받지 않습니다.
 * 훨씬 간단한 인터페이스 외에도 이 디자인 패턴을 사용하면 얻을 수 있는 이점이 하나 더 있습니다. 
 * 복잡한 하위 시스템에서 클라이언트 구현을 분리합니다. 덕분에 기존 하위 시스템을 변경할 수 있고 클라이언트에 영향을 주지 않습니다.
 * 
 * 
 * 키워드 : 통합 인터페이스, 집합 인터페이스, 복잡한 인터페이스 숨김, 느슨한 결합으로 분리,최소 지식 원칙
 * </pre>
 */
public class 파사드 {
	public static void main(String[] args) {
		DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
		facade.startNewDay();
		facade.digOutGold();
		facade.endDay();
	}
	
	
	abstract static class DwarvenMineWorker{
		static enum Action {
			GO_TO_SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK
		}
		public abstract String name();
		public abstract void work();
		
		public void goToSleep() {
			System.out.printf("%s 자러가자!\n", this.name());
		}
		public void wakeUp() {
			System.out.printf("%s 일어나!\n", this.name());
		}
		public void goHome() {
			System.out.printf("%s 집으로!\n", name());
		}

		public void goToMine() {
			System.out.printf("%s 광산으로!\n", name());
		}
		private void action(Action action) {
			switch (action) {
			case GO_TO_SLEEP:
				goToSleep();
				break;
			case WAKE_UP:
				wakeUp();
				break;
			case GO_HOME:
				goHome();
				break;
			case GO_TO_MINE:
				goToMine();
				break;
			case WORK:
				work();
				break;
			default:
				System.out.printf("정의되지 않은 행동");
				break;
			}
		}
		public void action(Action... actions) {
			Arrays.stream(actions).forEach(this::action);
		}
	}

	static class DwarvenTunnelDigger extends DwarvenMineWorker {
		@Override
		public void work() {
			System.out.printf("%s 좋은 터널을 찾아 뚫습니다.\n", name());
		}
		@Override
		public String name() {
			return "터널 굴착기_";
		}
	}
	static class DwarvenGoldDigger extends DwarvenMineWorker {
		public void work() {
			System.out.printf("%s 금을 채굴합니다.\n", name());
		}
		public String name() {
			return "금 굴착기_";
		}
	}
	static class DwarvenCartOperator extends DwarvenMineWorker {
		public void work() {
			System.out.printf("%s 광산에서 금덩이를 옳깁니다.\n", name());
		}
		public String name() {
			return "광산 노동자_";
		}
	}
	
	static class DwarvenGoldmineFacade {
		//공통 객체들이 아닌 상이한 객체들을 묶어 다루는 것이 더 일반적이다.
		private final List<DwarvenMineWorker> workers;

		public DwarvenGoldmineFacade() {
			workers = List.<DwarvenMineWorker>of(new DwarvenGoldDigger(), new DwarvenCartOperator(), new DwarvenTunnelDigger());
		}
		public void startNewDay() {
			makeActions(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE);
		}
		public void digOutGold() {
			makeActions(workers, DwarvenMineWorker.Action.WORK);
		}
		public void endDay() {
			makeActions(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.GO_TO_SLEEP);
		}
		private static void makeActions(Collection<DwarvenMineWorker> workers, DwarvenMineWorker.Action... actions) {
			workers.forEach(worker -> worker.action(actions));
		}
	}
}
