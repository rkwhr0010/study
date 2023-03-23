package designpattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 개체 지향 프로그래밍에서 명령 패턴은 동작을 수행하거나 나중에 이벤트를 트리거하는 데 필요한 
 * 모든 정보를 캡슐화하는 데 개체를 사용하는 행동 설계 패턴입니다.
 * 요청을 객체로 캡슐화하여 다양한 요청, 대기열 또는 로그 요청으로 클라이언트를 매개변수화하고 실행 취소 가능한 작업을 지원할 수 있습니다.
 * 이 모델을 사용하면 소비자로부터 명령을 생성하는 개체를 분리할 수 있으므로 패턴이 일반적으로 생산자-소비자 패턴으로 알려져 있습니다.
 * 클래식 구현에서 명령 패턴은 명령(Command), 수신자(Receiver), 호출자(Invoker) 및 클라이언트(Client)의 네 가지 구성 요소를 구현해야 합니다.
 */
public class 커맨드패턴 {
	
	/**
	 * ##수신기(Receiver)
	 * 일련의 응집력 있는 작업을 수행하는 개체입니다. 
	 * 명령의 execute() 메서드가 호출될 때 실제 작업을 수행하는 구성 요소입니다.
	 */
	static class TextFile{
		private String name;
		
		public TextFile(String name) {
			this.name = name;
		}
		public String open() {	return "파일 여는 중 ... " + name;	}
		public String read() {	return "파일 읽는 중 ... " + name;	}
		public String write() {	return "파일 쓰는 중 ... " + name;	}
		public String save() {	return "파일 저장 중 ... " + name;	}
		public String copy() {	return "파일 복사 중 ... " + name;	}
		public String paste() {	return "파일 붙이는 중 ... " + name;	}
	}
	
	/*
	 * ## 명령(Command) 객체
	 * 명령은 호출할 메서드, 메서드 인수 및 메서드를 구현하는 개체(Receiver)를 포함하여 작업을 실행하는 데 필요한 모든 정보를 저장하는 역할을 하는 개체입니다.
	 */
	@FunctionalInterface
	static interface TextFileOperation{
		//TextFileOperation 명령은 필요한 모든 정보를 캡슐화합니다.
		String execute();
	}
	static class OpenTextFileOperation implements TextFileOperation{
		//(Receiver)를 포함
		//파일 작업을 수행하는 구성 요소가 수신기(TextFile 인스턴스)라는 점을 강조!!!!
		private TextFile textFile;
		public OpenTextFileOperation(TextFile textFile) {
			this.textFile = textFile;
		}
		public String execute() {
			return textFile.open();
		}
	}
	
	static class SaveTextFileOperation implements TextFileOperation{
		private TextFile textFile;
		public SaveTextFileOperation(TextFile textFile) {
			this.textFile = textFile;
		}
		public String execute() {
			return textFile.save();
		}
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
	static class TextFileOperationExecutor {
		//대기열을 저장, 지네릭은 인터페이스로 정확히 무슨 일을 수행하는지는 인보커는 모른다.
		private final List<TextFileOperation> textFileOperations = new ArrayList<>();
		public String executeOperation(TextFileOperation textFileOperation) {
			textFileOperations.add(textFileOperation);
			return textFileOperation.execute();
		}
	}
	/**
	 * ## 클라이언트(Client)
	 * 클라이언트는 실행할 명령과 실행할 프로세스 단계를 지정하여 명령 실행 프로세스를 제어하는 ​​개체입니다.
	 * 따라서 패턴의 형식적 정의를 정통으로 사용하려면 일반적인 main 메서드를 사용하여 클라이언트 클래스를 만들어야 합니다.
	 */
	public static void main(String[] args) {
		//인보커, 필요에 따라 되돌리기 기능도 구현한다.
		TextFileOperationExecutor executor = new TextFileOperationExecutor();
		//리시버
		TextFile textFile = new TextFile("나는 리시버");
		//커맨드 (인터페이스로 추상화), 내부에 리시버
		TextFileOperation openTextFileOperation = new OpenTextFileOperation(textFile);
		TextFileOperation saveTextFileOperation = new SaveTextFileOperation(textFile);
		
		System.out.println(executor.executeOperation(new OpenTextFileOperation(new TextFile("파일1.txt"))));
		System.out.println(executor.executeOperation(new SaveTextFileOperation(new TextFile("파일1.txt"))));
		System.out.println(executor.executeOperation(()->"파일 읽고 쓰고 다한다 마!"));
	}
	
}
