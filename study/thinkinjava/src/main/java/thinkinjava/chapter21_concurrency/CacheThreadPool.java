package thinkinjava.chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：利用java.util.concurrent.Executor，实现线程的管理
 * @author NICK
 *
 */
public class CacheThreadPool {
	
	public static void main(String[] args) {
		ExecutorService  executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++){
			executor.execute( new LiftOff() );
		}
		executor.shutdown();
	}
}
