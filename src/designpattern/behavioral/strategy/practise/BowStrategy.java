package designpattern.behavioral.strategy.practise;

public class BowStrategy implements Strategy{
	@Override
	public void attack() {
		System.out.println("화살을 쏜다.");
	}
}
