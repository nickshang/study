package thinkinjava.chapter21_concurrency.c5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ���������������߳�Э��
 * 1������wait()��notify()��notifyAll()�������ڵ�����Щ����ǰ����"ӵ��"(��ȡ)���������
 * 2����������һ������ִ��ĳ�ֲ�����ά���Լ�������Ҫ��ô�����������ȵõ����������
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

//Ϳ������
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

//Ϳ�����
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
 * ����������������
 */
class Car{
	
	//�Ƿ���Ϳ����
	private boolean waxOn = false;
	
	/**
	 * Ϳ�����
	 */
	public synchronized void waxed(){
		waxOn = true;
		notifyAll();
	}
	
	/**
	 * �׹����
	 */
	public synchronized void buffed(){
		waxOn = false;  //Ready for another coat of wax
		notifyAll();
	}
	
	/**
	 * �ȴ�Ϳ�����
	 * ���Ϳ��δ��ɵ��÷��������񽫣�����
	 */ 
	public synchronized void waitForWaxing() throws InterruptedException{
		while (waxOn == false ){
			wait(); //���
		}
	}
	
	/**
	 * �ȴ��׹����
	 * �����Ϳ���е��ø÷��������񽫽��еȴ�������
	 */
	public synchronized void waitForBuffing() throws InterruptedException{
		while (waxOn == true) wait();
	}
}