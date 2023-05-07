package designpattern.structural.adapter;

public class AdapterEx {
	//원래 사용중인 인터페이스
	static interface XmlExport{
		void export();
	}
	static class XmlExportImpl implements XmlExport{
		public void export() {
			System.out.println("XML 익스포트");
		}
	}
	
	//외부 라이브러리
	static class ExteriorJSONExport{
		public void toJSON() {
			System.out.println("JSON 익스포트");
		}
	}
	//외부 라이브러리 어댑터
	static class JSONAdapter implements XmlExport{
		ExteriorJSONExport export;
		
		public JSONAdapter(ExteriorJSONExport export) {
			this.export = export;
		}
		public void export() {
			export.toJSON();
		}
	}
	
	//클라이언트
	public static void main(String[] args) {
		XmlExport xmlExport = new XmlExportImpl();
		XmlExport jsonExport = new JSONAdapter(new ExteriorJSONExport());
		xmlExport.export();
		jsonExport.export();
	}
}