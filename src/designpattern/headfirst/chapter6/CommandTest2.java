package designpattern.headfirst.chapter6;

public class CommandTest2 {
	// 리시버
	static class Light {
		String location = "";

		public Light(String location) {
			this.location = location;
		}

		public void on() {
			System.out.println(location + " light is on");
		}

		public void off() {
			System.out.println(location + " light is off");
		}
	}


	static class TV {
		String location;
		int channel;

		public TV(String location) {
			this.location = location;
		}

		public void on() {
			System.out.println("TV is on");
		}

		public void off() {
			System.out.println("TV is off");
		}

		public void setInputChannel() {
			this.channel = 3;
			System.out.println("Channel is set for VCR");
		}
	}

	static class Stereo {
		String location;

		public Stereo(String location) {
			this.location = location;
		}

		public void on() {
			System.out.println(location + " stereo is on");
		}

		public void off() {
			System.out.println(location + " stereo is off");
		}

		public void setCD() {
			System.out.println(location + " stereo is set for CD input");
		}

		public void setDVD() {
			System.out.println(location + " stereo is set for DVD input");
		}

		public void setRadio() {
			System.out.println(location + " stereo is set for Radio");
		}

		public void setVolume(int volume) {
			// code to set the volume
			// valid range: 1-11 (after all 11 is better than 10, right?)
			System.out.println(location + " stereo volume set to " + volume);
		}
	}

	static class Hottub {
		boolean on;
		int temperature;

		public Hottub() {
		}

		public void on() {
			on = true;
		}

		public void off() {
			on = false;
		}

		public void bubblesOn() {
			if (on) {
				System.out.println("Hottub is bubbling!");
			}
		}

		public void bubblesOff() {
			if (on) {
				System.out.println("Hottub is not bubbling");
			}
		}

		public void jetsOn() {
			if (on) {
				System.out.println("Hottub jets are on");
			}
		}

		public void jetsOff() {
			if (on) {
				System.out.println("Hottub jets are off");
			}
		}

		public void setTemperature(int temperature) {
			this.temperature = temperature;
		}

		public void heat() {
			temperature = 105;
			System.out.println("Hottub is heating to a steaming 105 degrees");
		}

		public void cool() {
			temperature = 98;
			System.out.println("Hottub is cooling to 98 degrees");
		}

	}

	static class CeilingFan {
		String location = "";
		int level;
		public static final int HIGH = 2;
		public static final int MEDIUM = 1;
		public static final int LOW = 0;

		public CeilingFan(String location) {
			this.location = location;
		}

		public void high() {
			// turns the ceiling fan on to high
			level = HIGH;
			System.out.println(location + " ceiling fan is on high");

		}

		public void medium() {
			// turns the ceiling fan on to medium
			level = MEDIUM;
			System.out.println(location + " ceiling fan is on medium");
		}

		public void low() {
			// turns the ceiling fan on to low
			level = LOW;
			System.out.println(location + " ceiling fan is on low");
		}

		public void off() {
			// turns the ceiling fan off
			level = 0;
			System.out.println(location + " ceiling fan is off");
		}

		public int getSpeed() {
			return level;
		}
	}

	static class GarageDoor {
		String location;

		public GarageDoor(String location) {
			this.location = location;
		}

		public void up() {
			System.out.println(location + " garage Door is Up");
		}

		public void down() {
			System.out.println(location + " garage Door is Down");
		}

		public void stop() {
			System.out.println(location + " garage Door is Stopped");
		}

		public void lightOn() {
			System.out.println(location + " garage light is on");
		}

		public void lightOff() {
			System.out.println(location + " garage light is off");
		}
	}


	// 커맨드
	@FunctionalInterface
	static interface Command {
		void execute();
	}

	static class NoCommand implements Command {
		public void execute() {
		}
	}

	static class LightOnCommand implements Command {
		Light light;

		public LightOnCommand(Light light) {
			this.light = light;
		}

		public void execute() {
			light.on();
		}
	}

	static class LightOffCommand implements Command {
		Light light;

		public LightOffCommand(Light light) {
			this.light = light;
		}

		public void execute() {
			light.off();
		}
	}

	static class GarageDoorUpCommand implements Command {
		GarageDoor garageDoor;

		public GarageDoorUpCommand(GarageDoor garageDoor) {
			this.garageDoor = garageDoor;
		}

		public void execute() {
			garageDoor.up();
		}
	}

	static class GarageDoorDownCommand implements Command {
		GarageDoor garageDoor;

		public GarageDoorDownCommand(GarageDoor garageDoor) {
			this.garageDoor = garageDoor;
		}

		public void execute() {
			garageDoor.up();
		}
	}

	static class StereoOnWithCDCommand implements Command {
		Stereo stereo;

		public StereoOnWithCDCommand(Stereo stereo) {
			this.stereo = stereo;
		}

