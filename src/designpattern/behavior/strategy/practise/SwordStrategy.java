package designpattern.behavior.strategy.practise;

public class SwordStrategy implements Strategy{

	@Override
	public void attack() {
		System.out.println("검을 휘두른다.");
	}

}
