package thinkinjava.chapter21_concurrency.c7;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：使用ScheduledExecutor的温室控制器
 * @author NICK
 *
 */
public class GreenhouseScheduler {
	
	//灯状态
	@SuppressWarnings("unused")
	private volatile boolean light = false;
	
	//水状态
	@SuppressWarnings("unused")
	private volatile boolean water = false;
	
	//恒温控制器状态
	private String thermostat = "Day";
	
	/**
	 * 获取恒温控制器状态
	 * @return
	 */
	public synchronized String getThermostat(){
		return thermostat;
	}
	
	/**
	 * 设置thermostat状态
	 * @param value
	 */
	public synchronized void setThermostat(String value) {
		this.thermostat = value;
	}
	
	//线程安排管理器
	ScheduledThreadPoolExecutor scheduler = 
		new ScheduledThreadPoolExecutor(10);
	
	/**
	 * 功能描述：安排一次性任务
	 * @param event 线程任务
	 * @param delay 延迟时间
	 */
	public void schedule(Runnable event, long delay){
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 功能描述：安排重复性任务
	 * @param event 任务
	 * @param initalDelay 初始化延迟时间
	 * @param period 周期次数
	 */
	public void repeat(Runnable event, long initalDelay, long period) {
		scheduler.scheduleAtFixedRate(event, initalDelay, period, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 功能描述：开灯任务线程
	 * @author NICK
	 *
	 */
	class LightOn implements Runnable{
		public void run(){
			System.out.println("Turning on lights");
			light = true;
		}
	}
	
	/**
	 * 功能描述：关灯任务线程
	 * @author NICK
	 *
	 */
	class LightOff implements Runnable{
		public void run(){
			System.out.println("Turning off lights");
			light = false;
		}
	}
	
	/**
	 * 功能描述:打开水任务线程
	 * @author NICK
	 *
	 */
	class WaterOn implements Runnable {
		@Override
		public void run() {
			System.out.println("Turning greenhouse water on");
			water = true;
		}
	}
	
	/**
	 * 功能描述:关闭水任务线程
	 * @author NICK
	 *
	 */
	class WaterOff implements Runnable {
		public void run(){
			System.out.println("Turning greenhouse water off");
			water = false;
		}
	}
	
	/**
	 * 功能描述：控制thermostat状态Night 线程
	 * @author NICK
	 *
	 */
	class ThermostatNight implements Runnable {
		@Override
		public void run() {
			System.out.println("Thermostat to night setting");
			setThermostat("Night");
		}
	}
	
	/**
	 * 功能描述：控制Thermostat 状态Day 线程
	 * @author NICK
	 *
	 */
	class ThermostatDay implements Runnable {
		@Override
		public void run() {
			System.out.println("Thermostat to day setting");
			setThermostat("Day");
		}
	}
	
	/**
	 * 功能描述：
	 * @author Think
	 *
	 */
	class Bell implements Runnable{
		@Override
		public void run() {
			System.out.println("Bing!");
		}
	}
	
	/**
	 * 功能描述：终止线程
	 * @author NICK
	 *
	 */
	class Terminate implements Runnable{

		@Override
		public void run() {
			System.out.println("Terminate");
			scheduler.shutdownNow();
			new Thread(){
				public void run(){
					for(DataPoint d : data ){
						System.out.println(d);
					}
				}
			}.start();
		}
	}
	
	/**
	 * 功能描述：DATA数据
	 * @author Think
	 *
	 */
	static class DataPoint {
		
		final Calendar time ;
		final float temperature;
		final float humidity;
		public DataPoint(Calendar d, float temp, float hum){
			time  = d;
			temperature = temp;
			humidity = hum;
		}
		@Override
		public String toString() {
			return time.getTime() + String.format(" temperature:%1$.1f humidity: %2$.2f", temperature,humidity);
		}
	}
	
	private Calendar lastTime = Calendar.getInstance();
	{
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}
	
	private float lastTemp = 65.0f;
	private int tempDirection = +1;
	private float lastHumidity = 50.0f;
	private int humidityDirection =  +1;
	private Random rand = new Random(47);
	
	List<DataPoint> data = java.util.Collections.synchronizedList(
			new java.util.ArrayList<DataPoint>() );
	
	class CollectData implements Runnable {
		public void run(){
			System.out.println("Collect data");
			synchronized(GreenhouseScheduler.this){
				lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30 );
				
				if(rand.nextInt(5) == 4 )
					tempDirection = -tempDirection;
				
				lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
				
				if(rand.nextInt(5) == 4)
					humidityDirection = -humidityDirection;
				
				lastHumidity = lastHumidity + humidityDirection * rand.nextFloat();
				
				data.add( new DataPoint( (Calendar)lastTime.clone(),lastTemp,lastHumidity));
			}
		}
	}
	
	public static void main(String[] args){
		GreenhouseScheduler gh = new GreenhouseScheduler();
		gh.schedule(gh.new Terminate(), 5000);
		gh.repeat(gh.new Bell(), 0, 1000);
		gh.repeat(gh.new ThermostatNight(), 0, 2000);
		gh.repeat(gh.new LightOn(), 0, 200);
		gh.repeat(gh.new WaterOff(), 0, 400);
		gh.repeat(gh.new WaterOn(), 0, 600);
		gh.repeat(gh.new WaterOff(), 0, 800);
		
		gh.repeat(gh.new ThermostatDay(), 0, 1400);
		gh.repeat(gh.new CollectData(), 500, 500);
	}
}
