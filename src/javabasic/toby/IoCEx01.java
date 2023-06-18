package javabasic.toby;

import java.util.HashMap;
import java.util.Map;

public class IoCEx01 {
	public static void main(String[] args) {
		new Client1().main();
		new Client2().main();
	}

	static class IoC {
		static Map<String, Service> map = new HashMap<>();
		
		//xml, java, annotation 같은 설정파일이라고 가정
		static {
			//클라이언트 코드 변경 없이 다른 컴포넌트를 주입할 수 있다.
//			map.put("service", new Service1Impl());
			map.put("service", new Service2Impl());
		}
		
		static Service getBean(String name){
			return map.get(name);
		}
		
	}
	
	//클라이언트라고 가정
	static class Client1{
		void main() {
			Service1 service1 = new Service1();
			Service2 service2 = new Service2();
			execute(service1);
			//로직을 대체해야하면 클라이언트는 기존 코드를 변경해야한다.
//			execute(service2);
		}
		void execute(Service1 service) {
			service.doSomething1("로직 수행...");
		}
	}
	
	static class Service1{
		void doSomething1(Object data) {
			/*무언가 로직을 수행하는 코드*/
			System.out.println(data);
		}
	}
	static class Service2{
		void doSomething2(Object data) {
			/*대체돼야할 로직 */
			System.out.println(data);
		}
	}
	
	static class Client2{
		void main() {
			Service service = IoC.getBean("service");
			execute(service); 
		}
		void execute(Service service) {
			service.doSomething("서로 다름");
		}
	}
	
	static interface Service{
		void doSomething(Object data);
	}
	static class Service1Impl implements Service{
		public void doSomething(Object data) {
			/*무언가 로직을 수행하는 코드*/
		}
	}
	static class Service2Impl implements Service{
		public void doSomething(Object data) {
			/*무언가 로직을 수행하는 코드*/
		}
	}
}
