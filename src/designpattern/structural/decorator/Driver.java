package designpattern.structural.decorator;

/**
 * <pre>
 * 데코레이터 패턴을 사용하여 객체에 정적 또는 동적으로 추가 책임을 부여할 수 있습니다. 
 * Decorator는 원본 개체에 향상된 인터페이스를 제공합니다.
 * 이 패턴의 구현에서 상속보다 구성을 선호하므로 각 데코레이팅 요소에 대해 계속해서 서브클래싱하는 오버헤드를 줄일 수 있습니다. 
 * 이 디자인과 관련된 재귀는 필요한 만큼 개체를 장식하는 데 사용할 수 있습니다.
 * 
 * 데코레이터 패턴은 "객체에 유연한 추가 책임을 동적으로 부여"한다
 * 즉, 데코레이터 패턴은 런타임에 개체의 기능을 확장하기 위해 상속 대신 구성을 사용합니다.
 * 
 * 반드시 추가 책임을 부여하지는 않는다. 데코레이터 클래스에서 무언가를 제거하는 책임을 맡는다면
 * 말장난 같지만 제거 책임을 맡으므로, 결과만 보면 책임이 덜어질 수 있다.
 * </pre>
 * @author kks
 *
 */
public class Driver {
	/**
	 * <pre>카페 주문 시스템에 단품 주문에서 옵션 추가 기능을 위해 기존 시스템을 고쳐야하는 상황</pre>
	 */
	
	abstract static class Beverage{
		private String menu;
		private String desciption;
		private int cost;
		
		protected Beverage() {}
		
		protected Beverage(String menu, String desciption, int cost) {
			super();
			this.menu = menu;
			this.desciption = desciption;
			this.cost = cost;
		}
		public String getMenu() {
			return menu;
		}
		public String getDesciption() {
			return desciption;
		}
		public int getCost() {
			return cost;
		}
		
	}
	
	static class Americano extends Beverage{
		public Americano() {
			super("아메리카노", "은은한 향과 맛",  2000 );
		}
	}
	static class Mocha extends Beverage{
		public Mocha() {
			super("모카", "초코 향과 맛",  2500 );
		}
	}
	/**
	 * 데코레이터 클래스
	 * 런타임 시점에 변경을 용이하게 하기 위해 구성을 이용한다.
	 */
	
	abstract static class BeverageDecorator extends Beverage{
		private Beverage beverage;
		
		protected BeverageDecorator(Beverage beverage, String menu, String desciption, int cost) {
			super(menu, desciption, cost);
			this.beverage = beverage;
		}
		
		@Override
		public String getMenu() {
			return super.getMenu()+" "+beverage.getMenu();
		}
		@Override
		public String getDesciption() {
			return super.getDesciption()+" "+ beverage.getDesciption();
		}
		@Override
		public int getCost() {
			return super.getCost()+beverage.getCost();
		}
	}
	
	static class Ice extends BeverageDecorator{
		public Ice(Beverage beverage) {
			super(beverage, "아이스", "얼음 추가", 500);
		}
	}
	
	static class DecoratorFactory{
		private DecoratorFactory() {
			throw new IllegalStateException("Utility class");
		}
		
		static Beverage ice(Beverage beverage) {
			return new Ice(beverage);
		}
		
		static Beverage shot(Beverage beverage) {
			return new BeverageDecorator(beverage, "샷추가", "" , 500) {};
		}
		
	}
	/**
	 * <pre>
	 * 데코레이터 패턴은 중첩 구조가 복잡해지면, 가독성이 나빠진다.
	 * </pre>
	 */
	public static void main(String[] args) {
		Beverage americano = new Americano();
		Beverage mocha = new Mocha();
		System.out.println(americano.getMenu()+" "+americano.getDesciption() + " " + americano.getCost());
		//데코레이터
		americano = DecoratorFactory.ice(americano);
		System.out.println(americano.getMenu()+" "+americano.getDesciption() + " " + americano.getCost());
		
		americano = DecoratorFactory.ice(americano);
		System.out.println(americano.getMenu()+" "+americano.getDesciption() + " " + americano.getCost());
		
		System.out.println(mocha.getMenu()+" "+mocha.getDesciption() + " " + mocha.getCost());
		mocha = DecoratorFactory.shot(mocha);
		System.out.println(mocha.getMenu()+" "+mocha.getDesciption() + " " + mocha.getCost());
		
	}
	
}
