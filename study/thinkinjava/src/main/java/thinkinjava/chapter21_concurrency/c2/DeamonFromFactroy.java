package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述： 定义一个线程类，将创建新的线程加入到定义了线程工程的线程管理器,由线程工厂产生现场，交由现场管理器进行管理。
 * @author NICK
 *
 */
public class DeamonFromFactroy implements Runnable{
	

	public void run() {
		while(true){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(  Thread.currentThread()  + "," + this);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
//		//1、根据线程工厂类，创建一个线程池
//		ExecutorService exec = Executors.newCachedThreadPool( new thinkinjava.chapter21_concurrency.c2.DeamonThreadFactory() );
//
//		//2、添加线程，线程将定制的工厂类产生
//		for ( int i=0; i<5; i++ )
//			exec.execute( new DeamonFromFactroy() );
//
//		//3、主线程睡眠，让后台线程运行
//		try {
//			TimeUnit.MILLISECONDS.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
