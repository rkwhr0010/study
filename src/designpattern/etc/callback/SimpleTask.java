package designpattern.etc.callback;

public class SimpleTask extends Task{

	@Override
	public void execute() {
		System.out.println("실행됐습니다.");
	}
}
