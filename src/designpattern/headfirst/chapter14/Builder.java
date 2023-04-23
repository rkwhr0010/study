package designpattern.headfirst.chapter14;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Builder {
	
	/*
	 * 큻라이언트가 객체 구조를 짠다. 
	 * 따라서 팩토리패턴은 한개의 단계로 객체를 생성하는 반면
	 * 빌더는 여러 단계가 수반된다.
	 * 이에 따라 클라이언트가 객체 구조에 대해 자세히 알아야한다.
	 */
	static abstract class VacationBuilder {
		String name;
		List<Accommodation> accommodations = new ArrayList<Accommodation>();
		List<String> events = new ArrayList<String>();
		
		//자기자신을 리턴한다. 메소드 체이닝
		public abstract VacationBuilder addAccommodation();
		public abstract VacationBuilder addAccommodation(String name);
		public abstract VacationBuilder addAccommodation(String name
														, int year
														, int month
														, int day
														, int nights
														, int location);
		public abstract VacationBuilder addEvent(String event);
		
		//가장 마지막에 모든 설정을 마치고 호출될 메서드
		public Vacation getVacation() {
			Vacation vacation = new Vacation();
			vacation.setName(name);
			vacation.setAccommodations(accommodations);
			vacation.setEvents(events);
			return vacation;
		}
	}
	
	static class CityVacationBuilder extends VacationBuilder {	
		public CityVacationBuilder() {
			this.name = "City Vacation Builder";
		}
		public VacationBuilder addAccommodation() {
			this.accommodations.add(new Hotel());
			return this;
		}
		public VacationBuilder addAccommodation(String name) {
			this.accommodations.add(new Hotel(name));
			return this;
		}
		public VacationBuilder addAccommodation(String name
				, int year, int month, int day, int nights, int location) {
			Reservation reservation = new Reservation();
			reservation.setArrivalDate(year, month, day);
			reservation.setNights(nights);
			
			Hotel hotel = new Hotel(name);
			hotel.setReservation(reservation);
			hotel.setRoomNumber(location);
			this.accommodations.add(hotel);
			return this;
		}
		public VacationBuilder addEvent(String event) {
			this.events.add("See the " + event + " show");
			return this;
		}
	}
	
	static class OutdoorsVacationBuilder extends VacationBuilder {	
		public OutdoorsVacationBuilder() {
			this.name = "Outdoorsy Vacation Builder";
		}
		public VacationBuilder addAccommodation() {
			this.accommodations.add(new Tent());
			return this;
		}
		public VacationBuilder addAccommodation(String name) {
			this.accommodations.add(new Tent(name));
			return this;
		}
		public VacationBuilder addAccommodation(String name
				, int year, int month, int day, int nights, int location) {
			Reservation reservation = new Reservation();
			reservation.setArrivalDate(year, month, day);
			reservation.setNights(nights);
			
			Tent tent = new Tent(name);
			tent.setReservation(reservation);
			tent.setSiteNumber(location);
			this.accommodations.add(tent);
			return this;
		}
		public VacationBuilder addEvent(String event) {
			this.events.add("Hike: " + event);
			return this;
		}
	}
	
	static abstract class Accommodation {
		String name;
		Reservation reservation = null;
		
		public void setReservation(Reservation r) {
			this.reservation = r;
		}
		public Reservation getReservation() {
			return this.reservation;
		}
		public abstract String getLocation();
		public String toString() {
			StringBuffer display = new StringBuffer();
			display.append("You're staying at " + name);
			if (this.reservation != null) {
				display.append("\nYou have a reservation for arrival date: " + reservation.getArrivalDate() + 
						", staying for " + reservation.getNights() + " nights");
			}
			if (this.getLocation() != "") {
				display.append(" in " + this.getLocation());
			}
			display.append("\n");
			return display.toString();
		}
	}
	static class Hotel extends Accommodation {
		int roomNumber;
		public Hotel() {
			this.name = "Hotel";
		}
		public Hotel(String name) {
			this.name = name;
		}
		public void setRoomNumber(int n) {
			this.roomNumber = n;
		}
		public int getRoomNumber() {
			return this.roomNumber;
		}
		public String getLocation() {
			if (roomNumber == 0) return "";
			else return "Room number " + this.roomNumber;
		}
	}
	
	static class Tent extends Accommodation {
		int siteNumber;
		public Tent() {
			this.name = "Tent";
		}
		public Tent(String name) {
			this.name = name;
		}
		public void setSiteNumber(int n) {
			this.siteNumber = n;
		}
		public int getSiteNumber() {
			return this.siteNumber;
		}
		public String getLocation() {
			if (siteNumber == 0) return "";
			else return "Site number " + this.siteNumber;
		}
	}
	
	static class Vacation {
		String name;
		List<Accommodation> accommodations = new ArrayList<Accommodation>();
		List<String> events = new ArrayList<String>();
	 
		public void setName(String name) {
			this.name = name;
		}
		public void setAccommodations(List<Accommodation> accommodations) {
			this.accommodations = accommodations;
		}
		public void setEvents(List<String> events) {
			this.events = events;
		}
		public String toString() {
			StringBuffer display = new StringBuffer();
			display.append("---- " + this.name + " ----\n");
			for (Accommodation a : accommodations) {
				display.append(a);
			}
			for (String e : events) {
				display.append(e + "\n");
			}
			return display.toString();
		}
	}

	 
	
	static class Reservation {
		LocalDate arrivalDate;
		int nights;
		
		public void setArrivalDate(int year, int month, int day) {
			this.arrivalDate = LocalDate.of(year, month, day);
		}
		public LocalDate getArrivalDate() {
			return this.arrivalDate;
		}
		public void setNights(int nights) {
			this.nights = nights;
		}
		public int getNights() {
			return this.nights;
		}
	}
	
	public static void main(String[] args) {
		VacationBuilder outdoorsyVacationBuilder = new OutdoorsVacationBuilder();
		Vacation outdoorsyVacation = outdoorsyVacationBuilder
				.addAccommodation("Two person tent", 2020, 7, 1, 5, 34)
				.addEvent("Beach")
				.addAccommodation("Two person tent")
				.addEvent("Mountains")
				.getVacation();
		System.out.println(outdoorsyVacation);
		
		VacationBuilder cityVacationBuilder = new CityVacationBuilder();
		Vacation cityVacation = cityVacationBuilder
				.addAccommodation("Grand Facadian", 2020, 8, 1, 5, 0)
				.addAccommodation("Hotel Commander", 2020, 8, 6, 2, 0)
				.addEvent("Cirque du Soleil")
				.getVacation();
		System.out.println(cityVacation);
	}
	
	
}
