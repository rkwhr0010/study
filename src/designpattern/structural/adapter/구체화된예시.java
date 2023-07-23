package designpattern.structural.adapter;
/* 어댑터는 호환되지 않는 인터페이스를 가진 객체들이 협업할 수 있도록 하는 구조적 디자인 패턴입니다.
 * 
 * 어댑터는 변환의 복잡성을 숨기기 위하여 객체 중 하나를 래핑​(포장)​합니다. 
 * 래핑된 객체는 어댑터를 인식하지도 못합니다. 
 * 
 * 클래스 어댑터와 객체 어댑터가 있습니다.
 * 일반적으로 객체 어댑터가 선호됩니다.
 * 
 * 
 * #구성요소
 * Origin Interface
 * 원래 사용하던 인페이스
 * 
 * Adapter
 * Origin Interface를 구현해
 * 구현 메서드를 Adaptee에게 위임한다.
 * 단순 위임 전 로직이 들어가기도 한다.
 * 
 * Adaptee
 * 적응 시켜야할 인터페이스
 * 외부 라이브러리나 레거시 코드 등...
 * 
 */
public class 구체화된예시 {
	public static void main(String[] args) {
		client(new XMLParserImpl());
		client(new JSONAdapter(new JSONParserImpl()));
		
	}
	//기존 클라이언트 코드 변경이 없다.
	static void client(XMLParser parser) {
		XMLParser xmlParserImpl = parser;
		System.out.println(xmlParserImpl.xmlParsing("<XML>"));;
		
	}
	
}

//Origin Interface
interface XMLParser {
	String xmlParsing(String xml);
}
class XMLParserImpl implements XMLParser {
	public String xmlParsing(String xml) {
		return xml;
	}
}
//Adaptee
interface JSONParser{
	String jsonParsing(String json);
}
class JSONParserImpl implements JSONParser{
	public String jsonParsing(String json) {
		return json+" {JSON}";
	}
}

class JSONAdapter implements XMLParser{
	final JSONParser jsonParser;
	
	public JSONAdapter(JSONParser jsonParser) {
		this.jsonParser = jsonParser;
	}
	public String xmlParsing(String xml) {
		return jsonParser.jsonParsing(xmlToJsonConvert(xml));
	}
	
	private String xmlToJsonConvert(String xml) {
		return xml + "===> 변환 ===>";
	}
}
