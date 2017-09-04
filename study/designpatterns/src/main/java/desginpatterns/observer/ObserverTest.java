package desginpatterns.observer;

/**
 * 功能描述： 观察着测试
 * @author NICK
 *
 */
public class ObserverTest {
	public static void main(String[] args) {
		
		// 可观察着
		WeatherData weatherData = new WeatherData();
		
		// 观察着
		CurrentConditionsDisplay display = new CurrentConditionsDisplay(weatherData);
		
		// 更新->通知
		weatherData.setMeasurements(0.1f, 0.1f, 0.1f);
		
		// 更新->通知
		weatherData.setMeasurements(0.2f, 0.2f, 0.2f);
		
		// 删除观察着
		weatherData.deleteObserver(display);
		
		// 更新->通知
		weatherData.setMeasurements(0.3f, 0.3f, 0.3f);
		
	}
}
