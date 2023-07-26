package javabasic.solid;
/*
 * 단일 책임 원칙
 * 이 원칙에 따르면 클래스는 하나의 책임만 져야 합니다. 
 * 또한 변경해야 하는 이유는 단 하나여야 합니다.
 * 
 * 클래스는 자신의 이름이 나타내는 일을 해야 합니다. 
 * 올바른 클래스 이름은 해당 클래스의 책임을 나타낼 수 있는 가장 좋은 방법입니다.
 * 각 클래스는 하나의 개념을 나타내어야 합니다.
 * 사용되지 않는 속성이 결정적 증거입니다.
 * 무조건 책임을 분리한다고 SRP가 적용되는 건 아닙니다. 
 * 각 개체 간의 응집력이 있다면 병합이 순 작용의 수단이 되고 
 * 결합력이 있다면 분리가 순 작용의 수단이 됩니다.
 * 
 * #적은 책임 이점
 * 단위가 작아 테스트가 용이하다
 * 재사용성이 증가된다.
 * 낮은 결합을 유지하게 된다.
 * 재사용성+낮은 결합의 결과로 조합성이 높아진다.
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
			return "{'data' : " + data +"}";
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
			return "{'data' : " + data +"}";
		}
	}
	
}
