package designpattern.behavioral.visitor;

import java.util.Arrays;

public class VisitorEx2 {
	static abstract class Unit {

		private final Unit[] children;

		public Unit(Unit... children) {
			this.children = children;
		}

		public void accept(UnitVisitor visitor) {
			Arrays.stream(children).forEach(child -> child.accept(visitor));
		}
	}

	static interface UnitVisitor {

		void visit(Soldier soldier);

		void visit(Sergeant sergeant);

		void visit(Commander commander);
	}

	static class Commander extends Unit {

		public Commander(Unit... children) {
			super(children);
		}

		@Override
		public void accept(UnitVisitor visitor) {
			visitor.visit(this);
			super.accept(visitor);
		}

		@Override
		public String toString() {
			return "commander";
		}
	}

	static class Sergeant extends Unit {

		public Sergeant(Unit... children) {
			super(children);
		}

		@Override
		public void accept(UnitVisitor visitor) {
			visitor.visit(this);
			super.accept(visitor);
		}

		@Override
		public String toString() {
			return "sergeant";
		}
	}

	static class Soldier extends Unit {

		public Soldier(Unit... children) {
			super(children);
		}

		@Override
		public void accept(UnitVisitor visitor) {
			visitor.visit(this);
			super.accept(visitor);
		}

		@Override
		public String toString() {
			return "soldier";
		}
	}

	static class CommanderVisitor implements UnitVisitor {

		@Override
		public void visit(Soldier soldier) {
			// Do nothing
		}

		@Override
		public void visit(Sergeant sergeant) {
			// Do nothing
		}

		@Override
		public void visit(Commander commander) {
			System.out.println("Good to see you {}" + commander);
		}
	}

	static class SergeantVisitor implements UnitVisitor {

		@Override
		public void visit(Soldier soldier) {
			// Do nothing
		}

		@Override
		public void visit(Sergeant sergeant) {
			System.out.println("Hello {}" + sergeant);
		}

		@Override
		public void visit(Commander commander) {
			// Do nothing
		}
	}

	static class SoldierVisitor implements UnitVisitor {

		@Override
		public void visit(Soldier soldier) {
			System.out.println("Greetings {}" + soldier);
		}

		@Override
		public void visit(Sergeant sergeant) {
			// Do nothing
		}

		@Override
		public void visit(Commander commander) {
			// Do nothing
		}
	}

	public static void main(String[] args) {
		
//		commander.accept(new SoldierVisitor());
//		commander.accept(new SergeantVisitor());
//		commander.accept(new CommanderVisitor());
	}
	
}
