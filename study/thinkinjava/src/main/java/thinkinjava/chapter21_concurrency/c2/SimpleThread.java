package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：练习编码的变体，直接Thread类继承实现多线程
 * @author NICK
 *
 */
public class SimpleThread  extends  Thread {
	
	//显示线程计算状态
	private int countDown = 5;
	
	//线程名称
	private static int threadName = 0;

	//构造函数
	public SimpleThread(int i ){
		super( Integer.toString(  threadName++ ));
		start();
	}
	
	@Override
	public void run() {
		while( countDown-- > 0 ){
			System.out.println( "#Thread name: " + Thread.currentThread().getName() + " ，计算状态:" + countDown  );
			
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for ( int i = 0; i < 5; i ++ ){
			Thread t = new Thread( new SimpleThread(i) );
		}
	}
	
}
