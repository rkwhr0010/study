package designpattern.headfirst.chapter2;

import java.util.ArrayList;
import java.util.List;

public class WheatherStationTest2 {
	static interface Subject{
		void registerObserver(Observer o);//등록
		void removeObserver(Observer o);  //삭제
		void notifyObservers();// 알림
	}
	
	@FunctionalInterface
	static interface Observer{
		void update();
	}
	
	static interface DisplayElement{
		void display();
	}
	
	
	static class WeatherData implements Subject{
		private List<Observer> observers;
		private float temperature;
		private float humidity;
		private float pressure;

		public WeatherData() {
			observers = new ArrayList<>();
		}
		public void registerObserver(Observer o) {
			observers.add(o);
		}
		public void removeObserver(Observer o) {
			observers.remove(o);
		}
		public void notifyObservers() {
			observers.forEach((ob)->ob.update());
		}
		public void measurementsChanged() {
			notifyObservers();
		}
		public void setMeasurements(float temperature, float humidity, float pressure) {
			this.temperature = temperature;
			this.humidity = humidity;
			this.pressure = pressure;
			measurementsChanged();
		}
		public float getTemperature() {
			return temperature;
		}
		public float getHumidity() {
			return humidity;
		}
		public float getPressure() {
			return pressure;
		}
	}
	
	static class CurrentConditionsDisplay implements Observer, DisplayElement{
		private float temperature;
		private float humidity;
		private WeatherData weatherData;

		public CurrentConditionsDisplay(WeatherData weatherData) {
			this.weatherData = weatherData;
			weatherData.registerObserver(this);
		}

		@Override
		public void update() {
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}

		@Override
		public void display() {
			System.out.println("현재 상태: 온도 " +temperature + "F, 습도 "+humidity + "%");
		}
		
	}
	static class StatisticsDisplay implements Observer, DisplayElement {
		private float maxTemp = 0.0f;
		private float minTemp = 200;
		private float tempSum= 0.0f;
		private int numReadings;
		private WeatherData weatherData;

		public StatisticsDisplay(WeatherData weatherData) {
			this.weatherData = weatherData;
			weatherData.registerObserver(this);
		}

		public void update() {
			float temp = weatherData.getTemperature();
			tempSum += temp;
			numReadings++;

			if (temp > maxTemp) {
				maxTemp = temp;
			}
	 
			if (temp < minTemp) {
				minTemp = temp;
			}

			display();
		}

		public void display() {
			System.out.println("Avg/Max/Min 온도 = " + (tempSum / numReadings)
				+ "/" + maxTemp + "/" + minTemp);
		}
	}
	static class ForecastDisplay implements Observer, DisplayElement {
		private float currentPressure = 29.92f;  
		private float lastPressure;
		private WeatherData weatherData;

		public ForecastDisplay(WeatherData weatherData) {
			this.weatherData = weatherData;
			weatherData.registerObserver(this);
		}

		public void update() {
	        lastPressure = currentPressure;
			currentPressure = weatherData.getPressure();

			display();
		}

		public void display() {
			System.out.print("기상 예보: ");
			if (currentPressure > lastPressure) {
				System.out.println("날씨가 좋아지고 있습니다.");
			} else if (currentPressure == lastPressure) {
				System.out.println("지금과 비슷합니다.");
			} else if (currentPressure < lastPressure) {
				System.out.println("쌀쌀하며 비가 올 것 같습니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
		
	}
	
}
