package designpattern.headfirst.chapter6;

public class CommandTest4 {
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
		public static final int HIGH = 3;
		public static final int MEDIUM = 2;
		public static final int LOW = 1;
		public static final int OFF = 0;
		String location;
		int speed;

		public CeilingFan(String location) {
			this.location = location;
			speed = OFF;
		}

		public void high() {
			speed = HIGH;
			System.out.println(location + " ceiling fan is on high");
		}

		public void medium() {
			speed = MEDIUM;
			System.out.println(location + " ceiling fan is on medium");
		}

		public void low() {
			speed = LOW;
			System.out.println(location + " ceiling fan is on low");
		}

		public void off() {
			speed = OFF;
			System.out.println(location + " ceiling fan is off");
		}

		public int getSpeed() {
			return speed;
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
	static interface Command {
		void execute();

		void undo();
	}

	static class NoCommand implements Command {
		public void execute() {
		}

		public void undo() {
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

		public void undo() {
			light.off();
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

		@Override
		public void undo() {
			light.on();
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

		@Override
		public void undo() {
			garageDoor.down();
		}
	}

	static class GarageDoorDownCommand implements Command {
		GarageDoor garageDoor;

		public GarageDoorDownCommand(GarageDoor garageDoor) {
			this.garageDoor = garageDoor;
		}

		public void execute() {
			garageDoor.down();
		}

		public void undo() {
			garageDoor.up();
		}
	}

	static class TVOnCommand implements Command {
		TV tv;

		public TVOnCommand(TV tv) {
			this.tv = tv;
		}

		public void execute() {
			tv.on();
			tv.setInputChannel();
		}

		public void undo() {
			tv.off();
		}
	}

	static class TVOffCommand implements Command {
		TV tv;

		public TVOffCommand(TV tv) {
			this.tv = tv;
		}

		public void execute() {
			tv.off();
		}

		public void undo() {
			tv.on();
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

		@Override
		public void undo() {
			stereo.off();
		}
	}

	static class StereoOnCommand implements Command {
		Stereo stereo;

		public StereoOnCommand(Stereo stereo) {
			this.stereo = stereo;
		}

		public void execute() {
			stereo.on();
		}

		public void undo() {
			stereo.off();
		}
	}

	static class StereoOffCommand implements Command {
		Stereo stereo;
		int prev;

		public StereoOffCommand(Stereo stereo) {
			this.stereo = stereo;
		}

		public void execute() {
			stereo.off();
		}

		@Override
		public void undo() {
			stereo.on();
			stereo.setCD();
			stereo.setVolume(11);
		}
	}

	static class CeilingFanHighCommand implements Command {
		CeilingFan ceilingFan;
		int prevSpeed;

		public CeilingFanHighCommand(CeilingFan ceilingFan) {
			this.ceilingFan = ceilingFan;
		}

		public void execute() {
			prevSpeed = ceilingFan.getSpeed();
			ceilingFan.high();
		}

		public void undo() {
			if (prevSpeed == CeilingFan.HIGH) {
				ceilingFan.high();
			} else if (prevSpeed == CeilingFan.MEDIUM) {
				ceilingFan.medium();
			} else if (prevSpeed == CeilingFan.LOW) {
				ceilingFan.low();
			} else if (prevSpeed == CeilingFan.OFF) {
				ceilingFan.off();
			}
		}
	}

	static class CeilingFanMediumCommand implements Command {
		CeilingFan ceilingFan;
		int prevSpeed;

		public CeilingFanMediumCommand(CeilingFan ceilingFan) {
			this.ceilingFan = ceilingFan;
		}

		public void execute() {
			prevSpeed = ceilingFan.getSpeed();
			ceilingFan.medium();
		}

		public void undo() {
			if (prevSpeed == CeilingFan.HIGH) {
				ceilingFan.high();
			} else if (prevSpeed == CeilingFan.MEDIUM) {
				ceilingFan.medium();
			} else if (prevSpeed == CeilingFan.LOW) {
				ceilingFan.low();
			} else if (prevSpeed == CeilingFan.OFF) {
				ceilingFan.off();
			}
		}
	}

	static class CeilingFanLowCommand implements Command {
		CeilingFan ceilingFan;
		int prevSpeed;

		public CeilingFanLowCommand(CeilingFan ceilingFan) {
			this.ceilingFan = ceilingFan;
		}

		public void execute() {
			prevSpeed = ceilingFan.getSpeed();
			ceilingFan.low();
		}

		public void undo() {
			if (prevSpeed == CeilingFan.HIGH) {
				ceilingFan.high();
			} else if (prevSpeed == CeilingFan.MEDIUM) {
				ceilingFan.medium();
			} else if (prevSpeed == CeilingFan.LOW) {
				ceilingFan.low();
			} else if (prevSpeed == CeilingFan.OFF) {
				ceilingFan.off();
			}
		}
	}

	static class CeilingFanOffCommand implements Command {
		CeilingFan ceilingFan;
		int prevSpeed;

		public CeilingFanOffCommand(CeilingFan ceilingFan) {
			this.ceilingFan = ceilingFan;
		}

		public void execute() {
			prevSpeed = ceilingFan.getSpeed();
			ceilingFan.off();
		}

		public void undo() {
			if (prevSpeed == CeilingFan.HIGH) {
				ceilingFan.high();
			} else if (prevSpeed == CeilingFan.MEDIUM) {
				ceilingFan.medium();
			} else if (prevSpeed == CeilingFan.LOW) {
				ceilingFan.low();
			} else if (prevSpeed == CeilingFan.OFF) {
				ceilingFan.off();
			}
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

		public void undo() {
			light.off();
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

		public void undo() {
			light.on();
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

		public void undo() {
			hottub.cool();
			hottub.off();
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

		@Override
		public void undo() {
			hottub.on();
			hottub.heat();
			hottub.bubblesOn();
		}
	}

	static class MacroCommand implements Command {
		Command[] commands;

		public MacroCommand(Command[] commands) {
			this.commands = commands;
		}

		@Override
		public void execute() {
			for (Command command : commands) {
				command.execute();
			}
		}

		@Override
		public void undo() {
			for (Command command : commands) {
				command.undo();
			}
		}
	}

	// 인보커
	static class RemoteControl {
		Command[] onCommands;
		Command[] offCommands;
		Command undoCommand;

		public RemoteControl() {
			onCommands = new Command[7];
			offCommands = new Command[7];

			Command noCommand = new NoCommand();
			for (int i = 0; i < offCommands.length; i++) {
				onCommands[i] = noCommand;
				offCommands[i] = noCommand;
			}
			undoCommand = noCommand;
		}

		public void setCommand(int slot, Command onCommand, Command offCommand) {
			onCommands[slot] = onCommand;
			offCommands[slot] = offCommand;
		}

		public void onButtonWasPushed(int slot) {
			onCommands[slot].execute();
			undoCommand = onCommands[slot];
		}

		public void offButtonWasPushed(int slot) {
			offCommands[slot].execute();
			undoCommand = offCommands[slot];
		}

		public void undoButtonWasPushed() {
			undoCommand.undo();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("\n------ 리모컨 ------\n");
			for (int i = 0; i < offCommands.length; i++) {
				sb.append("[slot" + i + "]" + onCommands[i].getClass().getSimpleName());
				sb.append("    " + offCommands[i].getClass().getSimpleName() + "\n");
			}
			sb.append("[undo] " + undoCommand.getClass().getSimpleName() + "\n");
			return sb.toString();
		}
	}

	// 클라이언트, 커맨드 객체 생성, 인보커에게 커맨드 객체 전달
	public static void main(String[] args) {
		//인보커
		RemoteControl remoteControl = new RemoteControl();
		//리시버
		Light light = new Light("Living Room");
		TV tv = new TV("Living Room");
		Stereo stereo = new Stereo("Living Room");
		Hottub hottub = new Hottub();
		//커맨드
		LightOnCommand lightOn = new LightOnCommand(light);
		StereoOnCommand stereoOn = new StereoOnCommand(stereo);
		TVOnCommand tvOn = new TVOnCommand(tv);
		HottubOnCommand hottubOn = new HottubOnCommand(hottub);
		LightOffCommand lightOff = new LightOffCommand(light);
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);
		TVOffCommand tvOff = new TVOffCommand(tv);
		HottubOffCommand hottubOff = new HottubOffCommand(hottub);

		Command[] partyOn = { lightOn, stereoOn, tvOn, hottubOn};
		Command[] partyOff = { lightOff, stereoOff, tvOff, hottubOff};
  
		MacroCommand partyOnMacro = new MacroCommand(partyOn);
		MacroCommand partyOffMacro = new MacroCommand(partyOff);
 
		remoteControl.setCommand(0, partyOnMacro, partyOffMacro);
  
		System.out.println(remoteControl);
		System.out.println("--- Pushing Macro On---");
		remoteControl.onButtonWasPushed(0);
		System.out.println("--- Pushing Macro Off---");
		remoteControl.offButtonWasPushed(0);
	}

}
