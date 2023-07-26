package javabasic.solid;

import java.util.List;

/*
 * 클래스 A가 클래스 B의 하위 유형인 경우 프로그램의 동작을 방해하지 않고 B를 A로 대체할 수 있어야 합니다.
 * 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다.
 * 
 * 일반적으로 List<?> list = new ArrayList<>();
 * 이렇게 선언은 기반타입(상위)으로 객체 생성은 하위타입으로 하는 것이 좋은 예시
 * 생성 시점에서 구체 클래스를 노출시키기 꺼려질 경우 
 * 생성 부분을 Abstract Factory 등의 패턴을 사용하여 유연성 높이기도 한다.
 * 
 * 결과적으로 LSP원리도 역시 서브 클래스가 확장에 대한 인터페이스를 준수해야 함을 의미합니다.
 * 다형성은 객체지향에 가장 중요한 요소로 가능한 상위 타입으로 다뤄야 한다.
 * 
 * 
 * 
 */
public class LiskovSubstitution {
	/*
	 * 사례 1
	 * 기존엔 문제가 없었지만, 시간이 지나
	 * 새로운 클래스를 만들기 위해 기반 클래스로 확장을 하는데 
	 * 인터페이스 명세와 맞지 않는 기능인 경우
	 */
	interface Car {
	    void turnOnEngine();
	    void accelerate();
	}
	interface Engine{
		void on();
		void powerOn(int power);
	}
	
	class EngineCar implements Car{
		Engine engine;

		public EngineCar(Engine engine) {
			this.engine = engine;
		}
		public void turnOnEngine() {
			engine.on();
		}
		public void accelerate() {
			engine.powerOn(1000);
		}
	}
	//전기차는 엔진이 없다. 모터가 있다.
	class ElectricCar implements Car{
		public void turnOnEngine() {
			throw new AssertionError("I don't have an engine!");
		}
		public void accelerate() {
//			engine.powerOn(1000);
		}
	}
	
	
	class MyRepository {
	}
	
	
	
}
