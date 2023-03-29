package designpattern.headfirst.chapter2;

import java.util.ArrayList;
import java.util.List;

public class EmailService {
	static interface Customer{
		void news(Zmarket zmarket, String leaflet);
	}
	
	static class Zmarket{
		String leaflet;
		
		public String getLeaflet() {
			return leaflet;
		}
		public void setLeaflet(String leaflet) {
			this.leaflet = leaflet;
			update();
		}
		
		List<Customer> customers = new ArrayList<>();
		
		public void addCustomer(Customer customer) {
			customers.add(customer);
		}
		public void removeCustomer(Customer customer) {
			customers.remove(customer);
		}
		
		public void update() {
			for(Customer customer : customers) {
				customer.news(this, leaflet);
			}
		}
	}
	public static void main(String[] args) {
		Zmarket zmarket = new Zmarket();
		zmarket.addCustomer((market,leaflet)->System.out.println("구독자1"+leaflet));
		zmarket.addCustomer((market,leaflet)->System.out.println("구독자2"+leaflet));
		zmarket.addCustomer((market,leaflet)->System.out.println("구독자3"+leaflet));
		zmarket.addCustomer((market,leaflet)->System.out.println("구독자4"+leaflet));
		
		zmarket.setLeaflet("사과팝니다.");
		
	}
	
}
