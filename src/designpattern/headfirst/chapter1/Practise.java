package designpattern.headfirst.chapter1;

public class Practise {
	static interface WeponBehavior{
		void useWeapon();
	}
	static class KnifeBehavior implements WeponBehavior{
		public void useWeapon() {
			System.out.println("나이프로 찌릅니다.");
		}
	}
	static class BowAndArrowBehavior implements WeponBehavior{
		public void useWeapon() {
			System.out.println("활을 쏩니다. 슈슛");
		}
	}
	static class AxeBehavior implements WeponBehavior{
		public void useWeapon() {
			System.out.println("도끼로 찍습니다.");
		}
	}
	static class SwordBehavior implements WeponBehavior{
		public void useWeapon() {
			System.out.println("검을 휘두릅니다.");
		}
	}
	
	static abstract class Character{
		WeponBehavior weponBehavior;
		
		public void setWeponBehavior(WeponBehavior weponBehavior) {
			this.weponBehavior = weponBehavior;
		}

		void fight() {
			System.out.print(this.getClass().getSimpleName());
			weponBehavior.useWeapon();
		}
	}
	
	static class Queen extends Character{
		public Queen() {
			weponBehavior = new KnifeBehavior();
		}
	}
	static class King extends Character{
		public King() {
			weponBehavior = new BowAndArrowBehavior();
		}
	}
	static class Knight extends Character{
		public Knight() {
			weponBehavior = new SwordBehavior();
		}
	}
	static class Troll extends Character{
		public Troll() {
			weponBehavior = new AxeBehavior();
		}
	}
	
	public static void main(String[] args) {
		WeponBehavior knifeBehavior = new KnifeBehavior();
		WeponBehavior bowAndArrowBehavior = new BowAndArrowBehavior();
		WeponBehavior swordBehavior = new SwordBehavior();
		WeponBehavior axeBehavior = new AxeBehavior();
		
		Character king = new King();
		Character queen = new Queen();
		Character knight = new Knight();
		Character troll = new Troll();
		
		king.fight();
		queen.fight();
		knight.fight();
		troll.fight();
		
		king.setWeponBehavior(knifeBehavior);
		queen.setWeponBehavior(bowAndArrowBehavior);
		knight.setWeponBehavior(axeBehavior);
		troll.setWeponBehavior(swordBehavior);
		
		king.fight();
		queen.fight();
		knight.fight();
		troll.fight();
		
	}
}
