package designpattern.headfirst.chapter2.practise;

import java.util.ArrayList;
import java.util.List;

public class 옵저버패턴 {
	static interface Observer<E>{
		void update(Observable<E> ob, E data) ;
	}
	
	static class Obser1 implements Observer<String>{
		String data;
		@Override
		public void update(Observable<String> ob, String data) {
			this.data = data;
		}
	}
	
	static interface Observable<E>{
		
		void setData(E data);
		void add(Observer<?> observer);
		void remove(Observer<?> observer);
		
		void allNotify();
	}
	static class Agency implements Observable<String>{
		String data;
		List<Observer> observers = new ArrayList<>();
		
		@Override
		public void setData(String data) {
			this.data = data;
		}

		@Override
		public void allNotify() {
			for(Observer observer : observers) {
				observer.update(this, data);
			}
		}

		@Override
		public void add(Observer observer) {
			observers.add(observer);
		}
		@Override
		public void remove(Observer observer) {
			observers.remove(observer);
		}
		
	}
	
}
