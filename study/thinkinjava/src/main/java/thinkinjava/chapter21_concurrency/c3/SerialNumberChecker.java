package thinkinjava.chapter21_concurrency.c3;

import java.util.concurrent.TimeUnit;

/**
 * �������������������࣬���̲߳���
 * @author NICK
 *
 */
public class SerialNumberChecker {

	//�߳�����
	private static final int SIZE = 10;
	
	//����Բ�㼯����
	private static CircularSet serials = new CircularSet(1000);
	
	//�̹߳�����
	private static java.util.concurrent.ExecutorService exec = 
		java.util.concurrent.Executors.newCachedThreadPool();
	
	//�߳���
	static class SerialChecker implements Runnable {
		public void run(){
			while(true){
				int serial = thinkinjava.chapter21_concurrency.c3.SerialNumberGenerator.nextSerialNumber();
				if( serials.contains( serial ) ) {
					System.out.println("[�ظ�����]Duplicate: " + serial );
					System.exit(0);
				}
				serials.add( serial );
			}
		}
	}
	
	//����
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		for ( int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker() );
			if( args.length > 0 ){
				TimeUnit.SECONDS.sleep(1);
				System.out.println("No duplicates detected");
				System.exit(0);
			}
		}
	}
}
