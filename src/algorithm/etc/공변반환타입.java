package algorithm.etc;

public class 공변반환타입 {
	public static void main(String[] args) {
		
	}
	
	static abstract class Value{
		abstract Number getNumber();
	}
	
	static class Val extends Value{

		@Override
		Long getNumber() {
			return Long.valueOf(1);
		}
	}
}
