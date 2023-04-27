package designpattern.structural.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrototypeEx {

	static abstract class Shape implements Cloneable {
		public int x;
		public int y;
		public String color;

		public Shape() {}

		protected Shape(Shape target) {
			if (target != null) {
				this.x = target.x;
				this.y = target.y;
				this.color = target.color;
			}
		}

		public abstract Shape clone();

		@Override
		public boolean equals(Object object2) {
			if (!(object2 instanceof Shape))
				return false;
			Shape shape2 = (Shape) object2;
			return shape2.x == x && shape2.y == y && Objects.equals(shape2.color, color);
		}
	}

	static class Circle extends Shape {
		public int radius;

		public Circle() {
		}

		public Circle(Circle target) {
			super(target);
			if (target != null) {
				this.radius = target.radius;
			}
		}

		// 공변 반환타입
		@Override
		public Circle clone() {
			return new Circle(this);
		}

		@Override
		public boolean equals(Object object2) {
			if (!(object2 instanceof Circle) || !super.equals(object2))
				return false;
			Circle shape2 = (Circle) object2;
			return shape2.radius == radius;
		}
	}

	static class Rectangle extends Shape {
		public int width;
		public int height;

		public Rectangle() {
		}

		public Rectangle(Rectangle target) {
			super(target);
			if (target != null) {
				this.width = target.width;
				this.height = target.height;
			}
		}

		// 공변 반환타입
		@Override
		public Rectangle clone() {
			return new Rectangle(this);
		}

		@Override
		public boolean equals(Object object2) {
			if (!(object2 instanceof Rectangle) || !super.equals(object2))
				return false;
			Rectangle shape2 = (Rectangle) object2;
			return shape2.width == width && shape2.height == height;
		}
	}

	public static void main(String[] args) {
		List<Shape> shapes = new ArrayList<>();
		List<Shape> shapesCopy = new ArrayList<>();

		Circle circle = new Circle();
		circle.x = 10;
		circle.y = 20;
		circle.radius = 15;
		circle.color = "red";

		Circle circle2 = circle.clone();
		circle2.x = 11;

		// 1
		shapes.add(circle);
		shapesCopy.add(circle);
		// 2
		shapes.add(circle);
		shapesCopy.add(circle2);

		Rectangle rectangle = new Rectangle();
		rectangle.width = 10;
		rectangle.height = 20;
		rectangle.color = "blue";

		Rectangle rectangle2 = rectangle.clone();
		// 3
		shapes.add(rectangle);
		shapesCopy.add(rectangle2);

		cloneAndCompare(shapes, shapesCopy);
	}

	private static void cloneAndCompare(List<Shape> shapes, List<Shape> shapesCopy) {

		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i) != shapesCopy.get(i)) {
//            	System.out.println(System.identityHashCode(shapes.get(i))+" "+System.identityHashCode(shapesCopy.get(i)));
				System.out.print(i + ": 도형이 서로 다른 객체입니다.");
				if (shapes.get(i).equals(shapesCopy.get(i))) {
					System.out.println(i + ": 그리고 같은 값을 가집니다.");
				} else {
					System.out.println(i + ": 그리고 다른 값입니다.");
				}
			} else {
				System.out.println(i + ": 도형이 서로 같은 객체입니다.");
			}
		}
	}
}
