package designpattern.headfirst.chapter8;

public class TemplateMethodTest3 {
	abstract static class AbstractClass{
		final void templateMethod() {
			primitiveOperation1();
			primitiveOperation2();
			concreteOperation();
			hook();
		}
		public void hook() {}
		public void concreteOperation() {}
		protected abstract void primitiveOperation2();
		protected abstract void primitiveOperation1();
	}
	abstract static class CaffeineBeverage{
		final void prepareRecipe() {
			 boilWater();
			 brew();
			 purInCup();
			 addCondiments();
		}
		protected abstract void addCondiments();
		protected abstract void brew();
		public void boilWater() {
			System.out.println("물 끊이는 중");
		}
		public void purInCup() {
			System.out.println("컵에 따르는 중");
		}
	}
	
	static class Coffee extends CaffeineBeverage{
		@Override
		public void addCondiments() {
			System.out.println("설탕과 우유를 추가하는 중");
		}
		@Override
		public void brew() {
			System.out.println("필터로 커피를 우려내는 중");
		}
	}
	static class Tea extends CaffeineBeverage{
		@Override
		protected void addCondiments() {
			System.out.println("레몬을 추가하는 중");
		}
		@Override
		protected void brew() {
			System.out.println("찻잎을 우려내는 중");
			
		}
	}
}


