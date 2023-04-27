package designpattern.etc.combining;

import java.util.ArrayList;
import java.util.List;

public class Exam005Composite {
	//인터페이스
	static interface Quackable{
		void quack();
	}
	static class MallardDuck implements Quackable{
		public void quack() {
			System.out.println("꽥꽥");
		}
	}
	static class RedheadDuck implements Quackable{
		public void quack() {
			System.out.println("꽥꽥");
		}
	}
	static class DuckCall implements Quackable{
		public void quack() {
			System.out.println("꽉꽉");			
		}
	}
	static class RubberDuck implements Quackable{
		public void quack() {
			System.out.println("삑삑");			
		}
	}
	
	//어댑터 패턴
	static class Goose{
		void honk() {
			System.out.println("끾끾");
		}
	}
	static class GooseAdapter implements Quackable{
		Goose goose;
		public GooseAdapter(Goose goose) {
			this.goose = goose;
		}
		public void quack() {
			goose.honk();
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
		private final List<Quackable> quackers = new ArrayList<>();
		
		public void add(Quackable quackable) {
			quackers.add(quackable);
		}
		@Override
		public void quack() {
			//반복자패턴
			for(Quackable quackable : quackers) {
				quackable.quack();
			}
		}
	}
	
	static class DuckSimulator{
		
		void simulate(AbstractDuckFactory duckFactory) {
			//인터페이스로 변수 다루기
			Quackable mallardDuck = duckFactory.createMallardDuck();
			Quackable redheadDuck = duckFactory.createRedheadDuck();
			Quackable duckCall = duckFactory.createDuckCall();
			Quackable rubberDuck = duckFactory.createRubberDuck();
			Quackable gooseAdapter = new GooseAdapter(new Goose());
			
			System.out.println("오리 시뮬레이션 게임: 무리 (컴포지트패턴)");
			
			Flock flockOfDucks = new Flock();
			//다양한 오리
			flockOfDucks.add(mallardDuck);
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
			
			System.out.println("\n오리 시뮬레이션 게임 : 전체 무리");
			simulate(flockOfDucks);
			System.out.println("\n오리 시뮬레이션 게임 : 물오리 무리");
			simulate(flockOfMallards);
			
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
