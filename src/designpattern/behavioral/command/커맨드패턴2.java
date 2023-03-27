package designpattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class 커맨드패턴2 {
	/**
	 * 커맨드 객체
	 */
	
	static interface Command{
		void execute();
	}
	//구상 커맨드
	static class WriteCommand implements Command{
		private Document document;
		public WriteCommand(Document document) {
			this.document = document;
		}
		@Override
		public void execute() {
			document.write();
		}
		
	}
	static class ReadCommand implements Command{
		private Document document;
		public ReadCommand(Document document) {
			this.document = document;
		}
		@Override
		public void execute() {
			document.read();
		}
	}
	/**
	 * 리시버
	 */
	static class Document{
		public void write() {System.out.println("쓰기!!!");}
		public void read() {System.out.println("읽기!!!");}
	}
	
	static class DocumentInvoker{
		private final List<Command> commands = new ArrayList<>();
		public void addCommand(Command command) {
			commands.add(command);
		}
		public void removeCommand(Command command) {
			commands.remove(command);
		}
		public void invoke() {
			for(Command command : commands) {
				command.execute();
			}
		}
		
	}
	public static void main(String[] args) {
		DocumentInvoker invoker = new DocumentInvoker();
		Document document = new Document();
		invoker.addCommand(new ReadCommand(document));
		invoker.addCommand(new WriteCommand(document));
		invoker.invoke();
		
		
	}
	
	
}
