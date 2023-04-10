package designpattern.headfirst.chapter4;

import designpattern.headfirst.chapter4.AbstractFactory.PizzaStore.PizzaType;

public class AbstractFactory {
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
 
		Pizza pizza = nyStore.orderPizza(PizzaType.CHEESE);
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza(PizzaType.CHEESE);
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza(PizzaType.CLAM);
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza(PizzaType.CLAM);
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza(PizzaType.PEPPERONI);
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza(PizzaType.PEPPERONI);
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza(PizzaType.VEGGIE);
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza(PizzaType.VEGGIE);
		System.out.println("Joel ordered a " + pizza + "\n");
	}
	
	/*야채*/
	static interface Veggies{
		String toString();
	}
	static class BlackOlives implements Veggies {
		public String toString() {
			return "검정 올리브";
		}
	}
	static class Eggplant implements Veggies {
		public String toString() {
			return "가지";
		}
	}
	static class Garlic implements Veggies {
		public String toString() {
			return "마늘";
		}
	}
	static class Mushroom implements Veggies {
		public String toString() {
			return "버섯";
		}
	}
	static class Onion implements Veggies {
		public String toString() {
			return "양파";
		}
	}
	static class RedPepper implements Veggies {
		public String toString() {
			return "빨간 피망";
		}
	}
	static class Spinach implements Veggies {
		public String toString() {
			return "Spinach";
		}
	}
	/*치즈*/
	static interface Cheese {
		public String toString();
	}
	static class MozzarellaCheese implements Cheese {
		public String toString() {
			return "슈레드 모짜렐라 치즈";
		}
	}
	static class ParmesanCheese implements Cheese {
		public String toString() {
			return "슈레드 파마산 치즈";
		}
	}
	static class ReggianoCheese implements Cheese {
		public String toString() {
			return "레지아노 치즈";
		}
	}
	/*조개*/
	static interface Clam {
		public String toString();
	}
	static class FreshClams implements Clam {
		public String toString() {
			return "생물 조개";
		}
	}
	static class FrozenClams implements Clam {
		public String toString() {
			return "냉동 조개";
		}
	}
	/*도우*/
	static interface Dough {
		public String toString();
	}
	static class ThickCrustDough implements Dough {
		public String toString() {
			return "두툼하고 빠삭한 스타일 도우";
		}
	}
	static class ThinCrustDough implements Dough {
		public String toString() {
			return "얇고 빠삭한 도우";
		}
	}
	/*소스*/
	static interface Sauce {
		public String toString();
	}
	static class MarinaraSauce implements Sauce {
		public String toString() {
			return "마리나라 소스";
		}
	}
	static class PlumTomatoSauce implements Sauce {
		public String toString() {
			return "이태리 토마토 소스";
		}
	}
	/*페퍼로니*/
	static interface Pepperoni {
		public String toString();
	}
	static class SlicedPepperoni implements Pepperoni {
		public String toString() {
			return "얇게 썰은 페퍼로니";
		}
	}

	/* 핵심 ## 추상 팩토리 정의*/
	static interface PizzaIngredientFactory {
		public Dough createDough();
		public Sauce createSauce();
		public Cheese createCheese();
		public Veggies[] createVeggies();
		public Pepperoni createPepperoni();
		public Clam createClam();
	}
	//시카고는 내륙이라 냉동 조개를 씀
	static class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
		public Dough createDough() {
			return new ThickCrustDough();
		}
		public Sauce createSauce() {
			return new PlumTomatoSauce();
		}
		public Cheese createCheese() {
			return new MozzarellaCheese();
		}
		public Veggies[] createVeggies() {
			return new Veggies[] { new BlackOlives(), new Spinach(), new Eggplant() };
		}
		public Pepperoni createPepperoni() {
			return new SlicedPepperoni();
		}
		public Clam createClam() {
			return new FrozenClams();
		}
	}
	//뉴욕은 연안이라 생물 조개 사용
	static class NYPizzaIngredientFactory implements PizzaIngredientFactory {
		public Dough createDough() {
			return new ThinCrustDough();
		}
		public Sauce createSauce() {
			return new MarinaraSauce();
		}
		public Cheese createCheese() {
			return new ReggianoCheese();
		}
		public Veggies[] createVeggies() {
			return new Veggies[] { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		}
		public Pepperoni createPepperoni() {
			return new SlicedPepperoni();
		}
		public Clam createClam() {
			return new FreshClams();
		}
	}
	
	/*피자 군*/
	abstract static class Pizza{
		//팩토리를 사용하여 거의 동일하나 다른 스타일의 피자를 생산할 수 있다.
		PizzaIngredientFactory ingredientFactory;
		String name;
		
		Dough dough;
		Sauce sauce;
		Veggies[] veggies;
		Cheese cheese;
		Pepperoni pepperoni;
		Clam clam;
		
		abstract void prepare();
		
		void bake() {	System.out.println("25분 간 굽습니다.");	}
		void cut() {System.out.println("대각선으로 피자를 썰어줍니다.");}
		void box() {System.out.println("피자박스에 피자를 옯겨 포장합니다.");}
		
		void setName(String name) {
			this.name = name;
		}
		String getName() {
			return name;
		}
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append("---- " + name + " ----\n");
			if (dough != null) {
				result.append(dough);
				result.append("\n");
			}
			if (sauce != null) {
				result.append(sauce);
				result.append("\n");
			}
			if (cheese != null) {
				result.append(cheese);
				result.append("\n");
			}
			if (veggies != null) {
				for(Veggies veggie : veggies) {
					result.append(veggie+", ");
				}
				result.delete(result.length()-2, result.length());
				result.append("\n");
			}
			if (clam != null) {
				result.append(clam);
				result.append("\n");
			}
			if (pepperoni != null) {
				result.append(pepperoni);
				result.append("\n");
			}
			return result.toString();
		}
	}
	
	static class CheesePizza extends Pizza {
	 
		public CheesePizza(PizzaIngredientFactory ingredientFactory) {
			super.ingredientFactory = ingredientFactory;
		}
	 
		void prepare() {
			System.out.println("준비중..." + name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
		}
	}
	static class ClamPizza extends Pizza {
		PizzaIngredientFactory ingredientFactory;
	 
		public ClamPizza(PizzaIngredientFactory ingredientFactory) {
			this.ingredientFactory = ingredientFactory;
		}
	 
		void prepare() {
			System.out.println("준비중..." + name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
			clam = ingredientFactory.createClam();
		}
	}
	static class PepperoniPizza extends Pizza {
		PizzaIngredientFactory ingredientFactory;
	 
		public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
			this.ingredientFactory = ingredientFactory;
		}
	 
		void prepare() {
			System.out.println("준비중..." + name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
			veggies = ingredientFactory.createVeggies();
			pepperoni = ingredientFactory.createPepperoni();
		}
	}
	static class VeggiePizza extends Pizza {
	 
		public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
			super.ingredientFactory = ingredientFactory;
		}
	 
		void prepare() {
			System.out.println("준비중..." + name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
			veggies = ingredientFactory.createVeggies();
		}
	}

	
	
	
	public abstract static class PizzaStore {
		public enum PizzaType{
			CHEESE, VEGGIE, CLAM, PEPPERONI
		}
		//팩토리 메서드 패턴, 객체 생성을 서브타입에 맡긴다.
		protected abstract Pizza createPizza(PizzaType item);
		
		public Pizza orderPizza(PizzaType type) {
			Pizza pizza = createPizza(type);
			System.out.println("--- Making a " + pizza.getName() + " ---");
			pizza.prepare();
			pizza.bake();
			pizza.cut();
			pizza.box();
			return pizza;
		}
	}
	static class ChicagoPizzaStore extends PizzaStore {
		protected Pizza createPizza(PizzaType item) {
			Pizza pizza = null;
			PizzaIngredientFactory ingredientFactory =
			new ChicagoPizzaIngredientFactory();
			
			switch (item) {
			case CHEESE:
				pizza = new CheesePizza(ingredientFactory);
				pizza.setName("시카고 스타일 치즈 피자");
				break;
			case VEGGIE:
				pizza = new VeggiePizza(ingredientFactory);
				pizza.setName("시카고 스타일 야채 피자");
				break;
			case CLAM:
				pizza = new ClamPizza(ingredientFactory);
				pizza.setName("시카고 스타일 조개 피자");
				break;
			case PEPPERONI:
				pizza = new PepperoniPizza(ingredientFactory);
				pizza.setName("시카고 스타일 페퍼로니 피자");
				break;
			default:
				throw new IllegalArgumentException();
			}
			return pizza;
		}

	}
	static class NYPizzaStore extends PizzaStore {
		 
		protected Pizza createPizza(PizzaType item) {
			Pizza pizza = null;
			PizzaIngredientFactory ingredientFactory =
			new ChicagoPizzaIngredientFactory();
			
			switch (item) {
			case CHEESE:
				pizza = new CheesePizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 치즈 피자");
				break;
			case VEGGIE:
				pizza = new VeggiePizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 야채 피자");
				break;
			case CLAM:
				pizza = new ClamPizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 조개 피자");
				break;
			case PEPPERONI:
				pizza = new PepperoniPizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 페퍼로니 피자");
				break;
			default:
				throw new IllegalArgumentException();
			}
			return pizza;
		}
	}
	
	
	
}
