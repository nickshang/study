package thinkinjava.chapter21_concurrency;

import java.util.concurrent.ExecutorService;


/**
 * 功能描述：使用FixedThreadPool管理线程
 * @author NICK
 *
 */
public class FixedThreadPool {
	public static void main(String[] args) {
		ExecutorService  exec = java.util.concurrent.Executors.newFixedThreadPool(5);
		for (int i = 0 ; i < 6; i++){
			exec.execute( new LiftOff() );
		}
		exec.shutdown();
	}
}
