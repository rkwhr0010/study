package designpattern.behavioral.templatemethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 단일 메서드에 논리를 캡슐화하여 복잡한 알고리즘을 더 쉽게 구현할 수 있습니다.
 * 템플릿 패턴은 "작업에서 함수의 골격을 정의하고 일부 단계를 하위 클래스로 연기합니다"라고 말합니다.
 * 작업에서 알고리즘의 골격을 정의하고 일부 단계를 하위 클래스로 연기합니다. 
 * Template Method를 사용하면 서브클래스가 알고리즘의 구조를 변경하지 않고 알고리즘의 특정 단계를 재정의할 수 있습니다.
 * </pre>
 *
 */
public class 템플릿메서드 {

	static class Computer {
		private Map<String, String> computerParts = new HashMap<>();

		public Computer(Map<String, String> computerParts) {
			this.computerParts = computerParts;
		}

		public Map<String, String> getComputerParts() {
			return computerParts;
		}
	}

	/**
	 * <pre>
	 * 골격 (추상)클래스
	 * </pre>
	 */
	static abstract class ComputerBuilder {
		protected Map<String, String> computerParts = new HashMap<>();
		protected List<String> motherboardSetupStatus = new ArrayList<>();

		// 재정의되는 것을 방지하기 위해 final 으로 선언되었음을 알 수 있습니다.
		public final Computer buildComputer() {
			//헐리우드 배우 원칙, 고수준 메서드만이 저수준 메서드를 호출(의존, 사용)한다. 저수준에서 고수준을 호출하면 안된다. 
			addMotherboard();
			setupMotherboard();
			addProcessor();
			hook(); // 추가 기능을 넣어 사용해도, 안해도 그만, 따라서 추상 메서드가 아님
			return getComputer();
		}

		protected void hook() {	}

		private Computer getComputer() {
			return new Computer(computerParts);
		}
		protected abstract void addMotherboard();
		protected abstract void setupMotherboard();
		protected abstract void addProcessor();

		protected List<String> getMotherboardSetupStatus() {
			return motherboardSetupStatus;
		}
		public Map<String, String> getComputerParts() {
			return computerParts;
		}
	}

	static class StandardComputerBuilder extends ComputerBuilder {
		protected void addProcessor() {
			computerParts.put("Processor", "Standard Processor");
		}
		protected void setupMotherboard() {
			motherboardSetupStatus.add("보급형 메인보드를 케이스에 고정");
			motherboardSetupStatus.add("전원 연결 ");
			motherboardSetupStatus.forEach(System.out::println);
		}
		protected void addMotherboard() {
			computerParts.put("Motherboard", "Standard Motherboard");
		}
	}

	static class HighEndComputerBuilder extends ComputerBuilder {
		protected void addMotherboard() {
			computerParts.put("Motherboard", "고성능 컴퓨터");
		}
		protected void setupMotherboard() {
			motherboardSetupStatus.add("고급형 메인보드를 케이스에 고정");
			motherboardSetupStatus.add("전원 연결 ");
			motherboardSetupStatus.forEach(step -> System.out.println(step));
		}
		protected void addProcessor() {
			computerParts.put("Processor", "High-end Processor");
		}
		@Override
		protected void hook() {
			computerParts.put("RGB", "RGB LED 설치");
		}
	}

	public static void main(String[] args) {
		new StandardComputerBuilder()
			.buildComputer()
			.getComputerParts()
			.forEach((k, v) -> System.out.println("Part : " + k + " Value : " + v));
		
		new HighEndComputerBuilder()
		  .buildComputer()
		  .getComputerParts()
		  .forEach((k, v) -> System.out.println("Part : " + k + " Value : " + v));
	}

}
