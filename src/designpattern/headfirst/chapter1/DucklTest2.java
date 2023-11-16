package designpattern.headfirst.chapter1;

public class DucklTest2 {
	static interface FlyBehavior{
		void fly();
	}
	static interface QuackBehavior{
		void quack();
	}
	
	static class FlyWithWings implements FlyBehavior{
		public void fly() {
			System.out.println("난다 고래!!!");
		}
	}
	static class FlyNoWay implements FlyBehavior{
		public void fly() {
			System.out.println("못난다 고래!!!");
		}
	}
	static class FlyRocketPowered implements FlyBehavior{
		public void fly() {
			System.out.println("로켓트 추친력!! 날기!!");
		}
	}
	
	static class Quack implements QuackBehavior{
		public void quack() {
			System.out.println("꽥꽥!!!");
		}
	}
	static class Squeak implements QuackBehavior{
		public void quack() {
			System.out.println("삑삑삑!!!");
		}
	}
	static class MuteQuack implements QuackBehavior{
		public void quack() {
			System.out.println("----무음");
		}
	}
	
	static abstract class Duck{
		FlyBehavior flyBehavior;
		QuackBehavior quackBehavior;
		
		public void setFlyBehavior(FlyBehavior flyBehavior) {
			this.flyBehavior = flyBehavior;
		}
		public void setQuackBehavior(QuackBehavior quackBehavior) {
			this.quackBehavior = quackBehavior;
		}
		public void performQuack() {
			quackBehavior.quack();
		}
		public void performFly() {
			flyBehavior.fly();
		}
		public void swim() {
			System.out.println("오리는 물에 뜬다, 가짜 오리라도 멀이죠!!");
		}
		abstract void display();
	}
	static class MallardDuck extends Duck{
		public MallardDuck() {
			super.quackBehavior = new Quack();
			super.flyBehavior = new FlyWithWings();
		}
		public void display() {
			System.out.println("저는 물오리입니다.");
		}
	}
	
	static class ModelDuck extends Duck{
		public ModelDuck() {
			quackBehavior = new Quack();
			flyBehavior = new FlyNoWay();
		}
		void display() {
			System.out.println("모형 오리!!");
		}
		
	}
	public static void main(String[] args) {
		Duck mallard = new MallardDuck();
		mallard.performFly();
		mallard.performQuack();

		//구성과 위임으로 런타임 중에 동작을 변경하고 있다!!!!
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
		
	}
}
