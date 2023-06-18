package javabasic.toby;

public class IoCEx01 {
	public static void main(String[] args) {
		new Client1().main();
		new Client2().main();
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
			Service service1Impl = new Service1Impl();
			Service service2Impl = new Service2Impl();
			execute(service1Impl); 
			//인터페이스에 맞춰 설계하면 클라이언트는 기존 코드를 변경할 필요 없다.(코드재사용)
			execute(service2Impl);
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
