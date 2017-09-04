package thinkinjava.chapter21_concurrency.c2;

import thinkinjava.chapter21_concurrency.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * ¹¦ÄÜÃèÊö£º²âÊÔÐÝÃß
 * @author NICK
 *
 */
public class SleepingTask  extends LiftOff{

	public void run(){
		try{
			while ( countDown-- > 0 ){
				System.out.println(status());
				TimeUnit.MILLISECONDS.sleep(110);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args ) {
		ExecutorService   exectorService = java.util.concurrent.Executors.newCachedThreadPool();
		for (int i = 0; i<10; i++){
			exectorService.execute( new SleepingTask() );
		}
		exectorService.shutdown();
	}

}
