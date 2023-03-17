package designpattern.observer.practise;

import java.util.ArrayList;
import java.util.List;

public class ObserverDriver {
	public static void main(String[] args) {
		
	}
	
	static interface PullingChannel{
		void update();
	}
	
	static class NewsPullingChannel implements PullingChannel{
		private final NewsAgency<?, ?> agnecy;
		String news;
		
		public NewsPullingChannel(NewsAgency<?, ?> agnecy) {
			this.agnecy = agnecy;
		}

		@Override
		public void update() {
			this.news = this.agnecy.getNews();
		}
		
	}
	
	
	
	static interface Channel {
		void update(String news);
	}
	static class NewsChannel implements Channel{
		private String news;
		@Override
		public void update(String news) {
			setNews(news);
		}
		public String getNews() {
			return news;
		}
		public void setNews(String news) {
			this.news = news;
		}
		
	}
	
	static class NewsAgency<C extends Channel,P extends PullingChannel>{
		private String news;
		private List<C> channels = new ArrayList<>();
		private List<P> pullingChannels = new ArrayList<>();
		
		public void setNews(String news) {
			this.news = news;
			nofifyObserver();
			nofifyObserver2();
		}
		
		private void nofifyObserver2() {
			for(P channel : pullingChannels) {
				channel.update();
			}
		}

		public String getNews() {
			return news;
		}

		private void nofifyObserver() {
			for(C channel : channels) {
				channel.update(this.news);
			}
		}
		
		public void addObserver(C channel) {
			this.channels.add(channel);
		}
		
		public void removeObserver(C channel) {
			this.channels.remove(channel);
		}
	}
}
