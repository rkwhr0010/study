package designpattern.creational.builder;

import designpattern.creational.builder.BuilderEx3.Builder;
import designpattern.creational.builder.BuilderEx3.HouseDirector;

public class BuilderEx3 {
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
	
	//빌더
	static interface Builder{
		//편의상 메서드 체이닝을 적용한 경우가 많다.
		Builder setInterior(String interior);
		Builder setExterior(String exterior);
		Builder setFloor(int floor);
		Builder setRoom(int room);
		Builder setWindow(int window);
		//생성 메서드는 단일 계층 구조엔 상관없으나, 다중 계층구조이면 서브클래스에 위임한다.
		//리턴하는 객체 타입이 다르기 때문이다.
		House build();
	}
	
	static class HouseBuilder implements Builder{
		//기존 코드가 설정자 메서드를 지원한하기에 임시로 값을 저장해둔다.
		String interior; //내장재
		String exterior; //외장재
		int floor; // 층수
		int room;  // 방수
		int window;// 창문수
		
		public Builder setInterior(String interior) {
			this.interior = interior;
			return this;
		}
		public Builder setExterior(String exterior) {
			this.exterior = exterior;
			return this;
		}
		public Builder setFloor(int floor) {
			this.floor = floor;
			return this;
		}
		public Builder setRoom(int room) {
			this.room = room;
			return this;
		}
		public Builder setWindow(int window) {
			this.window = window;
			return this;
		}
		@Override
		public House build() {
			//검증 로직이 존재할 수도 있을 것, 혹은 기본값으로 설정되지 않은 값을 대신 할 수도 있다.
			if(interior == null && exterior == null 
					&& floor == 0&& room == 0&& window == 0) {
				//기본적으로 빌더 패턴은 사용자가 생성구조를 잘 파악하고 있어야 함을 전제로 한다.
				throw new IllegalArgumentException("인수가 부족합니다.");
			}
			try {
				return new House(interior, exterior, floor, room, window);
			}finally {
				interior = null;
				exterior = null;
				floor = 0;
				room = 0;
				window = 0;
			}
		}
	}
	
	static class HouseDirector{
		public void house1(Builder builder) {
			builder.setExterior("나무");
			builder.setInterior("대리석");
			builder.setFloor(2);
			builder.setRoom(5);
			builder.setWindow(10);
		}
		public void house2(Builder builder) {
			builder.setExterior("콘크리트")
				.setInterior("유리")
				.setFloor(1)
				.setRoom(1)
				.setWindow(10);
		}
	}
	
	public static void main(String[] args) {
		Builder houseBuilder = new HouseBuilder();
		HouseDirector houseDirector = new HouseDirector();
		houseDirector.house1(houseBuilder);
		System.out.println(houseBuilder.build());
		houseDirector.house2(houseBuilder);
		System.out.println(houseBuilder.build());
	}
	
}
