package designpattern.etc.callback;

public class CallBackDriver {
	public static void main(String[] args) {
		Task task = new SimpleTask();
		task.executeWith(()->System.out.println("콜백은 다른 함수에 의해 실행되는 함수"));
		
	}
}
