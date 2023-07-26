package javabasic.solid;

/*
 * 버틀란트 메이어(Bertrand Meyer)박사가 1998년 객체지향 소프트웨어 설계 라는 책에서 
 * 정의한 내용으로 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 
 * 변경에는 닫혀있어야 한다는 원리입니다. 
 * 이것은 변경을 위한 비용은 가능한 줄이고 확장을 위한 비용은 가능한 극대화 해야 한다는 의미로, 
 * 요구사항의 변경이나 추가사항이 발생하더라도, 기존 구성요소는 수정이 일어나지 말아야 하며, 
 * 기존 구성요소를 쉽게 확장해서 재사용할 수 있어야 한다는 뜻입니다. 
 * 로버트 C. 마틴은 OCP는 관리가능하고 재사용 가능한 코드를 만드는 기반이며, 
 * OCP를 가능케 하는 중요 메커니즘은 추상화와 다형성이라고 설명하고 있습니다. 
 * OCP는 객체지향의 장점을 극대화하는 아주 중요한 원리라 할 수 있습니다.
 * 
 * 기존코드는 수정되면 안된다 == 기존 코드에서 버그가 스며들지 않는다
 * 
 */
public class OpenClosed {
	
	/* 1차
	 * 구상 클래스가 존재한다.
	 * 갑자기 Json 변환 기능도 필요해졌다.
	 * 이 클래스를 확장해서 기능을 추가하면, SRP에 위배된다.
	 * 그렇다고 이 클래스 자체에 기능을 추가하면 OCP에 위배된다.
	 */
	static class StringToXml{
		final String data;

		public StringToXml(String data) {
			this.data = data;
		}
		public String toXml() {
			return "<xml>"+data+"</xml>";
		}
	}
	/*
	 * 2차
	 * 먼저 확실히 변하지 않을 부분을 찝어내 추상화한다.
	 * 인터페이스는 되도록 변경되지 않아야 한다. 따라서 이 과정은 신중히 이뤄져야 한다.
	 * 
	 * 이후 확장을(Open) 해 기능을 추가한다.
	 */
	
	interface StringConverter {
		String converting();
	}
	static class StringToXml2 implements StringConverter{
		final String data;

		public StringToXml2(String data) {
			this.data = data;
		}
		public String converting() {
			return "<xml>"+data+"</xml>";
		}
	}
	static class JsonConverter implements StringConverter {
		final String data;
		
		public JsonConverter(String data) {
			this.data = data;
		}
		public String converting() {
			return "{'data' : " + data +"}";
		}
	}
}
