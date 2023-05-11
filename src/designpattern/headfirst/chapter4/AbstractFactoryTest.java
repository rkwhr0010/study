package designpattern.headfirst.chapter4;

public class AbstractFactoryTest {
	static abstract class Pizza{
		String name;
		
		Dough dough;
		Sauce sauce;
		Veggies[] veggies;
		Cheese cheese;
		Pepperoni pepperoni;
		Clams clam;
		
		
		public void setName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		public abstract void prepare();
		public void bake() {System.out.println("175도에서 25분 간 굽기");	}
		public void cut() {	System.out.println("피자를 사선으로 자르기");	}
		public void box() {	System.out.println("상자에 피자 담기");	}
		@Override
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
				for (int i = 0; i < veggies.length; i++) {
					result.append(veggies[i]);
					if (i < veggies.length-1) {
						result.append(", ");
					}
				}
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
	
	
	
	
	static interface Dough{	String toString();}
	static interface Sauce{	String toString();}
	static interface Cheese{ String toString();	}
	static interface Veggies{ String toString();	}
	static interface Pepperoni{ String toString();	}
	static interface Clams{ String toString();	}
	
	
	static class ThinCrustDough implements Dough {
		public String toString() {
			return "Thin Crust Dough";
		}
	}
	static class ThickCrustDough implements Dough {
		public String toString() {
			return "ThickCrust style extra thick crust dough";
		}
	}
	static class MozzarellaCheese implements Cheese {
		public String toString() {
			return "Shredded Mozzarella";
		}
	}
	static class ParmesanCheese implements Cheese {
		public String toString() {
			return "Shredded Parmesan";
		}
	}
	static class ReggianoCheese implements Cheese {
		public String toString() {
			return "Reggiano Cheese";
		}
	}
	static class FreshClams implements Clams {
		public String toString() {
			return "Fresh Clams from Long Island Sound";
		}
	}
	static class FrozenClams implements Clams {
		public String toString() {
			return "Frozen Clams from Chesapeake Bay";
		}
	}
	static class MarinaraSauce implements Sauce {
		public String toString() {
			return "Marinara Sauce";
		}
	}
	static class PlumTomatoSauce implements Sauce {
		public String toString() {
			return "Tomato sauce with plum tomatoes";
		}
	}
	static class BlackOlives implements Veggies {
		public String toString() {
			return "Black Olives";
		}
	}
	static class Eggplant implements Veggies {
		public String toString() {
			return "Eggplant";
		}
	}
	static class Garlic implements Veggies {
		public String toString() {
			return "Garlic";
		}
	}
	static class Mushroom implements Veggies {
		public String toString() {
			return "Mushrooms";
		}
	}
	static class Onion implements Veggies {
		public String toString() {
			return "Onion";
		}
	}
	static class RedPepper implements Veggies {
		public String toString() {
			return "Red Pepper";
		}
	}
	static class Spinach implements Veggies {
		public String toString() {
			return "Spinach";
		}
	}
	static class SlicedPepperoni implements Pepperoni {
		public String toString() {
			return "Sliced Pepperoni";
		}
	}
	static interface newProduct{}
	static class newnewProduct implements newProduct{}
	
	static interface PizzaIngredientFactory{
		Dough createDough();
		Sauce createSauce();
		Cheese createCheese();
		Veggies[] createVeggies();
		Pepperoni createPepperoni();
		Clams createClam();
	}
	
	static class NYPizzaIngredientFactory implements PizzaIngredientFactory{
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
			return new Veggies[] {
					new Garlic(),new Onion(), new Mushroom(), new RedPepper()
			};
		}
		public Pepperoni createPepperoni() {
			return new SlicedPepperoni();
		}
		public Clams createClam() {
			return new FreshClams();
		}
	}
	static class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory{
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
			return new Veggies[] {
					new Garlic(),new Onion(), new Mushroom(), new RedPepper()
			};
		}
		public Pepperoni createPepperoni() {
			return new SlicedPepperoni();
		}
		public Clams createClam() {
			return new FrozenClams();
		}
	}
	
	static class CheesePizza extends Pizza {
		private final PizzaIngredientFactory ingredientFactory;
		
		public CheesePizza(PizzaIngredientFactory ingredientFactory) {
			this.ingredientFactory = ingredientFactory;
		}
		
		@Override
		public void prepare() {
			System.out.println("준비 중: "+name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
		}
	}
	
	static class PepperoniPizza extends Pizza {
		private final PizzaIngredientFactory ingredientFactory;
		
		public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
			this.ingredientFactory = ingredientFactory;
		}
		@Override
		public void prepare() {
			System.out.println("준비 중: "+name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
			clam = ingredientFactory.createClam();
		}
	}
	static class ClamPizza extends Pizza {
		private final PizzaIngredientFactory ingredientFactory;
		
		public ClamPizza(PizzaIngredientFactory ingredientFactory) {
			this.ingredientFactory = ingredientFactory;
		}
		@Override
		public void prepare() {
			System.out.println("준비 중: "+name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
			clam = ingredientFactory.createClam();
		}
	}
	static class VeggiePizza extends Pizza {
		private final PizzaIngredientFactory ingredientFactory;
		
		public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
			this.ingredientFactory = ingredientFactory;
		}
		@Override
		public void prepare() {
			System.out.println("준비 중: "+name);
			dough = ingredientFactory.createDough();
			sauce = ingredientFactory.createSauce();
			cheese = ingredientFactory.createCheese();
			clam = ingredientFactory.createClam();
		}
	}

	
	abstract static class PizzaStore{
		public Pizza orderPizza(String type) {
			Pizza pizza;
			pizza = createPizza(type);
			
			pizza.prepare();
			pizza.bake();
			pizza.cut();
			pizza.box();
			
			return pizza;
		}
		protected abstract Pizza createPizza(String type);
	}

	static class NYPizzaStore extends PizzaStore{
		@Override //팩토리 메서드
		protected Pizza createPizza(String item) {
			Pizza pizza = null;
			PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
			
			if (item.equals("cheese")) {
				pizza = new CheesePizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 치즈 피자");
			} else if (item.equals("pepperoni")) {
				pizza = new PepperoniPizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 야채 피자");
			} else if (item.equals("clam")) {
				pizza = new ClamPizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 조개 피자");
			} else if (item.equals("veggie")) {
				pizza = new VeggiePizza(ingredientFactory);
				pizza.setName("뉴욕 스타일 페퍼로니 피자");
			}
			return pizza;
		}
	}

	static class ChicagoPizzaStore extends PizzaStore{
		
		@Override
		protected Pizza createPizza(String item) {
			Pizza pizza = null;
			PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
			
			if (item.equals("cheese")) {
				pizza = new CheesePizza(ingredientFactory);
				pizza.setName("시카고 스타일 치즈 피자");
			} else if (item.equals("pepperoni")) {
				pizza = new PepperoniPizza(ingredientFactory);
				pizza.setName("시카고 스타일 야채 피자");
			} else if (item.equals("clam")) {
				pizza = new ClamPizza(ingredientFactory);
				pizza.setName("시카고 스타일 조개 피자");
			} else if (item.equals("veggie")) {
				pizza = new VeggiePizza(ingredientFactory);
				pizza.setName("시카고 스타일 페퍼로니 피자");
			}
			return pizza;
		}
	}
	
	
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
 
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}
	
}
