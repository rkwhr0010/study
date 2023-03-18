package designpattern.observer.pulling;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
	private String news;
	private List<Pulling> list = new ArrayList<>();
	
	public void addObserver(Pulling pulling) {
		this.list.add(pulling);
	}
	
	public void removeObserver(Pulling pulling) {
		list.remove(pulling);
	}
	
	public void setNews(String news) {
		this.news = news;
		
		for(Pulling pulling : list) {
			pulling.update();
		}
	}
	
	public String getNews() {
		return this.news;
	}
}
