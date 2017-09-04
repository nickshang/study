package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：简单的后台线程（deamon）的实现
 * @author NICK
 *
 */
public class SimpleDeamons  implements Runnable{

	//设置一个一直循环的任务，间隔1秒睡眠一次,一直打印当前时间
	public void run() {
		while ( true){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("thrad interupted");
			}
			
			System.out.println( java.util.Calendar.getInstance().toString());
		}
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread( new SimpleDeamons());
		
		thread.setDaemon(true);    //后台进程必须设置:setDaemon(true),然后必须调用start();
		
		thread.start();
		
		System.out.println( "main thread start" );
		try {
			TimeUnit.SECONDS.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println( "main thread end" );
	}
}
