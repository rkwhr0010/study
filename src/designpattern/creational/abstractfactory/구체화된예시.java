package designpattern.creational.abstractfactory;

/**
 * 추상 공장 패턴의 첫 번째 방안은 각 제품 패밀리​(제품군)​에 해당하는 
 * 개별적인 인터페이스를 명시적으로 선언하는 것입니다.
 * 
 * # 구성요소
 * AbstractFacotry
 * 인터페이스는 각각의 추상 제품들을 생성하기 위한 
 * 여러 메서드들의 집합을 선언합니다.
 * 
 * ConcreteFacotry
 * 추상 팩토리의 생성 메서드들을 구현합니다. 각 구상 팩토리는 
 * 제품들의 특정 변형들에 해당하며 해당 특정 변형들만 생성합니다.
 * 
 * 구상 팩토리들은 구상 제품들을 인스턴스화하나, 
 * 그 제품들의 생성 메서드들의 시그니처들은 
 * 그에 해당하는 추상 제품들을 반환해야 합니다. 
 * 
 * AbstractProduct
 * 추상 제품들은 제품 패밀리를 구성하는 
 * 개별 연관 제품들의 집합에 대한 인터페이스들을 선언합니다.
 * 
 * ConcreteProduct
 * 구상 제품들은 변형들로 그룹화된 추상 제품들의 다양한 구현들입니다.
 * 
 * #키워드
 * 제품군
 * 
 */
public class 구체화된예시 {
	public static void main(String[] args) {
		new Client() {
			void run() {
				VehicleFactory vehicleFactory = new FastVehicleFactory();
				Ship ship = vehicleFactory.createShip();
				Truck truck = vehicleFactory.createTruck();
				Aircraft aircraft = vehicleFactory.createAircraft();
				
				ship.transport();
				truck.transport();
				aircraft.transport();
				
				vehicleFactory = new SlowVehicleFactory();
				ship = vehicleFactory.createShip();
				truck = vehicleFactory.createTruck();
				aircraft = vehicleFactory.createAircraft();
				
				ship.transport();
				truck.transport();
				aircraft.transport();
			}
		}.run();
 	}
}
class Client{
	void run() {}
}


//Creator
abstract class VehicleFactory{
	public abstract Ship createShip();
	public abstract Truck createTruck();
	public abstract Aircraft createAircraft();
	
	
	
}
class FastVehicleFactory extends VehicleFactory{
	public Ship createShip() {
		return new FastShip();
	}
	public Truck createTruck() {
		return new FastTruck();
	}
	public Aircraft createAircraft() {
		return new FastAircraft();
	}
}
class SlowVehicleFactory extends VehicleFactory{
	public Ship createShip() {
		return new SlowShip();
	}
	public Truck createTruck() {
		return new SlowTruck();
	}
	public Aircraft createAircraft() {
		return new SlowAircraft();
	}
}

//Product
interface Ship {
	public void transport();
}
interface Truck {
	public void transport();
}
interface Aircraft {
	public void transport();
}

class FastShip implements Ship{
	public void transport() {
		System.out.println("빠른 배, 적은 화물량");
	}
}
class FastTruck implements Truck{
	public void transport() {
		System.out.println("빠른 트럭, 적은 화물량");
	} 
}
class FastAircraft implements Aircraft{
	public void transport() {
		System.out.println("빠른 항공기, 적은 화물량");
	} 
}
class SlowShip implements Ship{
	public void transport() {
		System.out.println("느린 배, 많은 화물량");
	}
}
class SlowTruck implements Truck{
	public void transport() {
		System.out.println("느린 트럭, 많은 화물량");
	} 
}
class SlowAircraft implements Aircraft{
	public void transport() {
		System.out.println("느린 항공기, 많은 화물량");
	} 
}



