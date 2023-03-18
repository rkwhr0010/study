package designpattern.observer.pulling;

public class NewsPulling implements Pulling {
	private final NewsAgency agency;
	private String news;

	public NewsPulling(NewsAgency agency) {
		this.agency = agency;
	}

	@Override
	public void update() {
		setNews(this.agency.getNews());
		System.out.println("어떠한 조건에 만족해 값을 가져옮 ... "+this.getNews());
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

}
