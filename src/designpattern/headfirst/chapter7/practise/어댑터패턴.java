package designpattern.headfirst.chapter7.practise;

public class 어댑터패턴 {
	static interface Adaptee {
		void 적응이필요해();
	}
	static class AdapteeClass implements Adaptee{
		public void 적응이필요해() {
			System.out.println("적응이 필요한 코드 뭉치");
		}
	}
	//타겟 인터페이스
	static interface Origin{
		void 원본코드();
	}
	static class OriginClass implements Origin{
		public void 원본코드() {
			System.out.println("원본 코드");
		}
	}
	//핵심
	static class OriginAdapter implements Origin{
		Adaptee adaptee; // 어댑티 객체 인스턴스가 있다.
		
		public OriginAdapter(Adaptee adaptee) {
			this.adaptee = adaptee;
		}
		@Override
		public void 원본코드() {
			adaptee.적응이필요해();
		}
	}
	public static void main(String[] args) {
		Origin origin = new OriginClass();
		Adaptee adaptee = new AdapteeClass();
		Origin originAdapter = new OriginAdapter(adaptee);
		action(origin);
		action(originAdapter);
	}
	//기존에 클라이언트가 사용하던 원본 코드는 변경이 없다.
	static void action(Origin origin) {
		origin.원본코드();
	}
}
