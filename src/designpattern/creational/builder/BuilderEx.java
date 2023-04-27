package designpattern.creational.builder;

public class BuilderEx {
	static class House{
		String interior; //내장재
		String exterior; //외장재
		int floor; // 층수
		int room;  // 방수
		int window;// 창문수
		
		public House(String interior, String exterior, int floor, int room, int window) {
			super();
			this.interior = interior;
			this.exterior = exterior;
			this.floor = floor;
			this.room = room;
			this.window = window;
		}

		@Override
		public String toString() {
			return "House [interior=" + interior + ", exterior=" + exterior + ", floor=" + floor + ", room=" + room
					+ ", window=" + window + "]";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new House("황토", "나무", 2, 4, 10));
		//생성자 인수의 순서를 고려해야한다. 고려해도 실수 가능성은 인수수에 비례해 증가한다.
	}
}
