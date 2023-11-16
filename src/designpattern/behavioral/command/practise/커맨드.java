package designpattern.behavioral.command.practise;

import java.util.ArrayList;
import java.util.List;

import designpattern.behavioral.command.practise.커맨드.Command;

public class 커맨드 {
	//리시버, 실제 동작 수행
	static class PrintRecevier{
		public void copy() {
			System.err.println("Copy...");
		}
		public void print() {
			System.err.println("Print...");
		}
	}
	//커맨드 객체
	static interface Command{
		void execute();
	}
	static class CopyCommand implements Command{
		private final PrintRecevier printRecevier;
		public CopyCommand(PrintRecevier printRecevier) {
			this.printRecevier = printRecevier;
		}
		@Override
		public void execute() {
			printRecevier.copy();
		}
	}
	static class PrintCommand implements Command{
		private final PrintRecevier printRecevier;
		public PrintCommand(PrintRecevier printRecevier) {
			this.printRecevier = printRecevier;
		}
		@Override
		public void execute() {
			printRecevier.print();
		}
	}
	//인보커
	static class Invoker {
		private final List<Command> commands = new ArrayList<>();
		
		public void addCommand(Command command) {
			commands.add(command);
		}
		public void removeCommand(Command command) {
			commands.remove(command);
		}
		
		public void execute() {
			for(Command command : commands) {
				command.execute();
			}
		}
	}
	public static void main(String[] args) {
		PrintRecevier printRecevier = new PrintRecevier();
		Command copyCommand = new CopyCommand(printRecevier);
		Command printCommand = new PrintCommand(printRecevier);
		
		Invoker invoker = new Invoker();
		invoker.addCommand(copyCommand);
		invoker.addCommand(printCommand);
		
		invoker.execute();
		
		
	}
	
}
