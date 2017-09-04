package thinkinjava.chapter21_concurrency;

import java.util.concurrent.ExecutorService;

/**
 * ����������ʹ��SingleThreadExecutor�����߳�
 * @author NICK
 *
 */
public class SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService exec = java.util.concurrent.Executors.newSingleThreadExecutor();
		for ( int i = 0; i < 5; i++){
			exec.execute( new LiftOff() );
		}
		exec.shutdown();
		
	}
}
