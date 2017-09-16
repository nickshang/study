package thinkinjava.chapter21_concurrency.c2;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：使用Thread.UncaughtExcepion捕获异常
 * @author Think
 *
 */
public class UcaughtExceptionThread  implements Runnable{
	
	
	
	
	public void run() {
		Thread.currentThread().setUncaughtExceptionHandler( new UcaughtExceptionThreadO() );
		
		throw new RuntimeException( " execpton !!");
	}

	private class UcaughtExceptionThreadO implements UncaughtExceptionHandler{

		public void uncaughtException(Thread t, Throwable e) {
			System.out.println( "使用UncaughtExceptionHandler接口实现类捕获异常！" );
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute( new  UcaughtExceptionThread() );
	}
	
	
}
