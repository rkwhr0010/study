package designpattern.headfirst.chapter4;

public class AbstractFactoryEx {
	
	static interface AnimalFactory{
		Animal createCat();
		Animal createDog();
	}
	
	static class NormalAnimalFactory implements AnimalFactory{
		public Animal createCat() {
			return new Cat();
		}
		public Animal createDog() {
			return new Dog();
		}
	}
	static class BigAnimalFactory implements AnimalFactory{
		public Animal createCat() {
			return new BigCat(new Cat());
		}
		public Animal createDog() {
			return new BigDog(new Dog());
		}
	}
	
	static interface Animal{
		void sound();
	}
	
	static class Cat implements Animal{
		public void sound() {
			System.out.println("야옹");
		}
	}
	static class Dog implements Animal{
		public void sound() {
			System.out.println("멍멍");
		}
	}

	static class BigCat implements Animal{
		Animal cat;
		
		public BigCat(Animal cat) {
			this.cat = cat;
		}
		public void sound() {
			System.out.print("냐아아아앙! ");
			cat.sound();
		}
	}
	static class BigDog implements Animal{
		Animal dog;
		
		public BigDog(Animal dog) {
			this.dog = dog;
		}
		public void sound() {
			System.out.print("그르르를! ");
			dog.sound();
		}
	}
	public static void main(String[] args) {
		AnimalFactory normalAnimalFactory = new NormalAnimalFactory();
		AnimalFactory animalFactory = new BigAnimalFactory();
		
		Animal cat = normalAnimalFactory.createCat();
		Animal dog = normalAnimalFactory.createDog();
		cat.sound();
		dog.sound();
		cat = animalFactory.createCat();
		dog = animalFactory.createDog();
		cat.sound();
		dog.sound();
		
		
	}
	
}
