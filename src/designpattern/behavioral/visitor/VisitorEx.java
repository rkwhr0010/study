package designpattern.behavioral.visitor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VisitorEx {

	static interface Shape {
		void move(int x, int y);

		void draw();

		String accept(Visitor visitor);
	}

	static class Dot implements Shape {
		private int id;
		private int x;
		private int y;

		public Dot() {
		}

		public Dot(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		@Override
		public void move(int x, int y) {
			// move shape
		}

		@Override
		public void draw() {
			// draw shape
		}

		@Override
		public String accept(Visitor visitor) {
			return visitor.visitDot(this);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getId() {
			return id;
		}
	}

	static class Circle extends Dot {
		private int radius;

		public Circle(int id, int x, int y, int radius) {
			super(id, x, y);
			this.radius = radius;
		}

		@Override
		public String accept(Visitor visitor) {
			return visitor.visitCircle(this);
		}

		public int getRadius() {
			return radius;
		}
	}

	static class Rectangle implements Shape {
		private int id;
		private int x;
		private int y;
		private int width;
		private int height;

		public Rectangle(int id, int x, int y, int width, int height) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		@Override
		public String accept(Visitor visitor) {
			return visitor.visitRectangle(this);
		}

		@Override
		public void move(int x, int y) {
			// move shape
		}

		@Override
		public void draw() {
			// draw shape
		}

		public int getId() {
			return id;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
	}

	static class CompoundShape implements Shape {
		public int id;
		public List<Shape> children = new ArrayList<>();

		public CompoundShape(int id) {
			this.id = id;
		}

		@Override
		public void move(int x, int y) {
			// move shape
		}

		@Override
		public void draw() {
			// draw shape
		}

		public int getId() {
			return id;
		}

		@Override
		public String accept(Visitor visitor) {
			return visitor.visitCompoundGraphic(this);
		}

		public void add(Shape shape) {
			children.add(shape);
		}
	}

	static interface Visitor {
		String visitDot(Dot dot);

		String visitCircle(Circle circle);

		String visitRectangle(Rectangle rectangle);

		String visitCompoundGraphic(CompoundShape cg);
	}

	static class XMLExportVisitor implements Visitor {
		public String export(Shape... args) {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
			for (Shape shape : args) {
				sb.append(shape.accept(this)).append("\n");
			}
			return sb.toString();
		}
		
		public String visitDot(Dot d) {
			StringBuilder sb = new StringBuilder();
			sb.append("<Dot>\n");
			Field[] fields = d.getClass().getDeclaredFields();
			for(int i=0,tap =2;i<fields.length;i++) {
				for(int j=0;j<tap;j++) {
					sb.append("  ");
				}
				sb.append("<").append(fields[i].getName()).append(">") ;
				try {
					sb.append(fields[i].get(fields[i]));
				}  catch (IllegalAccessException e) {}
				sb.append("</").append(fields[i].getName()).append(">\n") ;
			}
			
			sb.append("</Dot>");
			
//			return sb.toString();
			return "<dot>" + "\n" 
					+ "    <id>" + d.getId() + "</id>" + "\n" 
					+ "    <x>" + d.getX() + "</x>" + "\n"
					+ "    <y>" + d.getY() + "</y>" + "\n" 
					+ "</dot>";
		}

		public String visitCircle(Circle c) {
			return "<circle>" + "\n" 
					+ "    <id>" + c.getId() + "</id>" + "\n" 
					+ "    <x>" + c.getX() + "</x>" + "\n"
					+ "    <y>" + c.getY() + "</y>" + "\n" 
					+ "    <radius>" + c.getRadius() + "</radius>" + "\n"
					+ "</circle>";
		}

		public String visitRectangle(Rectangle r) {
			return "<rectangle>" + "\n" 
					+ "    <id>" + r.getId() + "</id>" + "\n" 
					+ "    <x>" + r.getX() + "</x>" + "\n"
					+ "    <y>" + r.getY() + "</y>" + "\n" 
					+ "    <width>" + r.getWidth() + "</width>" + "\n"
					+ "    <height>" + r.getHeight() + "</height>" + "\n" 
					+ "</rectangle>";
		}

		public String visitCompoundGraphic(CompoundShape cg) {
			return "<compound_graphic>" + "\n" 
					+ "   <id>" + cg.getId() + "</id>" + "\n" 
					+ _visitCompoundGraphic(cg)
					+ "</compound_graphic>";
		}

		private String _visitCompoundGraphic(CompoundShape cg) {
			StringBuilder sb = new StringBuilder();
			for (Shape shape : cg.children) {
				String obj = shape.accept(this);
				// Proper indentation for sub-objects.
				obj = "    " + obj.replace("\n", "\n    ") + "\n";
				sb.append(obj);
			}
			return sb.toString();
		}

	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dot dot = new Dot(1, 10, 55);
		Circle circle = new Circle(2, 23, 15, 10);
		Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);
		
		Field field = dot.getClass().getDeclaredField("x");
		field.setAccessible(true);
		System.out.println(field.get(field));
		
//		CompoundShape compoundShape = new CompoundShape(4);
//		compoundShape.add(dot);
//		compoundShape.add(circle);
//		compoundShape.add(rectangle);
//
//		CompoundShape c = new CompoundShape(5);
//		c.add(dot);
//		
//		compoundShape.add(c);
//
//		export(circle, compoundShape);
		
	}

	private static void export(Shape... shapes) {
		XMLExportVisitor exportVisitor = new XMLExportVisitor();
		System.out.println(exportVisitor.export(shapes));
	}
}
