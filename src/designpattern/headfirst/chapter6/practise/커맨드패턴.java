package designpattern.headfirst.chapter6.practise;

import java.util.LinkedList;
import java.util.Queue;

public class 커맨드패턴 {
	
	static interface Command{
		void execute();
	}
	static class ReadCommand implements Command{
		Word word;
		public ReadCommand(Word word) {	this.word = word;}
		public void execute() {	word.read();}
	}
	static class WriteCommand implements Command{
		Word word;
		public WriteCommand(Word word) {	this.word = word;}
		public void execute() {	word.write();}
	}
	static class DeleteCommand implements Command{
		Word word;
		public DeleteCommand(Word word) {	this.word = word;}
		public void execute() {	word.delete();}
	}
	static class CreateCommand implements Command{
		Word word;
		public CreateCommand(Word word) {	this.word = word;}
		public void execute() {	word.create();}
	}
	/**
	 * ## 호출자(Invoker) 
	 * 주어진 명령을 실행하는 방법을 알고 있지만 명령이 어떻게 구현되었는지는 모르는 객체입니다. 
	 * 명령의 인터페이스만 알고 있습니다.
	 * 경우에 따라 호출자는 명령을 실행하는 것 외에도 명령을 저장하고 대기열에 넣습니다. 
	 * 이는 매크로 기록 또는 실행 취소 및 다시 실행 기능과 같은 일부 추가 기능을 구현하는 데 유용합니다.
	 * 
	 * TextFileOperationExecutor 클래스는 소비자로부터 명령 개체를 분리하고 
	 * TextFileOperation 명령 개체 내에 캡슐화된 메서드를 호출하는 추상화의 얇은 계층에 불과합니다.
	 * 이 경우 클래스는 명령 개체도 목록에 저장합니다. 
	 * 물론 작업 실행 프로세스에 추가 제어를 추가해야 하는 경우가 아니면 패턴 구현에서 필수 사항은 아닙니다.
	 */
	static class WordInvoker{
		final Queue<Command> commands = new LinkedList<>();		
		
		void addCommand(Command command) {
			commands.add(command);
		}
		
		void invoke() {
			if(!commands.isEmpty())
				commands.poll().execute();
		}
		
	}
	
	/**
	 * 리시버 실제로 알고리즘을 수행하는 객체
	 */
	static class Word{
		
		void read() {
			System.out.println("워드 읽기");
		}
		void write() {
			System.out.println("워드 쓰기");
		}
		void delete() {
			System.out.println("워드 파일삭제");
		}
		void create() {
			System.out.println("워드 파일생성");
		}
	}
	public static void main(String[] args) {
		WordInvoker invoker = new WordInvoker();
		Word word = new Word();
		invoker.addCommand(new ReadCommand(word));
		invoker.addCommand(new WriteCommand(word));
		invoker.addCommand(new CreateCommand(word));
		invoker.addCommand(new DeleteCommand(word));
		
		invoker.invoke();
		invoker.invoke();
		invoker.invoke();
		invoker.invoke();
		invoker.invoke();
		invoker.invoke();
		
	}
	
	
}
