package designpattern.behavioral.strategy;

/**
 * <pre>
 * 전략 패턴을 사용하면 런타임에 가장 적합한 알고리즘을 선택할 수 있습니다.
 * 인터페이스를 정의 후 여러 알고리즘을 구현한다.
 * 
 * 전략 패턴은 정책(Policy)이라고도 합니다.
 * 
 * 전략을 선택하는 주체는 Client입니다.
 * 
 * # 구성요소
 * Client
 * 전략을 선택할 주체
 * 클라이언트가 전략을 문맥에 설정하고, 실행하도록 명령한다.
 * Context는 실제 전략을 수행하지만, 선택권은 없다
 * 심지어 전략을 인터페이스로 다루기 때문에 어떤 전략을 수행하는지도 모른다.
 * 
 * Context
 * 실제 전략을 실행할 문맥
 * 외부로 전략 객체 설정자를 노출해야 한다.
 * 클라이언트가 설정해준 전략을 실행(위임)한다. 구현을 알지 못한다.
 * 
 * Strategy
 * 추상화된 인터페이스
 * 
 * ConcreteStrategy (1 ~ N)개
 * 전략을 구현한 클래스
 * 캡슐화된 알고리즘
 * 
 * # 키워드
 * OCP, 런타임 
 * </pre>
 */
public class NavigationEx {
	public static void main(String[] args) {
		//클라이언트 코드는 편의상 익명 객체로 
		//클라이언트가 선택한 전략을 문맥이 실행한다.
		Client client = new Client(){
			@Override
			void run() {
				MapContext mapContext = new MapContext();
				mapContext.setStrategy(new CostFirst());
				mapContext.execute();
				
				mapContext.setStrategy(new DistanceFirst());
				mapContext.execute();
				
				mapContext.setStrategy(new ClearRoadFirst());
				mapContext.execute();
			}
		};
		client.run();
	}
	
	/**
	 * <pre>
	 * 1. 전략 인터페이스를 정의한다.
	 * 추상화된 공통 행동을 정의한다.
	 * </pre>
	 */
	static interface FindStrategy {
		public void findRoute();
		default int calculate(int price, int percent) {
			return price/100*(100 - percent );
		}
	}
	
	/**
	 * <pre>
	 * 2. 구현체를 만든다. 
	 * 알맞은 알고리즘을 구현한다.
	 *  </pre>
	 */
	static class DistanceFirst implements FindStrategy{
		@Override
		public void findRoute() {
			System.out.println("가장 빠른 길로!!");
		}
	}
	static class ClearRoadFirst implements FindStrategy{
		@Override
		public void findRoute() {
			System.out.println("안막히는 길로!!");
		}
	}
	static class CostFirst implements FindStrategy{
		@Override
		public void findRoute() {
			System.out.println("톨비없는 길로!!");
		}
	}
	
	/**
	 * <pre>
	 * 3. 전략을 사용할 문맥을 만든다.
	 * 실질적으로 전략 구현체를 실행을 담당한다.
	 *  </pre>
	 */
	
	static class MapContext{
		private FindStrategy strategy;

		public MapContext() {}
		public MapContext(FindStrategy strategy) {
			this.strategy = strategy;
		}

		public void setStrategy(FindStrategy strategy) {
			this.strategy = strategy;
		}
		
		public void execute() {
			strategy.findRoute();
		}
		
	}
	/**
	 * <pre>
	 * 4. 클라이언트가 "런타임"에 알맞은 전략을 선택해 주입한다.
	 * </pre>
	 */
	abstract static class Client{
		abstract void run();
	}
}
