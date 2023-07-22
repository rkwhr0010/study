package designpattern.creational.factorymethod;

import java.util.NoSuchElementException;

import designpattern.creational.factorymethod.VehicleFactory.VehicleType;

/**
 * 팩토리 메서드 패턴은 (new 연산자를 사용한) 
 * 객체 생성 직접 호출들을 특별한 팩토리 메서드에 
 * 대한 호출들로 대체하라고 제안합니다. 
 * 걱정하지 마세요: 객체들은 여전히 new 연산자를 통해 생성되지만 
 * 팩토리 메서드 내에서 호출되고 있습니다.
 * 
 * # 구성요소
 * Creater
 * 크리에이터​(Creator) 클래스는 새로운 제품 객체들을 
 * 반환하는 팩토리 메서드를 선언합니다. 
 * 중요한 점은 이 팩토리 메서드의 반환 유형이 
 * 제품 인터페이스와 일치해야 한다는 것입니다.
 * 
 */
public class 구체화된예시 {
	public static void main(String[] args) {
		VehicleFactory vehicleFactory = new VehicleFactory();
		Vehicle ship = vehicleFactory.createVehicle(VehicleType.SHIP);
		Vehicle truck = vehicleFactory.createVehicle(VehicleType.TRUCK);
		Vehicle aircraft = vehicleFactory.createVehicle(VehicleType.AIRCRAFT);
		
		ship.transport();
		truck.transport();
		aircraft.transport();
		
		
	}
}


//Creator
class VehicleFactory{
	enum VehicleType{
		SHIP, TRUCK, AIRCRAFT
	}
	
	public Vehicle createVehicle(VehicleType type) {
		Vehicle vehicle = null;
		switch (type) {
		case SHIP:
			vehicle = new Ship();
			break;
		case TRUCK:
			vehicle = new Truck();
			break;
		case AIRCRAFT:
			vehicle = new Aircraft();
			break;
		default : 
			throw new NoSuchElementException();
		}
		
		return vehicle;
	}
}

//Product
interface Vehicle{
	void transport();
}
class Ship implements Vehicle{
	public void transport() {
		System.out.println("배로 운송");
	}
}
class Truck implements Vehicle{
	public void transport() {
		System.out.println("트럭으로 운송");
	}
}
class Aircraft implements Vehicle{
	public void transport() {
		System.out.println("항공기로 운송");
	}
}