		public void execute() {
			stereo.on();
			stereo.setCD();
			stereo.setVolume(11);
		}
	}

	static class StereoOffCommand implements Command {
		Stereo stereo;

		public StereoOffCommand(Stereo stereo) {
			this.stereo = stereo;
		}

		public void execute() {
			stereo.off();
		}
	}

	static class CeilingFanOnCommand implements Command {
		CeilingFan ceilingFan;

		public CeilingFanOnCommand(CeilingFan ceilingFan) {
			this.ceilingFan = ceilingFan;
		}

		public void execute() {
			ceilingFan.high();
		}
	}

	static class CeilingFanOffCommand implements Command {
		CeilingFan ceilingFan;

		public CeilingFanOffCommand(CeilingFan ceilingFan) {
			this.ceilingFan = ceilingFan;
		}

		public void execute() {
			ceilingFan.off();
		}
	}

	static class LivingroomLightOnCommand implements Command {
		Light light;

		public LivingroomLightOnCommand(Light light) {
			this.light = light;
		}

		public void execute() {
			light.on();
		}
	}

	static class LivingroomLightOffCommand implements Command {
		Light light;

		public LivingroomLightOffCommand(Light light) {
			this.light = light;
		}

		public void execute() {
			light.off();
		}
	}

	static class HottubOnCommand implements Command {
		Hottub hottub;

		public HottubOnCommand(Hottub hottub) {
			this.hottub = hottub;
		}

		public void execute() {
			hottub.on();
			hottub.heat();
			hottub.bubblesOn();
		}
	}

	static class HottubOffCommand implements Command {
		Hottub hottub;

		public HottubOffCommand(Hottub hottub) {
			this.hottub = hottub;
		}

		public void execute() {
			hottub.cool();
			hottub.off();
		}
	}

	// 인보커
	static class RemoteControl {
		Command[] onCommands;
		Command[] offCommands;

		public RemoteControl() {
			onCommands = new Command[7];
			offCommands = new Command[7];

			Command noCommand = new NoCommand();
			for (int i = 0; i < offCommands.length; i++) {
				onCommands[i] = noCommand;
				offCommands[i] = noCommand;
			}
		}

		public void setCommand(int slot, Command onCommand, Command offCommand) {
			onCommands[slot] = onCommand;
			offCommands[slot] = offCommand;
		}

		public void onButtonWasPushed(int slot) {
			onCommands[slot].execute();
		}

		public void offButtonWasPushed(int slot) {
			offCommands[slot].execute();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("\n------ 리모컨 ------\n");
			for (int i = 0; i < offCommands.length; i++) {
				sb.append("[slot" + i + "]" + onCommands[i].getClass().getSimpleName());
				sb.append("    " + offCommands[i].getClass().getSimpleName()+"\n");
			}
			return sb.toString();
		}
	}

	// 클라이언트, 커맨드 객체 생성, 인보커에게 커맨드 객체 전달
	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl();
		 
		Light livingRoomLight = new Light("Living Room");
		Light kitchenLight = new Light("Kitchen");
		CeilingFan ceilingFan= new CeilingFan("Living Room");
		GarageDoor garageDoor = new GarageDoor("Garage");
		Stereo stereo = new Stereo("Living Room");
		
		LightOnCommand livingRoomLightOn = 
				new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomLightOff = 
				new LightOffCommand(livingRoomLight);
		LightOnCommand kitchenLightOn = 
				new LightOnCommand(kitchenLight);
		LightOffCommand kitchenLightOff = 
				new LightOffCommand(kitchenLight);
  
		CeilingFanOnCommand ceilingFanOn = 
				new CeilingFanOnCommand(ceilingFan);
		CeilingFanOffCommand ceilingFanOff = 
				new CeilingFanOffCommand(ceilingFan);
 
		GarageDoorUpCommand garageDoorUp =
				new GarageDoorUpCommand(garageDoor);
		GarageDoorDownCommand garageDoorDown =
				new GarageDoorDownCommand(garageDoor);
 
		StereoOnWithCDCommand stereoOnWithCD =
				new StereoOnWithCDCommand(stereo);
		StereoOffCommand  stereoOff =
				new StereoOffCommand(stereo);
 
		remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
		remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
		remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
		remoteControl.setCommand(3, stereoOnWithCD, stereoOff);
  
		System.out.println(remoteControl);
 
		remoteControl.onButtonWasPushed(0);
		remoteControl.offButtonWasPushed(0);
		remoteControl.onButtonWasPushed(1);
		remoteControl.offButtonWasPushed(1);
		remoteControl.onButtonWasPushed(2);
		remoteControl.offButtonWasPushed(2);
		remoteControl.onButtonWasPushed(3);
		remoteControl.offButtonWasPushed(3);
	}

}
