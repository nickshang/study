package desginpatterns.observer;

/**
 * �����������ɹ۲���ʵ�֣� ѧϰ���ģʽ���۲���ģʽ����java.
 * @author NICK
 *
 */
public class WeatherData extends java.util.Observable{
	
	private float temperature;
	
	private float humidity;
	
	private float pressure;
	
	public WeatherData() { }

	public void measurementsChanged(){
		setChanged();
		notifyObservers();
	}
	
	public void setMeasurements(float temperature,float humidity,float pressure){
		this.temperature = temperature;
		this.humidity  = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
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
}
