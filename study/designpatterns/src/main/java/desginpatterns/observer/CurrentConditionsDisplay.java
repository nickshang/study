package desginpatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * �����������۲���ʵ��
 * @author NICK
 *
 */
public class CurrentConditionsDisplay implements Observer ,DisplayElment {
	
	private float temperature;
	
	private float humidity;
	
	private float pressure;
	
	
	/**
	 * �����������ɹ۲���
	 */
	private Observable observable ;
	
	public CurrentConditionsDisplay(Observable observable){
		this.observable = observable;
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		if ( obs instanceof WeatherData){
			WeatherData weatherData = (WeatherData)obs;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			this.pressure = weatherData.getPressure();
			display();
		}
	}
	
	
	public void display(){
		System.out.println( "temperature:" + temperature +", humidity:" + humidity +", pressure:" + pressure );
	}
	

}
