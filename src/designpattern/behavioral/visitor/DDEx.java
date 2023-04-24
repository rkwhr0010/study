package designpattern.behavioral.visitor;

public class DDEx {
	interface Animal{
		void accept(Visitor visitor);
	}
	
	class Dog implements Animal{
		@Override
		public void accept(Visitor visitor) {
			visitor.visitDog(this);
		}
		@Override
		public String toString() {
			return "Dog";
		}
	}
	
	interface Visitor{
		void visitDog(Dog dog);
	}
	class VisitorImpl implements Visitor{

		@Override
		public void visitDog(Dog dog) {
			System.out.println(dog);
		}
		
	}
	
}
