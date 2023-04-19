package designpattern.etc.combining;

import java.util.ArrayList;
import java.util.List;

public class Exam006Observer {
	
	static class MallardDuck implements Quackable{
		Observable observable;
		
		public MallardDuck() {
			observable = new Observable(this);
		}
		
		public void quack() {
			System.out.println("꽥꽥");
			notifyObservers();
		}

		public void registerObserver(Observer observer) {
			observable.registerObserver(observer);
		}
		public void notifyObservers() {
			observable.notifyObservers();
		}
		public void removeObserver(Observer observer) {
			observable.removeObserver(observer);
		}
		public String toString() {
			return "물오리";
		}
	}
	static class RedheadDuck implements Quackable{
		Observable observable;
		
		public RedheadDuck() {
			observable = new Observable(this);
		}
		public void quack() {
			System.out.println("꽥꽥");
			notifyObservers();
		}

		@Override
		public void registerObserver(Observer observer) {
			observable.registerObserver(observer);
		}
		public void notifyObservers() {
			observable.notifyObservers();
		}
		public void removeObserver(Observer observer) {
			observable.removeObserver(observer);
		}
		public String toString() {
			return "붉은오리";
		}
	}
	static class DuckCall implements Quackable{
		Observable observable;
		public DuckCall() {
			observable = new Observable(this);
		}
		public void quack() {
			System.out.println("꽉꽉");	
			notifyObservers();
		}

		public void registerObserver(Observer observer) {
			observable.registerObserver(observer);
		}
		public void notifyObservers() {
			observable.notifyObservers();
		}
		public void removeObserver(Observer observer) {
			observable.removeObserver(observer);
		}
		public String toString() {
			return "오리호출";
		}
	}
	static class RubberDuck implements Quackable{
		Observable observable;
		
		public RubberDuck() {
			observable = new Observable(this);
		}
		
		public void quack() {
			System.out.println("삑삑");
			notifyObservers();
		}

		public void registerObserver(Observer observer) {
			observable.registerObserver(observer);
		}
		public void notifyObservers() {
			observable.notifyObservers();
		}
		public void removeObserver(Observer observer) {
			observable.removeObserver(observer);
		}
		public String toString() {
			return "고무오리";
		}
	}
	
	//어댑터 패턴
	static class Goose{
		void honk() {
			System.out.println("끾끾");
		}
		public String toString() {
			return "거위";
		}
	}
	static class GooseAdapter implements Quackable{
		Goose goose;
		Observable observable;

		public GooseAdapter(Goose goose) {
			this.goose = goose;
			observable = new Observable(this);
		}
		public void quack() {
			goose.honk();
			notifyObservers();
		}
		public void registerObserver(Observer observer) {
			observable.registerObserver(observer);
		}
		public void notifyObservers() {
			observable.notifyObservers();
		}
		public void removeObserver(Observer observer) {
			observable.removeObserver(observer);
		}
		public String toString() {
			return "오리인척하는 거위";
		}
	}
	
	//데코레이터
	static class QuackCounter implements Quackable{
		Quackable duck;
		//이 클래스의 모든 인스턴스가 공유하는 변수
		static int count = 0;
		
		public QuackCounter(Quackable duck) {
			this.duck = duck;
		}

		public void quack() {
			++count;
			duck.quack();
		}
		
		static int getQuacks() {
			return count;
		}

		public void registerObserver(Observer observer) {
			duck.registerObserver(observer);
		}
		public void notifyObservers() {
			duck.notifyObservers();
		}
		public void removeObserver(Observer observer) {
			duck.removeObserver(observer);
		}
		@Override
		public String toString() {
			return duck.toString();
		}
	}
	
	//추상 팩토리
	abstract static class AbstractDuckFactory{
		public abstract Quackable createMallardDuck();
		public abstract Quackable createRedheadDuck();
		public abstract Quackable createDuckCall();
		public abstract Quackable createRubberDuck();
	}
	
