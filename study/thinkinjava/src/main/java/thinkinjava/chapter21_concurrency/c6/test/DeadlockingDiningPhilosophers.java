package thinkinjava.chapter21_concurrency.c6.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ���������������߳�
 * 
 * Change DeadlockingDiningPhilosophers.java so that when a
 * philosopher is done with their chopsticks, they drop them
 * into a bin. When a philosopher wants to eat, they take
 * the next two available chopsticks from the bin. Does this
 * eliminate the possibility of deadlock? Can you
 * reintroduce deadlock by simply reducing the number of
 * available chopsticks?
 * 
 * @author NICK
 *
 */
public class DeadlockingDiningPhilosophers {

	public static void main(String[] args) throws InterruptedException {
		
		int size = 2;
		
		
		//
		ChopstickQueue<Chopstick> chopstickQueue = new ChopstickQueue<Chopstick>();
		for (int i = 0; i < size; i++ ){
			chopstickQueue.put( new Chopstick() );
		}
		
		//�½�5����ѧ��
		Philosopher[] philosophers = new Philosopher[size];
		for (int i = 0; i < size; i++ )
			philosophers[i] = new Philosopher(chopstickQueue , i, 20 );
	
		//�½��̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < size; i++ )
			exec.execute( philosophers[i] );
		
		//�ر��̳߳�
		TimeUnit.SECONDS.sleep(10000);
		exec.shutdownNow();
			
	}
}
