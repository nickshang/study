package thinkinjava.chapter21_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ��������������java.util.concurrent.Executor��ʵ���̵߳Ĺ���
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
