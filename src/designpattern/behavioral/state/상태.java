package designpattern.behavioral.state;
/**
상태 패턴은 "상태에 따라 클래스 동작이 변경된다"고 말합니다. 
상태 패턴에서는 다양한 상태를 나타내는 개체와 상태 개체가 변경됨에 따라 동작이 달라지는 컨텍스트 개체를 만듭니다.
 
상태 패턴의 주요 아이디어는 개체가 클래스를 변경하지 않고 동작을 변경할 수 있도록 허용하는 것입니다. 
또한 이를 구현하면 if/else 문이 많이 없어도 코드가 더 깔끔해집니다.
우체국으로 보내지는 소포가 있다고 상상해보십시오. 
소포 자체를 주문한 다음 우체국으로 배달하고 최종적으로 고객에게 받을 수 있습니다. 
이제 실제 상태에 따라 배송 상태를 인쇄하려고 합니다.
가장 간단한 접근 방식은 일부 부울 플래그를 추가하고 클래스의 각 메서드 내에 간단한 if/else 문을 적용하는 것입니다. 
간단한 시나리오에서는 그다지 복잡하지 않습니다. 
그러나 처리할 상태가 더 많아지면 코드가 복잡해지고 오염되어 더 많은 if/else 문이 생성될 수 있습니다.
게다가 각 상태에 대한 모든 논리는 모든 메서드에 분산됩니다. 
이제 여기에서 State 패턴을 사용하는 것으로 간주할 수 있습니다. 
상태 디자인 패턴 덕분에 전용 클래스에 논리를 캡슐화하고, 
단일 책임 원칙과 개방/폐쇄 원칙을 적용하고, 
코드를 더 깔끔하고 유지 관리하기 쉽게 만들 수 있습니다.

UML 다이어그램에서 컨텍스트 클래스에는 프로그램 실행 중에 변경될 연결된 상태가 있음을 알 수 있습니다.
우리의 컨텍스트는 동작을 상태 구현에 위임할 것입니다. 즉, 들어오는 모든 요청은 상태의 구체적인 구현에 의해 처리됩니다.
로직이 분리되어 있고 새 상태를 추가하는 것이 간단하다는 것을 알 수 있습니다. 필요한 경우 다른 상태 구현을 추가하는 것으로 귀결됩니다.
 */
public class 상태 {
	
	static class Package{
		private PackageState state = new OrderedState();
		public PackageState getState() {
			return state;
		}
		public void setState(PackageState state) {
			this.state = state;
		}
		public void previousState() {
			state.prev(this);
		}
		public void nextState() {
			state.next(this);
		}
		public void printStatus() {
			state.printStatus();
		}
		
	}
	static interface PackageState{
		void next(Package pkg);
		void prev(Package pkg);
		void printStatus();
	}
	
	static class OrderedState implements PackageState{
		public void next(Package pkg) {
			pkg.setState(new DeliveredState());
		}
		public void prev(Package pkg) {
			System.out.println("주문 시작 상태입니다.");
		}
		public void printStatus() {
			System.out.println("주문한 패키지가 아직 사무실로 배송되지 않았습니다.");
		}
		
	}
	static class DeliveredState implements PackageState{
		public void next(Package pkg) {
			pkg.setState(new ReceivedState());
		}
		public void prev(Package pkg) {
			pkg.setState(new OrderedState());
		}
		public void printStatus() {
			System.out.println("소포가 우체국으로 배달되었지만 아직 받지 못했습니다.");
		}
		
	}
	static class ReceivedState implements PackageState{
		public void next(Package pkg) {
			System.out.println("종료 상태입니다.");
		}
		public void prev(Package pkg) {
			pkg.setState(new DeliveredState());
		}
		public void printStatus() {
			System.out.println("택배가 이미 고객에게 배송완료됐습니다.");
		}
	}
	
	public static void main(String[] args) {
		Package pkg = new Package();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();
        
        pkg.previousState();
        pkg.printStatus();
        
        pkg.previousState();
        pkg.printStatus();
        
        pkg.previousState();
        pkg.printStatus();
        
	}
/**
State vs. Strategy
두 가지 디자인 패턴은 매우 유사하지만 UML 다이어그램은 동일하며 그 뒤에 있는 아이디어는 약간 다릅니다.

첫째, 전략 패턴은 상호 교환 가능한 알고리즘 계열을 정의합니다. 
일반적으로 이들은 동일한 목표를 달성하지만 예를 들어 정렬 또는 렌더링 알고리즘과 같이 다른 구현을 사용합니다.

상태 패턴에서 동작은 실제 상태에 따라 완전히 변경될 수 있습니다.

다음으로, 전략에서 클라이언트는 가능한 전략을 사용하고 명시적으로 변경할 수 있음을 알고 있어야 합니다. 
반면 상태 패턴에서는 각 상태가 다른 상태와 연결되어 유한 상태 머신과 같은 흐름을 생성합니다.

 */
	
	
	
}
