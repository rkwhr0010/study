package designpattern.structural.flyweight;

import java.time.LocalDate;
import java.time.Month;

public class FlyweightEx {
	//공통 인터페이스
	static interface Tree {
		public void display(int x, int y);
		public default boolean isWithinRange(LocalDate aDate) {
			Month month = aDate.getMonth();
			return (month.getValue() > 2) && (month.getValue() < 11);
		}
	}
	static class ConiferTree implements Tree {
		public void display(int x, int y) {
			System.out.println("침엽수 위치 : " + x + ", " + y);
		}
	}
	static class DeciduousTree implements Tree {
		public void display(int x, int y) {
			System.out.println("낙엽수 위치 : " + x + ", " + y);
			if (!this.isWithinRange(LocalDate.now())) {
				System.out.println("현재 계절엔 낙엽이 없습니다.");
			}
		}
	}
	//팩토리로 생성을 관리
	static class TreeFactory {
		Tree d, c = null;
		public TreeFactory() {
			this.d = new DeciduousTree();
			this.c = new ConiferTree();
		}
		public Tree getTree(String type) throws Exception {
			if (type.equals("침엽수")) {
				return this.d;
			} else if (type.equals("낙엽수")) {
				return this.c;
			} else {
				throw new Exception("지원하지 않는 종류");
			}
		}
	}
	
	public static void main(String[] args) {
		//플레이웨이트 클래스에서 상태만을 때어 별도로 관리
		//상태가 바뀔때마다 마치 새로운 인스턴스 처럼 보이지만, 하나의 인스턴스
		int[][] deciduousLocations = {{1, 1}, {33, 50}, {100, 90}};
		int[][] coniferLocations = {{10, 87}, {24, 76}, {2, 64}};
		
		TreeFactory treeFactory = new TreeFactory();
		Tree d, c;
		try {
			d = treeFactory.getTree("낙엽수");
			c = treeFactory.getTree("침엽수");
			for (int[] location : deciduousLocations) {
				d.display(location[0],  location[1]);
			}
			for (int[] location : coniferLocations) {
				c.display(location[0],  location[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
