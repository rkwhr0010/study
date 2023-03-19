package designpattern.behavioral.observer.normal;

/**
관찰자는 행동 디자인 패턴입니다. 
관찰 가능 개체와 관찰자 개체 간의 통신을 지정합니다. 
Observable은 관찰자에게 상태의 변화를 알리는 객체입니다.
예를 들어 뉴스 대행사는 뉴스를 수신할 때 채널에 알릴 수 있습니다. 뉴스 수신은 뉴스 에이전시의 상태를 변경하고 채널에 알림을 발생시킵니다.
직접 구현하는 방법을 살펴보겠습니다.
 */
public class ObserverDriver {
	public static void main(String[] args) {
		NewsAgency agency = new NewsAgency();
		Channel observer = new NewsChannel();
		Channel observer2 = new EduChannel();
		
		agency.addObserver(observer);
		agency.addObserver(observer2);
		
		agency.setNews("속보...블라블라....");
		
		
		
		
		
	}
}
