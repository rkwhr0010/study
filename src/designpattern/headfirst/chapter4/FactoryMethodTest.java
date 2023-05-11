package designpattern.headfirst.chapter4;

import java.util.ArrayList;
import java.util.List;

public class FactoryMethodTest {
	static abstract class Pizza{
		String name;
		String dough;
		String sauce;
		List<String> toppings = new ArrayList<>();
		
		public String getName() {	return name;	}
		
		public void prepare() {
			System.out.println("준비중 ... " + name);
			System.out.println("도우를 돌리는 중 ...");
			System.out.println("소스를 뿌리는 중 ...");
			System.out.println("토핑을 올리는 중 ...");

			toppings.forEach(topping->System.out.println(" "+topping));

		}
		public void bake() {System.out.println("175도에서 25분 간 굽기 ... ");	}
		public void cut() {	System.out.println("피자를 사선으로 짜르기 ... ");}
		public void box() {	System.out.println("피자 상자에 담기 ... ");	}
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
	
	//추상 팩터리
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
		//객체 생성을 서브클래스로 위임
		protected abstract Pizza createPizza(String type);
	}
	
	static class NYPizzaStore extends PizzaStore{
		@Override
		protected Pizza createPizza(String type) {
			Pizza pizza = null;
			if (type.equals("cheese")) {
				pizza = new NYStyleCheesePizza();
			} else if (type.equals("pepperoni")) {
				pizza = new NYStylePepperoniPizza();
			} else if (type.equals("clam")) {
				pizza = new NYStyleClamPizza();
			} else if (type.equals("veggie")) {
				pizza = new NYStyleVeggiePizza();
			}
			return pizza;
		}
		
		static class NYStylePepperoniPizza extends Pizza {
			public NYStylePepperoniPizza() {
				name = "NYStyle 페퍼로니 피자";
				dough = "크러스트";
				sauce = "마리나라 소스";
				toppings.add("슬라이스 페퍼로니");
				toppings.add("슬라이스 양파");
				toppings.add("모짜렐라 치즈");
			}
		}
		static class NYStyleClamPizza extends Pizza {
			public NYStyleClamPizza() {
				name = "NYStyle 조개 피자";
				dough = "씬 크러스트";
				sauce = "마늘 소스";
				toppings.add("조개");
				toppings.add("모짜렐라 치즈");
			}
		}
		static class NYStyleVeggiePizza extends Pizza {
			public NYStyleVeggiePizza() {
				name = "NYStyle 야채 피자";
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
		static class NYStyleCheesePizza extends Pizza {
			public NYStyleCheesePizza() {
				name = "NYStyle 피즈 피자";
				dough = "크러스트";
				sauce = "마리나라 피자 소스";
				toppings.add("생 모짜렐라");
				toppings.add("파마산");
			}
		}
	}
	
	static class ChicagoPizzaStore extends PizzaStore{
		@Override
		protected Pizza createPizza(String type) {
			Pizza pizza = null;
			if (type.equals("cheese")) {
				pizza = new ChicagoStyleCheesePizza();
			} else if (type.equals("pepperoni")) {
				pizza = new ChicagoStylePepperoniPizza();
			} else if (type.equals("clam")) {
				pizza = new ChicagoStyleClamPizza();
			} else if (type.equals("veggie")) {
				pizza = new ChicagoStyleVeggiePizza();
			}
			return pizza;
		}
		
		static class ChicagoStylePepperoniPizza extends Pizza {
			public ChicagoStylePepperoniPizza() {
				name = "ChicagoStyle 페퍼로니 피자";
				dough = "크러스트";
				sauce = "마리나라 소스";
				toppings.add("슬라이스 페퍼로니");
				toppings.add("슬라이스 양파");
				toppings.add("모짜렐라 치즈");
			}
			@Override
			public void cut() {
				System.out.println("네모난 모양으로 피자 자르기");
			}
		}
		static class ChicagoStyleClamPizza extends Pizza {
			public ChicagoStyleClamPizza() {
				name = "ChicagoStyle 조개 피자";
				dough = "씬 크러스트";
				sauce = "마늘 소스";
				toppings.add("조개");
				toppings.add("모짜렐라 치즈");
			}
		}
		static class ChicagoStyleVeggiePizza extends Pizza {
			public ChicagoStyleVeggiePizza() {
				name = "ChicagoStyle 야채 피자";
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
		static class ChicagoStyleCheesePizza extends Pizza {
			public ChicagoStyleCheesePizza() {
				name = "ChicagoStyle 피즈 피자";
				dough = "크러스트";
				sauce = "마리나라 피자 소스";
				toppings.add("생 모짜렐라");
				toppings.add("파마산");
			}
		}
	}
	
	static class CaliforniaPizzaStore extends PizzaStore{
		@Override
		protected Pizza createPizza(String type) {
			Pizza pizza = null;
			if (type.equals("cheese")) {
				pizza = new CaliforniaStyleCheesePizza();
			} else if (type.equals("pepperoni")) {
				pizza = new CaliforniaStylePepperoniPizza();
			} else if (type.equals("clam")) {
				pizza = new CaliforniaStyleClamPizza();
			} else if (type.equals("veggie")) {
				pizza = new CaliforniaStyleVeggiePizza();
			}
			return pizza;
		}
		
		static class CaliforniaStylePepperoniPizza extends Pizza {
			public CaliforniaStylePepperoniPizza() {
				name = "CaliforniaStyle 페퍼로니 피자";
				dough = "크러스트";
				sauce = "마리나라 소스";
				toppings.add("슬라이스 페퍼로니");
				toppings.add("슬라이스 양파");
				toppings.add("모짜렐라 치즈");
			}
		}
		static class CaliforniaStyleClamPizza extends Pizza {
			public CaliforniaStyleClamPizza() {
				name = "CaliforniaStyle 조개 피자";
				dough = "씬 크러스트";
				sauce = "마늘 소스";
				toppings.add("조개");
				toppings.add("모짜렐라 치즈");
			}
		}
		static class CaliforniaStyleVeggiePizza extends Pizza {
			public CaliforniaStyleVeggiePizza() {
				name = "CaliforniaStyle 야채 피자";
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
		static class CaliforniaStyleCheesePizza extends Pizza {
			public CaliforniaStyleCheesePizza() {
				name = "CaliforniaStyle 피즈 피자";
				dough = "크러스트";
				sauce = "마리나라 피자 소스";
				toppings.add("생 모짜렐라");
				toppings.add("파마산");
			}
		}
	}
	
	public static void main(String[] args) {
		//추상화 타입
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
		System.out.println(orderPizza(nyStore,"cheese"));
		System.out.println(orderPizza(chicagoPizzaStore,"cheese"));
	}
	
	//추상화된 타입으로 다루기
	static Pizza orderPizza(PizzaStore pizzaStore, String type) {
		return pizzaStore.orderPizza(type);
	}
}
