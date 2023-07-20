package designpattern.behavioral.state;

public class 추상화된예시 {
	public static void main(String[] args) {
		new Client().run();
	}
}
interface State {
	void action();
}
//optional 
abstract class StateBase implements State{
	Context context;
	public StateBase(Context context) {
		this.context = context;
	}
}
class ConcreteState1 extends StateBase{
	public ConcreteState1(Context context) {
		super(context);
	}
	public void action() {
		System.out.println("doSomething");
		context.setState(context.getState2());
	}
}
class ConcreteState2 extends StateBase{
	public ConcreteState2(Context context) {
		super(context);
	}
	public void action() {
		System.out.println("doSomething");
		context.setState(context.getState1());
	}
}
class Context {
	private State state;
	//상태 생어주기를 Context가 관리
	private StateBase state1 = new ConcreteState1(this);
	private StateBase state2 = new ConcreteState2(this);
	
	public Context() {
		state = state1; // 시작 상태
	}
	
	void execute() {
		state.action();
	}
	
	void setState(State state) {
		System.out.println(this.state.getClass().getSimpleName()+"==>"+
				state.getClass().getSimpleName());
		this.state = state;
	}
	StateBase getState1() {
		return state1;
	}
	StateBase getState2() {
		return state2;
	}
}
class Client {
	void run() {
		Context context = new Context();
		context.execute();
		context.execute();
		context.execute();
		context.execute();
	}
}
