package designpattern.behavioral.observer.pulling;

public class ObserverDriver {
	public static void main(String[] args) {
		NewsAgency newsAgency = new NewsAgency();
		newsAgency.addObserver(()->{});
		newsAgency.addObserver(new EduPulling(newsAgency));
		newsAgency.addObserver(new NewsPulling(newsAgency));
		
		newsAgency.setNews("긴급속보");
		
	}
}
