package designpattern.observer.normal;

class NewsChannel implements Channel {
	private String news;

	@Override
	public void update(Object news) {
		setNews((String) news);
		System.out.println(this.getClass().getSimpleName() +":"+getNews());
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

}