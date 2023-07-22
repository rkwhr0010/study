package designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/*
 *옵서버 패턴은 당신이 여러 객체에 자신이 관찰 중인 객체에 발생하는 
 *모든 이벤트에 대하여 알리는 구독 메커니즘을 정의할 수 있도록 하는 행동 디자인 패턴입니다. 
 *출판-구독 패턴의 원시 형태
 *
 *#구성요소
 * subject(publisher)
 * 핵심 관심사
 * 다른 객체들에 관심 이벤트들을 발행합니다. 이러한 이벤트들은 출판사가 상태를 전환하거나 어떤 행동들을 실행할 때 발생합니다. 
 * 출판사들에는 구독 인프라가 포함되어 있으며, 이 인프라는 현재 구독자들이 리스트를 떠나고 새 구독자들이 리스트에 가입할 수 있도록 합니다.
 * 
 * subscriber
 * subject에 관심이 있는 자
 * 이 구독자 인터페이스는 알림 인터페이스를 선언하며 대부분의 경우 단일 update 메서드로 구성됩니다. 
 * 이 메서드에는 출판사가 업데이트와 함께 어떤 이벤트의 세부 정보들을 전달할 수 있도록 하는 여러 매개변수가 있을 수 있습니다.
 * 
 * concreteSubscriber
 * 구상 구독자들은 출판사가 보낸 알림들에 대한 응답으로 몇 가지 작업을 수행합니다. 
 * 이러한 모든 클래스는 출판사가 구상 클래스들과 결합하지 않도록 같은 인터페이스를 구현해야 합니다.
 * 
 * Client
 * 클라이언트는 출판사 및 구독자 객체들을 별도로 생성한 후 구독자들을 출판사 업데이트에 등록합니다.
 * 
 * 
 * #적용
 * 클라이언트는 출판사 및 구독자 객체들을 별도로 생성한 후 구독자들을 출판사 업데이트에 등록합니다.
 * 이 패턴은 앱의 일부 객체들이 제한된 시간 동안 또는 특정 경우에만 다른 객체들을 관찰해야 할 때 사용하세요.
 */
public class 구체화된예시 {
	public static void main(String[] args) {
		Subject<String> youtuber1 = new Youtuber<>();		
		Subject<String> youtuber2 = new PullYoutuber<>();		
		
		Observer<String> Subscriber1 = new Subscriber<>("옥희");
		Observer<String> Subscriber2 = new Subscriber<>("철수");
		Observer<String> Subscriber3 = new Subscriber<>("앙드레");
		Observer<String> Subscriber4 = new Subscriber<>("권호");
		
		youtuber1.subscribe(Subscriber1);
		youtuber1.subscribe(Subscriber2);
		youtuber1.subscribe(Subscriber3);
		youtuber2.subscribe(Subscriber3);
		youtuber2.subscribe(Subscriber4);
		
		youtuber1.setData("정치 동영상");
		youtuber2.setData("예능 동영상");
		
	}
	
}
//Subject
interface Subject<T>{
	void subscribe(Observer<T> observer);
	void remove(Observer<T> observer);
	void pull();
	void push();
	void setData(T data);
	T getData();
}
//Obsever
interface Observer<T>{
	void update(T data);
	void update(Subject<T> subject);
}

class Youtuber<T> implements Subject<T>{
	protected T lastVideo;
	private List<Observer<T>> observers = new ArrayList<>();
	
	@Override
	public void subscribe(Observer<T> observer) {
		observers.add(observer);
	}

	@Override
	public void remove(Observer<T> observer) {
		observers.remove(observer);
	}
	@Override
	public void push() {
		for(Observer<T> ob : observers) {
			ob.update(lastVideo);
		}
	}
	@Override
	public void pull() {
		for(Observer<T> ob : observers) {
			ob.update(this);
		}
	}
	//기본 동작은 푸시
	public void setData(T data) {
		System.out.println(data + " 푸시 방식으로 보내기");
		this.lastVideo = data;
		push();
	}
	public T getData() {
		return lastVideo;
	}
}
//풀방식
class PullYoutuber<T> extends Youtuber<T>{
	@Override
	public void setData(T data) {
		System.out.println(data + " 풀 방식으로 보내기");
		super.lastVideo = data;
		pull();
	}
}


class Subscriber<T> implements Observer<T>{
	T data;
	final String name;
	
	public Subscriber(String name) {
		this.name = name;
	}
	public void update(T data) {
		System.out.println("\t"+name+" 푸시 방식으로 데이터를 받았습니다.");
		this.data = data;
	}
	public void update(Subject<T> subject) {
		System.out.println("\t"+name+" 풀 방식으로 데이터를 받았습니다.");
		this.data = subject.getData();
	}
}


