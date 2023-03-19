package designpattern.behavioral.strategy;

/**
 * <pre>
 * 전략 패턴을 사용하면 런타임에 가장 적합한 알고리즘을 선택할 수 있습니다.
 * 일반적으로 알고리즘을 적용하는 데 사용되는 인터페이스로 시작한 다음 가능한 각 알고리즘에 대해 여러 번 구현합니다.
 * 
 * 전략 패턴은 정책(Policy)이라고도 합니다.
 * </pre>
 *
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
