package thinkinjava.chapter21_concurrency.c6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：死锁线程
 * @author NICK
 *
 */
public class DeadlockingDiningPhilosophers {

	public static void main(String[] args) throws InterruptedException {
		
		int size = 2;
		
		//新建5根筷子
		Chopstick[] chopsticks = new Chopstick[size];
		for (int i = 0; i < size; i++ )
			chopsticks[i] = new Chopstick();
		
		//新建5个哲学家
		Philosopher[] philosophers = new Philosopher[size];
		for (int i = 0; i < size; i++ )
			philosophers[i] = new Philosopher( chopsticks[i], chopsticks[(i+1)%size], i, 20 );
	
		//新建线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < size; i++ )
			exec.execute( philosophers[i] );
		
		//关闭线程池
		TimeUnit.SECONDS.sleep(10000);
		exec.shutdownNow();
			
	}
}
