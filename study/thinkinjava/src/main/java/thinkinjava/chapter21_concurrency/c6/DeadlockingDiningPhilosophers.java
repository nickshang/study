package thinkinjava.chapter21_concurrency.c6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ���������������߳�
 * @author NICK
 *
 */
public class DeadlockingDiningPhilosophers {

	public static void main(String[] args) throws InterruptedException {
		
		int size = 2;
		
		//�½�5������
		Chopstick[] chopsticks = new Chopstick[size];
		for (int i = 0; i < size; i++ )
			chopsticks[i] = new Chopstick();
		
		//�½�5����ѧ��
		Philosopher[] philosophers = new Philosopher[size];
		for (int i = 0; i < size; i++ )
			philosophers[i] = new Philosopher( chopsticks[i], chopsticks[(i+1)%size], i, 20 );
	
		//�½��̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < size; i++ )
			exec.execute( philosophers[i] );
		
		//�ر��̳߳�
		TimeUnit.SECONDS.sleep(10000);
		exec.shutdownNow();
			
	}
}
