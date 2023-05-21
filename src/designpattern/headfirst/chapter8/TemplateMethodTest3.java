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
	//abstract 메서드가 없더라도 abstract를 붙여 인스턴스화를 방지할 수 있다.
	abstract static class CaffeineBeverage{
		//핵심 템플릿 메서드 final
		final void prepareRecipe() {
			 boilWater();
			 brew();
			 purInCup();
			 addCondiments();
		}
		//약간의 동작 차이만을 보이므로 재정의를 강제하도록 abstract 키워드를 붙인다.
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


