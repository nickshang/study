package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * 功能描述：使用Thread.UncaughtExceptionHandler实现接口捕获异常
 * @author Think
 *
 */
public class CaptureCaughtException implements Runnable {


	/**
	 * 线程任务
	 */
	public void run() {
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		
		ExecutorService exec = java.util.concurrent.Executors.newCachedThreadPool( new CaughtExceptionFactroy() );
		exec.execute( new CaptureCaughtException() );
	}
	
}


/**
 * 线程捕获异常类
 * @author Nick
 *
 */
  class CaughtException implements Thread.UncaughtExceptionHandler{
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(" 线程捕获异常类: caught exception. ");
		e.printStackTrace();
	}
}


/**
 * 线程捕获跟踪工厂类
 * @author NICK
 *
 */
 class CaughtExceptionFactroy implements ThreadFactory{

	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler( new CaughtException() );
		return t;
	}
	
}
