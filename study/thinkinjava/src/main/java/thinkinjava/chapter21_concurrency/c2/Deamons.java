package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.TimeUnit;


/**
 * 功能描述：测试当主线程设置为后台线程后，主线程派生的线程也是后台进程
 * @author Nick
 *
 */
public class Deamons  {
	
	public static void main(String[] args) {
		Thread t = new Thread( new Deamon() );
		t.setDaemon(true);
		t.start();
		
		try {
			TimeUnit.MICROSECONDS.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Deamon implements  Runnable {
	
	public void run() {
		Thread[] ts = new Thread[10];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread( new DeamonSpawn() );
			ts[i].start();
			System.out.println( ts[i] + ": start "   );
		}
		
		for (int i = 0; i < ts.length; i++) {
			System.out.println( "[" + i + "]" + " isDeamon: " + ts[i].isDaemon() );
		}
		
		while ( true )
			Thread.yield();
	}
}

class DeamonSpawn implements Runnable{
	public void run() {
		while (true) 
			Thread.yield();
	}
}