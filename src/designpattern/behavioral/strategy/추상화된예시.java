package designpattern.behavioral.strategy;

public class 추상화된예시 {
	public static void main(String[] args) {
		new Client().run();
	}
}
interface Strategy {
	void action();
}
class ConcreteStrategy1 implements Strategy{
	public void action() {/*doSomething*/}
}
class ConcreteStrategy2 implements Strategy{
	public void action() {/*doSomething*/}
}
class Context {
	Strategy strategy;
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public void execute() {
		System.out.println(strategy.getClass().getSimpleName());
		strategy.action();
	}
}
class Client{
	void run() {
		//전략 객체 생애주기를 클라이언트가 관리
		Context context = new Context();
		context.setStrategy(new ConcreteStrategy1());
		context.execute();
		context.setStrategy(new ConcreteStrategy2());
		context.execute();
	}
}