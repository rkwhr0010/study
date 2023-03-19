package designpattern.behavioral.observer.practise;

import java.util.Observable;
import java.util.Observer;


@SuppressWarnings("deprecation")
public class ObserverDriver {
	public static void main(String[] args) {
		NewsAgency<Boolean> newsAgency = new NewsAgency<>();
//		newsAgency.addObserver(new NewsChannel<String>());
//		newsAgency.addObserver(new NewsChannel<String>());
		NewsChannel<Integer> newsChannel = new NewsChannel<Integer>();
		NewsChannel<String> newsChannel2 = new NewsChannel<String>();
		newsChannel.setNews(123123);
		newsChannel2.setNews("하나둘셋");
		newsAgency.addObserver(newsChannel);
		newsAgency.addObserver(newsChannel2);
		newsAgency.setNews(true);
		
		
		System.out.println(newsChannel.getNews()+"@" +newsChannel.hashCode());
		System.out.println(newsChannel2.getNews()+"@" +newsChannel.hashCode());
		
		
	}
	
	static class NewsChannel<T> implements Observer{
		private T news;
		
		@SuppressWarnings("unchecked")
		@Override
		public void update(Observable o, Object news) {
			this.news = (T) news;
			System.out.println(this.news+"  "+this.hashCode());
		}
		
		public T getNews() {return news;}
		public void setNews(T news) {this.news = news;}
	}
	
	static class NewsAgency<T> extends Observable{
		private T news;
		
		public void setNews(T news) {
			this.news = news;
			super.setChanged();
			super.notifyObservers(news);
		}
	}
}
