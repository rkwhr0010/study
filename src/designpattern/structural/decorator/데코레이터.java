package designpattern.structural.decorator;

public class 데코레이터 {
	
	static interface Food{
		String eat();
	}
	
	static class 밥 implements Food{
		public String eat() {
			return "밥";
		}
	}
	
	static class 고추장 implements Food{
		private final Food food;

		public 고추장(Food food) {
			this.food = food;
		}
		@Override
		public String eat() {
			return "고추장 + "+food.eat();
		}
	}
	
	static class 나물 implements Food{
		private final Food food;
		public 나물(Food food) {
			this.food = food;
		}
		public String eat() {
			return "나물 + "+food.eat();
		}
	}
	
	public static void main(String[] args) {
		Food 밥 = new 밥();
		Food 고추장 = new 고추장(밥);
		Food 나물 = new 나물(고추장);
		System.out.println(나물.eat());
	}
}
