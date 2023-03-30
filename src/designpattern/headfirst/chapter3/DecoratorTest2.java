package designpattern.headfirst.chapter3;

public class DecoratorTest2 {
	//Compenet
	static abstract class Beverage{
		public enum Size {TALL,GRANDE,VENTI}
		Size size =Size.TALL;
		
		String decription = "제목 없음";
		public String getDecription() {
			return decription;
		}
		public Size getSize() {
			return size;
		}
		public void setSize(Size size) {
			this.size = size;
		}
		public abstract double cost();
	}
	/**
	 * Decorator
	 * 형태만 (다형성) 맞추기 위해 상속(구현)하지만,
	 * 행동은 구성와 위임으로 런타임 시 동적으로 변경 가능하다.
	 */
	static abstract class CondimentDecorator extends Beverage{
		//다형성
		Beverage beverage;
		@Override
		public abstract String getDecription();
	}
	
	
	static class Espresso extends Beverage{
		public Espresso() {
			super.decription = "에스프레소";
		}
		@Override
		public double cost() {
			return 1.99;
		}
	}
	
	static class HouseBlend extends Beverage{
		public HouseBlend() {
			super.decription = "하우스 블렌드 커피";
		}
		@Override
		public double cost() {
			return .89;
		}
	}
	static class DarkRoast extends Beverage{
		public DarkRoast() {
			super.decription = "다크로스트 커피";
		}
		@Override
		public double cost() {
			return .99;
		}
	}
	static class Decaf extends Beverage{
		public Decaf() {
			super.decription = "디카페인 커피";
		}
		@Override
		public double cost() {
			return 1.05;
		}
	}
	
	static class Mocha extends CondimentDecorator{
		public Mocha(Beverage beverage) {
			super.beverage = beverage;
		}
		@Override
		public String getDecription() {
			return beverage.getDecription() + ", 모카";
		}
		@Override
		public double cost() {
			return beverage.cost()+ .20;
		}
	}
	static class Milk extends CondimentDecorator{
		public Milk(Beverage beverage) {
			super.beverage = beverage;
		}
		@Override
		public String getDecription() {
			return beverage.getDecription() + ", 우유";
		}
		@Override
		public double cost() {
			return beverage.cost()+.10;
		}
	}
	static class Whip extends CondimentDecorator{
		public Whip(Beverage beverage) {
			super.beverage = beverage;
		}
		@Override
		public String getDecription() {
			return beverage.getDecription() + ", 휘핑크림";
		}
		@Override
		public double cost() {
			return beverage.cost()+.10;
		}
	}
	static class Soy extends CondimentDecorator{
		public Soy(Beverage beverage) {
			super.beverage = beverage;
		}
		@Override
		public String getDecription() {
			return beverage.getDecription() + ", 두유";
		}
		@Override
		public double cost() {
			return beverage.cost()+.15;
		}
	}
	
	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		System.out.println(beverage.getDecription()+" $"+beverage.cost());
		
		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		System.out.println(beverage2.getDecription()+" $"+beverage2.cost());
		
		Beverage beverage3 = new HouseBlend();
		beverage3 = new Soy(beverage3);
		beverage3 = new Mocha(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.println(beverage3.getDecription()+" $"+beverage3.cost());
		
	}
	
}
