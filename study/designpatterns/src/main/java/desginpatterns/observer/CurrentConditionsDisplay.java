package desginpatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 功能描述：观察着实现
 * @author NICK
 *
 */
public class CurrentConditionsDisplay implements Observer ,DisplayElment {
	
	private float temperature;
	
	private float humidity;
	
	private float pressure;
	
	
	/**
	 * 功能描述：可观察着
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
