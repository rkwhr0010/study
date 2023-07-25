package javabasic.solid;
/*
 * 단일 책임 원칙부터 시작하겠습니다. 예상할 수 있듯이 
 * 이 원칙에 따르면 클래스는 하나의 책임만 져야 합니다. 
 * 또한 변경해야 하는 이유는 단 하나여야 합니다.
 */
public class SingleResponsibility {
	
	//책임을 두 가지 가지고 있다.
	static class StringToXmlOrJson{
		final String data;

		public StringToXmlOrJson(String data) {
			this.data = data;
		}
		
		public String toXml() {
			return "<xml>"+data+"</xml>";
		}
		
		public String toJson() {
			return "{'data' : " + data +"}"; 
		}
	}
	
	
	//책임을 하나만 갖도록 클래스를 분리 했다.
	static class StringToXml{
		final String data;

		public StringToXml(String data) {
			this.data = data;
		}
		public String toXml() {
			return "<xml>"+data+"</xml>";
		}
	}
	static class StringToJson{
		final String data;
		
		public StringToJson(String data) {
			this.data = data;
		}
		public String toJson() {
			return "<xml>"+data+"</xml>";
		}
	}
	
	
	//더 가다듬기
	//인터페이스로 추상화했다.
	static interface StringConverter{
		String converting();
	}
	
	static class XmlConverter implements StringConverter {
		final String data;
		
		public XmlConverter(String data) {
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
			return "<xml>"+data+"</xml>";
		}
	}
	
}
