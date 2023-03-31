package javabasic.io;

import java.util.ArrayList;
import java.util.List;

public class SimpleFactoryTest {
	static abstract class Pizza{
		String name;
		String dough;
		String sauce;
		List<String> toppings = new ArrayList<>();
		
		public String getName() {
			return name;
		}
		
		public void prepare() {	System.out.println("준비중 ... " + name);	}
		public void bake() {System.out.println("굽는 중 ... "+ name);	}
		public void cut() {	System.out.println("짜르는 중 ... " + name);	}
		public void box() {	System.out.println("포장 중 ... "+ name);	}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("---- " +name+" ----\n")
				.append(dough+"\n")
				.append(sauce+"\n");
			toppings.forEach(topping -> sb.append(topping+"\n"));
			return sb.toString();
		}
	}
	
	static class PepperoniPizza extends Pizza {
		public PepperoniPizza() {
			name = "페퍼로니 피자";
			dough = "크러스트";
			sauce = "마리나라 소스";
			toppings.add("슬라이스 페퍼로니");
			toppings.add("슬라이스 양파");
			toppings.add("모짜렐라 치즈");
		}
	}
	static class ClamPizza extends Pizza {
		public ClamPizza() {
			name = "조개 피자";
			dough = "씬 크러스트";
			sauce = "마늘 소스";
			toppings.add("조개");
			toppings.add("모짜렐라 치즈");
		}
	}
	static class VeggiePizza extends Pizza {
		public VeggiePizza() {
			name = "야채 피자";
			dough = "크러스트";
			sauce = "마리나라 소스";
			toppings.add("슈레드 모짜랄라");
			toppings.add("파마산");
			toppings.add("다신 양파");
			toppings.add("슬라이스 버섯");
			toppings.add("슬라이스 피망");
			toppings.add("슬라이스 블랙 올리브");
		}
	}
	static class CheesePizza extends Pizza {
		public CheesePizza() {
			name = "피즈 피자";
			dough = "크러스트";
			sauce = "마리나라 피자 소스";
			toppings.add("생 모짜렐라");
			toppings.add("파마산");
		}
	}
	
	static class SimplePizzaFactory {
		public Pizza createPizza(String type) {
			Pizza pizza = null;
			if (type.equals("cheese")) {
				pizza = new CheesePizza();
			} else if (type.equals("pepperoni")) {
				pizza = new PepperoniPizza();
			} else if (type.equals("clam")) {
				pizza = new ClamPizza();
			} else if (type.equals("veggie")) {
				pizza = new VeggiePizza();
			}
			return pizza;
		}
	}
	
	static class PizzaStore{
		SimplePizzaFactory pizzaFactory;

		public PizzaStore(SimplePizzaFactory pizzaFactory) {
			this.pizzaFactory = pizzaFactory;
		}
		
		public Pizza orderPizza(String type) {
			Pizza pizza;
			pizza = pizzaFactory.createPizza(type);
			
			pizza.prepare();
			pizza.bake();
			pizza.cut();
			pizza.box();
			
			return pizza;
		}
		
	}
	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore store = new PizzaStore(factory);

		Pizza pizza = store.orderPizza("cheese");
		System.out.println("주문하신 피자 " + pizza.getName() + "\n");
		System.out.println(pizza);
 
		pizza = store.orderPizza("veggie");
		System.out.println("주문하신 피자 " + pizza.getName() + "\n");
		System.out.println(pizza);
	}
	
}
