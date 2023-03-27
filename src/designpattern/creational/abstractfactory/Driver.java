package designpattern.creational.abstractfactory;

import designpattern.creational.abstractfactory.Driver.FactoryProvider.Type;

/**
 * <pre>
 * <b>추상 팩토리</b>
 * 구체적인 클래스를 지정하지 않고 관련 또는 종속 개체의 패밀리를 만들기 위한 인터페이스를 제공합니다.
 * 추상 팩토리 패턴은 구체적인 클래스를 지정하지 않고 공통 주제를 가진 개별 팩토리 그룹을 캡슐화하는 방법을 제공합니다.
 * 
 * <b>팩토리</b>
 * 구현 논리를 숨기고 클라이언트 코드가 새 개체를 초기화하는 대신 사용에 집중할 수 있도록 팩터리라는 클래스에 캡슐화된 정적 메서드를 제공합니다.
 * 즉, 사용자가 new 연산자 사용을 하지 않는다. new 연산자 사용은 특정 구체클래스에 의존하게 되므로, 변경에 취약하다.
 * IOC 컨테이너를 사용하지 않으면 결국, 코드 상에 new 연산자는 들어갈 수 밖에 없다.
 * 이를 한 곳에 집중시킨다.
 * </pre>
 *
 */
public class Driver {
	
	static interface Animal{
		String getType();
		void makeSound();
	}
	
	static class Dog implements Animal{
		public String getType() {
			return "Dog";
		}
		public void makeSound() {
			System.out.println("멍멍!!");
		}
	}
	
	static class Cat implements Animal{
		public String getType() {
			return "Cat";
		}
		public void makeSound() {
			System.out.println("니아옹!!");
		}
	}
	
	
	static interface Color {
		String getColor();
	}
	
	static class Red implements Color{
		public String getColor() {
			return "빨강";
		}
	}
	static class Melange implements Color{
		public String getColor() {
			return "멜란지";
		}
	}
	
	
	/*방법 1 메서드를 하나로 사용한다. 단, 사용자입장에서 형변환이라는 부담이 간다.
	 * 대신 있던 것을 제거할 때는 기존 코드 수정이 없다.*/
	static interface AbstractFactory{
		/*변동 가능성이 가장 높은 부분, 따라서 추상화*/
		<T> T create(String type);
	}
	
	static class AnimalFactory implements AbstractFactory{
		@SuppressWarnings("unchecked")
		@Override
		public Animal create(String type) {
			Animal animal = null;
			if("Dog".equalsIgnoreCase(type)) {
				animal = new Dog();
			}else if("Cat".equalsIgnoreCase(type)) {
				animal = new Cat();
			}
			return animal;
		}
	}
	static class ColorFactory implements AbstractFactory{
		@SuppressWarnings("unchecked")
		@Override
		public Color create(String type) {
			if("Red".equalsIgnoreCase(type)) {
				return new Red();
			}else if("Melange".equalsIgnoreCase(type)) {
				return new Melange();
			}
			throw new IllegalArgumentException();
		}
	}
	
	static class FactoryProvider {
		public enum Type{
			ANIMAL, COLOR;
		}
		
		private FactoryProvider() {	}
		public static AbstractFactory getFactory(Type type) {
			if(Type.ANIMAL == type) {
				return new AnimalFactory();
			}else if(Type.COLOR == type){
				return new ColorFactory();
			}
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 예제가 좀... 클래스군을 추상화한게 아닌데... 팩토리가 서로 다르다;
	 */
	public static void main(String[] args) {
		AnimalFactory animalFactory = (AnimalFactory)FactoryProvider.getFactory(Type.ANIMAL);
		ColorFactory colorFactory = (ColorFactory)FactoryProvider.getFactory(Type.COLOR);
		
		Animal dog = animalFactory.create("Dog");
		dog.makeSound();
		Animal cat = animalFactory.create("Cat");
		cat.makeSound();
		
		Color red = colorFactory.create("Red");
		Color melange = colorFactory.create("Melange");
		
		assert "Dog".equals(dog.getType());
		assert "Cat".equals(cat.getType());
		assert "빨강".equals(red.getColor());
		assert "멜란지".equals(melange.getColor());
		
		System.out.println("통과");
		
		
	}
	
	
}
