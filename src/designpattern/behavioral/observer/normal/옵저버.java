package designpattern.behavioral.observer.normal;

import java.util.ArrayList;
import java.util.List;

public class 옵저버 {
	public static void main(String[] args) {
		Observable observable = new Observable();
		observable.addObserver(new ObserverA());
		observable.addObserver(new ObserverB());
		
		observable.setData("핳핳");
		
	}
	
	static class Observable {
		private Object data;
		
		List<Observer<?>> observers = new ArrayList<>();
		
		public void removeObserver(Observer<?> observer) {
			this.observers.remove(observer);
		}
		public void addObserver(Observer<?> observer) {
			this.observers.add(observer);
		}
		public void setData(Object data) {
			this.data = data;
			for(Observer<?> obs : observers) {
				obs.update(this, this.data);
			}
		}
	}
	
	static interface Observer<T>{
		void update(Observable ob,Object data);
	}
	
	static class ObserverA implements Observer<String>{
		private String data;
		public void update(Observable ob, Object data) {
			setData(String.valueOf(data));
			System.out.println("관찰자A 데이터 !!! " + data);
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}
	static class ObserverB implements Observer<String>{
		private String data;
		public void update(Observable ob, Object data) {
			setData(String.valueOf(data));
			System.out.println("관찰자B 데이터 !!! " + data);
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}
	
	
	
}
