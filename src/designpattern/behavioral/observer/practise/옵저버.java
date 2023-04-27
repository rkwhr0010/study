package designpattern.behavioral.observer.practise;

import java.util.ArrayList;
import java.util.List;

public class 옵저버 {
	static interface Observer{
		void update(Observable ob, Object data) ;
	}
	static class Observable{
		List<Observer> observers = new ArrayList<>();
		Object data;
		
		public void setData(Object data) {
			this.data = data;
		}
		public void allNotify() {
			for(Observer observer : observers) {
				observer.update(this, data);
			}
		}
		void addObserver(Observer observer) {
			observers.add(observer);
		}
		void removeObserver(Observer observer) {
			observers.remove(observer);
		}
	
	}
	
	public static void main(String[] args) {
		
	}
}
