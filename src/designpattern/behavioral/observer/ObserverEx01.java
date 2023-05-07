package designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverEx01 {
	static interface Subject<T>{
		//옵저버 등록
		void addObserver(Observer<T> observer);
		//옵저서 삭제
		void removeObserver(Observer<T> observer);
		//상태 변화 시 알림
		void notityObservers();
		
		T getData();
	}
	
	//핵심 관찰 대상1
	static class News<T> implements Subject<T>{
		//이 주제를 관찰할 옵저버 목록
		List<Observer<T>> observers = new ArrayList<>();
		//옵저버들의 핵심 관찰 데이터
		T news;
		
		public T getNews() {
			return this.news;
		}
		public void setNews(T news) {
			this.news = news;
			notityObservers();
		}
		public void addObserver(Observer<T> observer) {
			observers.add(observer);
		}
		public void removeObserver(Observer<T> observer) {
			observers.add(observer);
		}
		public void notityObservers() {
			for(Observer<T> observer : observers)
				observer.update(this);
		}
		public T getData() {
			return news;
		}
		public String toString() {
			return "News 구독자 : "+observers.toString();
		}
	}
	//핵심 관찰 대상1
	static class Magazine<T> implements Subject<T>{
		//이 주제를 관찰할 옵저버 목록
		List<Observer<T>> observers = new ArrayList<>();
		//옵저버들의 핵심 관찰 데이터
		T magazine;
		
		public T getMagazine() {
			return this.magazine;
		}
		public void setMagazine(T magazine) {
			this.magazine = magazine;
			notityObservers();
		}
		public void addObserver(Observer<T> observer) {
			observers.add(observer);
		}
		public void removeObserver(Observer<T> observer) {
			observers.add(observer);
		}
		public void notityObservers() {
			for(Observer<T> observer : observers)
				observer.update(this);
		}
		public T getData() {
			return magazine;
		}
		public String toString() {
			return "Magazine 구독자 : "+observers.toString();
		}
	}
	
	static interface Observer<T>{
		//업데이터 받는 방식, Pull방식
		void update(Subject<T> subject);
	}
	static class Person<T> implements Observer<T>{
		String name;
		//갱신 받은 데이터 목록
		List<T> updateData = new ArrayList<>();
		
		public Person(String name) {
			super();
			this.name = name;
		}
		public void update(Subject<T> subject) {
			updateData.add(subject.getData());
		}
		@Override
		public String toString() {
			return name+" "+updateData.toString();
		}
	}
	//테스트
	public static void main(String[] args) {
		Observer<String> person1 = new Person<>("홍길동");
		Observer<String> person2 = new Person<>("임꺽정");
		Observer<String> person3 = new Person<>("김자바");
		
		//대한잡지
		Magazine<String> magazine = new Magazine<>();
		//민국뉴스
		News<String> news = new News<>();
		
		magazine.addObserver(person1);
		magazine.addObserver(person2);
		
		news.addObserver(person2);
		news.addObserver(person3);
		
		System.out.println(magazine);
		System.out.println(news);
		
		magazine.setMagazine("1월 호 잡지 출판");
		news.setNews("속보... 주저리주저리");
		
		magazine.setMagazine("2월 호 잡지 출판");
		news.setNews("오늘 날씨 정보");
		
		System.out.println(person1);
		System.out.println(person2);
		System.out.println(person3);
		
	}
}
