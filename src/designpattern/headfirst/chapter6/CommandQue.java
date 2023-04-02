package designpattern.headfirst.chapter6;

import java.util.LinkedList;
import java.util.Queue;

public class CommandQue {
	static interface Command{
		void execute();
	}
	
	static class Invoker{
		Queue<Command> commands = new LinkedList<>();
		
		void addCommand(Command command) {
			commands.add(command);
		}
		void execute() {
			if(!commands.isEmpty()) {
				commands.poll().execute();
			}
		}
	}
	
	static class HelloReciver{
		void sayHello() {
			System.out.println("헬로!");
		}
	}
	
	static class HelloCommand implements Command{
		HelloReciver helloReciver;
		public HelloCommand(HelloReciver helloReciver) {
			this.helloReciver = helloReciver;
		}
		@Override
		public void execute() {
			helloReciver.sayHello();
		}
	}
	
	
	public static void main(String[] args) {
		Invoker invoker = new Invoker();
		HelloReciver helloReciver = new HelloReciver();
		HelloCommand helloCommand = new HelloCommand(helloReciver);
		
		invoker.addCommand(helloCommand);
		invoker.addCommand(()->System.out.println("내맘대로 기능"));
		
		invoker.execute();
		invoker.execute();
		invoker.execute();
		
		
	}
	
}
