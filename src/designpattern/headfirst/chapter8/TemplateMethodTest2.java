package designpattern.headfirst.chapter8;

public class TemplateMethodTest2 {
	
	//더 추상화 할 요소는?
	abstract static class CaffeineBeverage{
		abstract void prepareRecipe();
		public void boilWater() {
			System.out.println("물 끊이는 중");
		}
		public void purInCup() {
			System.out.println("컵에 따르는 중");
		}
	}
	
	static class Coffee extends CaffeineBeverage{
		void prepareRecipe() {
			boilWater();
			brewCoffeeGrinds();
			purInCup();
			addSugarAndMilk();
		}
		
		
		public void brewCoffeeGrinds() {
			System.out.println("필터로 커피를 우려내는 중");
		}
		
		public void addSugarAndMilk() {
			System.out.println("설탕과 우유를 추가하는 중");
		}
	}
	static class Tea extends CaffeineBeverage{
		void prepareRecipe() {
			boilWater();
			steepTeaBag();
			purInCup();
			addLemon();
		}
		public void steepTeaBag() {
			System.out.println("찻잎을 우려내는 중");
		}
		public void addLemon() {
			System.out.println("레몬을 추가하는 중");
		}
	}
}


