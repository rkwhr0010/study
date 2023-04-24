package javabasic;

public class DoubleDispatch {
	static class DD{
		
		static void box(Box box) {
			String value = box.value;
		}
	}
	
	static class Box {
		String value = "ggg";
		String unBoxing(DD dd) {
			dd.box(this);
			return value;
		}
	}
	
	static class Wrapper{
		String value = "램";
		String unWrapping() {
			return value;
		}
	}
	
}
