package designpattern.behavioral.strategy;

/**
 * <pre>
 * 전략 패턴을 사용하면 런타임에 가장 적합한 알고리즘을 선택할 수 있습니다.
 * 인터페이스를 정의 후 여러 알고리즘을 구현한다.
 * 
 * 전략 패턴은 정책(Policy)이라고도 합니다.
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
public class StrategeDriver {
	public static void main(String[] args) {
		Strategy eventDiscount = new EventDiscount();
		Strategy noDiscount = new NoDiscount();
		
		Context context = new Context(noDiscount);
		System.out.println(context.execute(65000));
		context.setStrategy(eventDiscount);
		System.out.println(context.execute(65000));
		context.setStrategy(price -> {
			System.out.println("반값 할인!!");
			return price / 100*(100-50); 
		});
		System.out.println(context.execute(65000));
	}
	
	/**
	 * <pre>
	 * 1. 전략 인터페이스를 정의한다.
	 * 추상화된 공통 행동을 정의한다.
	 * </pre>
	 */
	static interface Strategy {
		int execute(int price);
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
	static class EventDiscount implements Strategy{
		@Override
		public int execute(int price) {
			System.out.println("할인 금액 적용!!");
			return calculate(price,30);
		}
	}
	static class NoDiscount implements Strategy{
		@Override
		public int execute(int price) {
			System.out.println("결제 완료!!");
			return price;
		}
	}
	
	/**
	 * <pre>
	 * 3. 전략을 사용할 문맥을 만든다.
	 * 실질적으로 전략 구현체를 실행을 담당한다.
	 *  </pre>
	 */
	
	static class Context{
		private Strategy strategy;

		public Context(Strategy strategy) {
			super();
			this.strategy = strategy;
		}

		public void setStrategy(Strategy strategy) {
			this.strategy = strategy;
		}
		
		public int execute(int price) {
			return strategy.execute(price);
		}
		
	}
	
	
	
}
