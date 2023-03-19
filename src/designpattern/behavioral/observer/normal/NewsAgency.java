package designpattern.behavioral.observer.normal;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
	//관찰 대상 핵심 데이터(상태), 리스트일수도.. 클래스일 수도....
	private String news;
	//구독자
	private List<Channel> channels = new ArrayList<>();
	
	//구독
	public void addObserver(Channel channel) {
		channels.add(channel);
	}
	//구독 재지
	public void removeObserver(Channel channel) {
		channels.remove(channel);
	}
	//상태값 변경
	public void setNews(String news) {
		this.news = news;
		for(Channel channel : channels) {
			channel.update(this.news);
		}
	}
	
}
