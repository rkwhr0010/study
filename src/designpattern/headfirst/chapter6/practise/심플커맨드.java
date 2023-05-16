package designpattern.headfirst.chapter6.practise;

import java.util.*;

public class 심플커맨드 {
	static interface Command{
		void execute();
	}
	static class Invoker{
		//큐 예시
		private final Queue<Command> commands = new LinkedList<>();
		public void addCommand(Command command) {
			this.commands.add(command);
		}
		public void action() {
			if(!commands.isEmpty())
				commands.poll().execute();
			else System.out.println("더 이상의 요청은 없어요!");
		}
	}
	public static void main(String[] args) {
		Invoker invoker = new Invoker();
		invoker.addCommand(()->System.out.println("커맨드 객체는 요청을 캡슐화합니다."));
		invoker.addCommand(()->System.out.println("커맨드 객체는 요청을 캡슐화합니다."));
		
		invoker.action();
		invoker.action();
		invoker.action();
	}
}
