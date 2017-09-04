package desginpatterns.observer;

/**
 * ���������� �۲��Ų���
 * @author NICK
 *
 */
public class ObserverTest {
	public static void main(String[] args) {
		
		// �ɹ۲���
		WeatherData weatherData = new WeatherData();
		
		// �۲���
		CurrentConditionsDisplay display = new CurrentConditionsDisplay(weatherData);
		
		// ����->֪ͨ
		weatherData.setMeasurements(0.1f, 0.1f, 0.1f);
		
		// ����->֪ͨ
		weatherData.setMeasurements(0.2f, 0.2f, 0.2f);
		
		// ɾ���۲���
		weatherData.deleteObserver(display);
		
		// ����->֪ͨ
		weatherData.setMeasurements(0.3f, 0.3f, 0.3f);
		
	}
}
