package javabasic.toby;

import java.util.Arrays;
import java.util.List;

import javabasic.toby.Dispatch01.Service2;

public class Dispatch01 {

	public static void main(String[] args) {
		//런타임이 아닌 컴파일 시점에 이미 무엇을 호출할지 결정됐다.(바이트 코드에 정보 있음) == 스테틱 디스패치
		new Service().run();
		new Service().run(1);
		new Service().run("static");
		System.out.println();
		//다이나믹 디스패칭
		Service2 ser = new MyService();
		
		/*다 제거 되어있고, 딱 이부분만 있다고 가정*/
		/* 타입이 추상화된 타입이고, 가보면 abstract메서드로 구현부가 없음 
		 * 컴파일 시점에서는 뭘 호출하는지 알 수 없다. 
		 * 런타임 시점에서 ser 어떤 오브젝트가 할당되어있는지 확인하고 실행한다. (다이다믹 디스패칭)
		 * 메서드를 호출하는 부분에 보이진 않지만 receiver parameter가 존재한다. 
		 * receiver parameter에는 오브젝트 마다 존재하는 this가 할당되어 있다 .
		 * 이 정보로 어떤 클래스가 호출했는지 런타임 시점에 알 수 있다. */
		ser.run();
		/*다 제거 되어있고, 딱 이부분만 있다고 가정*/
		ser = new MyService2();
		ser.run();
		System.out.println();
		
		/* Method Signature
		 * name, parameter list, parameter type, (no return type)
		 * 오버로딩에 조건 (중복 정의)
		 * 
		 * Method Type
		 * return type, method type, parameter method argument types, exception
		 * 메서드 레퍼런스 조건 (일치)
		 * 
		 */
		List<Service2> list = Arrays.asList(new MyService(), new MyService2());
		list.forEach(Service2::run); //forEach()메서드(컨슈머)와 run()메서드 ,method type 일치
		
		
	}
	static class OverLoadText{
		void over() {}
		//Parameter type
		void over(String val) {}
		//Parameter list
		void over(String val, Integer val2) {}
		//No return Type
//		String over(String val) {return val;}
		//No parameter name
//		void over(String test, Integer test) {}
		
		//파라미터화된 타입도 마찬가지
		void over(Value val) {}
//		Value over(Value<?> val) {return val;}
//		void over(Value<Integer> val) {}
	}
	static class Value<T>{
		T value;
		public Value(T value) {
			super();
			this.value = value;
		}
	}
	static class OverRideTest{
		void over(String val) {}
	}
	static class OverRideImpl extends OverRideTest{
//		String over(String val) {
//			return "";
//		}
	}
	
	
	
	
	/**
	 * 의존관계
	 * 클라이언트가 대상 참조를 가지고 사용하는 것
	 * 클라이언트가 기준
	 * 
	 * 컴포넌트란 이를 만든 개발자의 손이 미치지 않는 곳에서도, 아무 변경 없이, 필요에 따라 확장해서 사용될 수 있는 소프트웨어 덩이
	 * 
	 * 클래스/오브객트 분류 디자인 패턴에서 대부분은 객체 오브객트 패턴에 속한다.
	 * 오브젝트 패턴은 런타임시에 바뀔 수 있는 상속 관계보다 더 동적인 오브젝트 의존 관계를 다룬다.(DI와도 연결되어 있다)
	 * 
	 * 스프링에서 Dependency
	 * 컴파일 타임이 아니라 런타임시에 결정/구성되는 오브젝트 의존 관계
	 * 1. 구현 대신 인터페이스 사용
	 * 	클래스(구현) 의존관계 제거
	 * 	클래스에 대한 의존성은 생성 패턴처럼 3자에게 위임
	 * 
	 * 2. 오브젝트 합성(composition)사용
	 * 	재사용성을 확보하기 위한 방법의 한 가지 (상속 대안)
	 * 	인터페이스 사용이 전제(블랙 박스 재사용)
	 * 	새롭고 복잡한 기능을 얻기 위해서 오브젝트를 조합/합성
	 * 	런타임시에 다른 오브젝트에 대한 레퍼런스를 획득
	 * 	각 클래스가 캡슐화되고 자신의 역할에 충실하게 도와줌
	 */
	
	
	/*
	 * 스테틱 디스패치
	 */
	static class Service{
		void run() {
			System.out.println("static dispatch");
		}
		void run(Integer call) {
			System.out.println("static dispatch "+call);
		}
		void run(String call) {
			System.out.println("static dispatch "+call);
		}
	}
	/*
	 * 다이나믹 디스패칭
	 */
	abstract static class Service2{
		abstract void run();
	}
	static class MyService extends Service2{
		void run() {
			System.out.println(this.getClass().getSimpleName());
		}
	}
	static class MyService2 extends Service2{
		void run() {
			System.out.println(this.getClass().getSimpleName());
		}
	}
	
	
}
