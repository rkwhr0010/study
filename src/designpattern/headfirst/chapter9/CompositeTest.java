package designpattern.headfirst.chapter9;

import java.util.ArrayList;
import java.util.List;

public class CompositeTest {
	static abstract class MenuComponent{
		public void add(MenuComponent menuComponent) {
			throw new UnsupportedOperationException();
		}
		public void remove(MenuComponent menuComponent) {
			throw new UnsupportedOperationException();
		}
		public MenuComponent getChild(int i) {
			throw new UnsupportedOperationException();
		}
		public String getName() {
			throw new UnsupportedOperationException();
		}
		public String getDescription() {
			throw new UnsupportedOperationException();
		}
		public double getPrice() {
			throw new UnsupportedOperationException();
		}
		public boolean isVegetarian() {
			throw new UnsupportedOperationException();
		}
		public void print() {
			throw new UnsupportedOperationException();
		}
	}
	
	static class MenuItem extends MenuComponent{
		String name;
		String description;
		boolean vegetarian;
		double price;
		
		public MenuItem(String name, String description, boolean vegetarian, double price) {
			this.name = name;
			this.description = description;
			this.vegetarian = vegetarian;
			this.price = price;
		}
		
		public String getName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
		
		public boolean isVegetarian() {
			return vegetarian;
		}
		
		public double getPrice() {
			return price;
		}
		
		public void print() {
			System.out.print("  "+getName());
			if(isVegetarian()) {
				System.out.print("(v)");
			}
			System.out.println(", "+getPrice());
			System.out.println("    -- "+getDescription());
		}
		
	}
	
	static class Menu extends MenuComponent{
		List<MenuComponent> menuComponents = new ArrayList<>();
		String name;
		String description;
		
		public Menu(String name, String description) {
			this.name = name;
			this.description = description;
		}
		
		public void add(MenuComponent menuComponent) {
			menuComponents.add(menuComponent);
		}
		public void remove(MenuComponent menuComponent) {
			menuComponents.remove(menuComponent);
		}
		public MenuComponent getChild(int i) {
			return menuComponents.get(i);
		}
		
		public String getName() {
			return name;
		}
		public String getDescription() {
			return description;
		}
		
		public void print() {
			System.out.print("\n"+getName());
			System.out.println(", "+getDescription());
			System.out.println("----------------------");
			
			for(MenuComponent menuComponent : menuComponents) {
				menuComponent.print();
			}
			
		}
	}
	
	static class Waitress{
		MenuComponent allmenus;

		public Waitress(MenuComponent allmenus) {
			this.allmenus = allmenus;
		}
		
		public void printMenu() {
			allmenus.print();
		}
	}
	
	
	public static void main(String[] args) {
		MenuComponent phMenu = new Menu("팬케이크 하우스 메뉴", "아침 메뉴");
		MenuComponent dinerMenu = new Menu("객체마을 식당 메뉴", "점심 메뉴");
		MenuComponent cafeMenu = new Menu("카페 메뉴", "저녁 메뉴");
		MenuComponent dessertMenu = new Menu("디저트 메뉴", "디저트를 즐겨 보에쇼");
		
		MenuComponent allMenu = new Menu("전체 메뉴", "전체 메뉴");
		
		allMenu.add(phMenu);
		allMenu.add(dinerMenu);
		allMenu.add(cafeMenu);
		
		phMenu.add(
				new MenuItem("K&B 팬케이크 아침정식"
				,"팬케이크에 스크램블에그와 토스트", true, 2.99));
		phMenu.add(
				new MenuItem("레귤러 팬케이크 아침정식"
				,"팬케이크에 계란 후라이와 소세지", false, 2.99));
		phMenu.add(
				new MenuItem("블루베리 팬케이크"
				,"팬케이크와 블루베리, 블루베리 시럽", true, 3.49));
		phMenu.add(
				new MenuItem("와플"
				,"와플과 블루베리 또는 딸기를 드립니다.", true, 3.59));
		
		dinerMenu.add(
				new MenuItem("채식주의자용 BLT"
				, "통밀 위에 콩고기 베이컨, 상추, 토마토를 얹은 메뉴", true, 2.99));
		
		dinerMenu.add(
				new MenuItem("BLT"
				, "통밀 위에 베이컨, 상추, 토마토를 얹은 메뉴", false, 2.99));
		dinerMenu.add(
				new MenuItem("오늘의 스프"
				, "감자 샐러드를 곁들인 오늘의 스프", true, 2.99));
		dinerMenu.add(
				new MenuItem("핫도그"
				, "사워크라우트, 갖은 양념, 양파, 치즈가 곁들여진 핫도그", true, 2.99));
		dinerMenu.add(
				new MenuItem("파스타"
				, "마리나라 소스 스파게티, 효모빵도 드립니다.", true, 3.89));
		dinerMenu.add(dessertMenu);
		
		
		dessertMenu.add(
				new MenuItem("애플 파이"
				, "바삭바삭한 크러스트에 바닐라 아이스크림이 얹혀 있는 애플 파이", true, 1.59));
		dessertMenu.add(
				new MenuItem("치즈케이크"
				, "초콜릿 그레이엄 크러스트 위에 부드러운 뉴욕 치즈케이크", true, 1.99));
		dessertMenu.add(
				new MenuItem("소르베"
				, "라스베리와 라임의 절묘한 조화", true, 1.89));
		
		cafeMenu.add(
				new MenuItem("베지 버거와 에어 프라이"
				, "통밀빵, 상추, 토마토, 감자 튀김이 첨가된 베지 버거", true, 1.59));
		cafeMenu.add(
				new MenuItem("오늘의 스프"
				, "통밀빵, 상추, 토마토, 감자 튀김이 첨가된 베지 버거", false, 0.69));
		cafeMenu.add(
				new MenuItem("투리토"
				, "통 핀토콩과 살사, 구아카몰이 곁들여진 푸짐한 부리토", true, 0.89));
		
		Waitress waitress = new Waitress(allMenu);
		waitress.printMenu();
	}
}
