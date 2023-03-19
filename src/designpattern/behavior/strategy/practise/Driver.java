package designpattern.behavior.strategy.practise;
/**
 * <pre>
 * 전략 패턴을 사용하면 런타임에 가장 적합한 알고리즘을 선택할 수 있습니다.
 * 일반적으로 알고리즘을 적용하는 데 사용되는 인터페이스로 시작한 다음 가능한 각 알고리즘에 대해 여러 번 구현합니다.
 * 
 * 전략 패턴은 정책(Policy)이라고도 합니다.
 * </pre>
 */
public class Driver {
	public static void main(String[] args) {
		AttackContext context = new AttackContext(()->System.out.println("기본 공격!"));
		context.attack();
		context.setStrategy(new BowStrategy());
		context.attack();
		context.setStrategy(new SwordStrategy());
		context.attack();
		
	}
}
