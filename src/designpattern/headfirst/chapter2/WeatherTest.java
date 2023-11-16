package designpattern.headfirst.chapter2;

public class WeatherTest {

	static class WeatherData {

		private float temp;
		private float humidity;
		private float pressure;

		public float getTemp() {
			return temp;
		}

		public void setTemp(float temp) {
			this.temp = temp;
		}

		public float getHumidity() {
			return humidity;
		}

		public void setHumidity(float humidity) {
			this.humidity = humidity;
		}

		public float getPressure() {
			return pressure;
		}

		public void setPressure(float pressure) {
			this.pressure = pressure;
		}

		public void measurementsChanged() {
			float temp = getTemp();
			float humidity = getHumidity();
			float pressure = getPressure();
			
		}
	}

}
