package designpattern.headfirst.chapter6;

public class CommandTest {
	//리시버
	static class Light {
		public Light() {	}
		public void on() {
			System.out.println("Light is on");
		}
		public void off() {
			System.out.println("Light is off");
		}
	}
	static  class GarageDoor {
		public GarageDoor() {
		}

		public void up() {
			System.out.println("Garage Door is Open");
		}

		public void down() {
			System.out.println("Garage Door is Closed");
		}

		public void stop() {
			System.out.println("Garage Door is Stopped");
		}

		public void lightOn() {
			System.out.println("Garage light is on");
		}

		public void lightOff() {
			System.out.println("Garage light is off");
		}
	}
	
	//커맨드
	@FunctionalInterface
	static interface Command{
		void execute();
	}
	static class LightOnCommand implements Command{
		Light light;
		
		public LightOnCommand(Light light) {
			this.light = light;
		}
		public void execute() {
			light.on();
		}
	}
	static class GarageDoorOpenCommand implements Command{
		GarageDoor garageDoor;
		public GarageDoorOpenCommand(GarageDoor garageDoor) {
			this.garageDoor = garageDoor;
		}
		@Override
		public void execute() {
			garageDoor.up();
		}
	}
	//인보커
	static class SimpleRemoteControl{
		Command slot;
		public SimpleRemoteControl() {	}
		
		public void setCommand(Command slot) {
			this.slot = slot;
		}
		public void buttonWasPressed() {
			slot.execute();
		}
	}
	
	//클라이언트(편의상 main이 이 역할), 커맨드 객체 생성, 인보커에게 커맨드 객체 전달
	public static void main(String[] args) {
		//인보커, 내부에 커맨드 객체를 저장하고, 다시 요청이 오면 커맨드 객체를 실행
		SimpleRemoteControl remoteControl = new SimpleRemoteControl();
		//리시버, 실제 동작을 수행하는 객체
		Light light = new Light();
		//커맨드, 외부와  내부에 리시버에게 동작을 위임한다.
		LightOnCommand lightOnCommand = new LightOnCommand(light);
		//인보커에 커맨드 저장
		remoteControl.setCommand(lightOnCommand);
		//클라이언트가 저장한 커맨드 객체 실행 요청, 이 메서드는 단순히 리시버에 동작을 위임한다.
		remoteControl.buttonWasPressed();
		
		GarageDoor garageDoor = new GarageDoor();
		GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
		
		remoteControl.setCommand(garageDoorOpenCommand);
		remoteControl.buttonWasPressed();
	}

}