	//팩토리 군
	static class DuckFactory extends AbstractDuckFactory{
		public Quackable createMallardDuck() {
			return new MallardDuck();
		}
		public Quackable createRedheadDuck() {
			return new RedheadDuck();
		}
		public Quackable createDuckCall() {
			return new DuckCall();
		}
		public Quackable createRubberDuck() {
			return new RubberDuck();
		}
	}
	//와...추상 팩토리 패턴을 이런 식으로 사용하네
	static class CountingDuckFactory extends AbstractDuckFactory{
		public Quackable createMallardDuck() {
			return new QuackCounter(new MallardDuck());
		}
		public Quackable createRedheadDuck() {
			return new QuackCounter(new RedheadDuck());
		}
		public Quackable createDuckCall() {
			return new QuackCounter(new DuckCall());
		}
		public Quackable createRubberDuck() {
			return new QuackCounter(new RubberDuck());
		}
	}
	
	//컴포지트 패턴
	static class Flock implements Quackable{
		private final List<Quackable> ducks = new ArrayList<>();
		
		public void add(Quackable quackable) {
			ducks.add(quackable);
		}
		@Override
		public void quack() {
			//반복자패턴
			for(Quackable quackable : ducks) {
				quackable.quack();
			}
		}
		@Override
		public void registerObserver(Observer observer) {
			for(Quackable duck : ducks) {
				duck.registerObserver(observer);
			}
		}
		public void notifyObservers() {	}
		public void removeObserver(Observer observer) {
			for(Quackable duck : ducks) {
				duck.registerObserver(observer);
			}
		}
		public String toString() {
			return "오리무리";
		}
	}
	
	//옵저버 패턴
	static interface QuackObservable{//주제
		void registerObserver(Observer observer);
		void removeObserver(Observer observer);
		void notifyObservers();
	}
	//인터페이스 + 옵저버 기능 추가
	static interface Quackable extends QuackObservable{
		void quack();
	}

	static class Observable implements QuackObservable{
		List<Observer> observers = new ArrayList<>();
		QuackObservable duck;
		
		public Observable(QuackObservable duck) {
			this.duck = duck;
		}
		public void registerObserver(Observer observer) {
			observers.add(observer);
		}
		public void removeObserver(Observer observer) {
			observers.remove(observer);
		}
		public void notifyObservers() {
			for(Observer observer : observers) {
				observer.update(duck);
			}
		}
		public String toString() {
			return observers.toString();
		}
	}
	static interface Observer{
		void update(QuackObservable duck);
	}
	static class Quackologist implements Observer{
		public void update(QuackObservable duck) {
			System.out.println("꽥꽥학자 : "+duck+"가 방금 소리냈다.") ;
		}
		public String toString() {
			return "꽥꽥학자";
		}
	}
	static class MrKim implements Observer{
		public void update(QuackObservable duck) {
			System.out.println("경수가 관찰중인 : " + duck);
		}
	}
	
	
	static class DuckSimulator{
		
		void simulate(AbstractDuckFactory duckFactory) {
			//인터페이스로 변수 다루기
			Quackable redheadDuck = duckFactory.createRedheadDuck();
			Quackable duckCall = duckFactory.createDuckCall();
			Quackable rubberDuck = duckFactory.createRubberDuck();
			Quackable gooseAdapter = new GooseAdapter(new Goose());
			
			
			Flock flockOfDucks = new Flock();
			//다양한 오리
			flockOfDucks.add(redheadDuck);
			flockOfDucks.add(duckCall);
			flockOfDucks.add(rubberDuck);
			flockOfDucks.add(gooseAdapter);
			
			Flock flockOfMallards = new Flock();
			
			//물오리
			flockOfMallards.add(duckFactory.createMallardDuck());
			flockOfMallards.add(duckFactory.createMallardDuck());
			flockOfMallards.add(duckFactory.createMallardDuck());
			flockOfMallards.add(duckFactory.createMallardDuck());

			//오리군에 물오리군을 추가
			flockOfDucks.add(flockOfMallards);
			
			System.out.println("\n오리 시뮬레이션 게임(옵저버)");
			Quackologist quackologist = new Quackologist();
			MrKim mrKim = new MrKim();
			flockOfDucks.registerObserver(quackologist);
			flockOfDucks.registerObserver(mrKim);
			
			simulate(flockOfDucks);
			
			System.out.println("\n오리가 소리 낸 횟수 : " + QuackCounter.getQuacks());
			
		}
		
		//인터페이스로 메서드 인자로 다루기
		private void simulate(Quackable duck) {
			duck.quack();
		}
	}
	
	public static void main(String[] args) {
		DuckSimulator duckSimulator = new DuckSimulator();
		//데코레이터 객체를 생산하는 추상팩토리
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		duckSimulator.simulate(duckFactory);
	}
}
