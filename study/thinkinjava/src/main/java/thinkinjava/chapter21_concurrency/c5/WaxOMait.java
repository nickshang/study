package thinkinjava.chapter21_concurrency.c5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：测试线程协助
 * 1、调用wait()、notify()、notifyAll()的任务在调用这些方法前必须"拥有"(获取)对象的锁；
 * 2、可以人另一个对象执行某种操作以维护自己的锁。要这么做，必须首先得到对象的锁；
 * @author NICK
 *
 */
public class WaxOMait {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Car c = new Car();
		exec.execute( new WaxOn(c) );
		exec.execute( new WaxOff(c));
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exec.shutdownNow();
	}
}

//涂蜡任务
class WaxOn implements Runnable {
	
	private Car car;
	
	public WaxOn(Car c) { car = c; }
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println( "Wax on!" );
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		}catch(InterruptedException e) {
			System.out.println( "Exiting via interrupt ");
		}
		System.out.println( "Exiting Wax task ");
	}
}

//涂蜡完成
class WaxOff implements Runnable {
	private Car car;
	
	public WaxOff(Car c) { car = c; }
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				car.waitForWaxing();
				System.out.println("Wax off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		}catch(InterruptedException e){
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax Off task");
	}
}

/**
 * 功能描述：汽车类
 */
class Car{
	
	//是否在涂蜡中
	private boolean waxOn = false;
	
	/**
	 * 涂蜡完成
	 */
	public synchronized void waxed(){
		waxOn = true;
		notifyAll();
	}
	
	/**
	 * 抛光完成
	 */
	public synchronized void buffed(){
		waxOn = false;  //Ready for another coat of wax
		notifyAll();
	}
	
	/**
	 * 等待涂蜡完成
	 * 如果涂蜡未完成调用方法的任务将（挂起）
	 */ 
	public synchronized void waitForWaxing() throws InterruptedException{
		while (waxOn == false ){
			wait(); //如果
		}
	}
	
	/**
	 * 等待抛光完成
	 * 如果在涂蜡中调用该方法的任务将进行等待（挂起）
	 */
	public synchronized void waitForBuffing() throws InterruptedException{
		while (waxOn == true) wait();
	}
}