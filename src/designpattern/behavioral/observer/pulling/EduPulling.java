package designpattern.behavioral.observer.pulling;

public class EduPulling implements Pulling {
	@SuppressWarnings("unused")
	private final NewsAgency agency;
	private String news;
	
	public EduPulling(NewsAgency agency) {
		this.agency = agency;
	}

	@Override
	public void update() {
		System.out.println("어떠한 조건에 만족하지 않아 값을 가져오지 않음");
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
//		agency.getNews();
	}
	

}
