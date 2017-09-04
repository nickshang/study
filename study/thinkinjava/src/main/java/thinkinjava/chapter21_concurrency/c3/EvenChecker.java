package thinkinjava.chapter21_concurrency.c3;

import java.util.concurrent.ExecutorService;

/**
 * 功能描述：消费者任务，检查生产者偶数的有效性
 * @author Nick
 *
 */
public class EvenChecker implements Runnable {
	
	//生产者
	private thinkinjava.chapter21_concurrency.c3.IntGenerator intGenerator;
	
	//线程ID
	private final int id;
	
	/**
	 * 现场构造函数
	 * @param intGenerator
	 * @param id
	 */
	public EvenChecker(thinkinjava.chapter21_concurrency.c3.IntGenerator intGenerator , int id){
		this.intGenerator = intGenerator;
		this.id = id;
	}

	/**
	 * 现场任务
	 */
	public void run() {
		//检查偶数的有效性
		while( !intGenerator.isCanceled() ){
			int i = intGenerator.next();
			if (i % 2 != 0){
				System.out.println( "i:" + i + ",不是偶数 ,退出!" );
				intGenerator.cancel();
			}
		}
	}
	
	/**
	 * 
	 * @param
	 * @param count
	 */
	public static void test(thinkinjava.chapter21_concurrency.c3.IntGenerator gp, int count){
		System.out.println( "press control + c to exit;" );
		ExecutorService exec = java.util.concurrent.Executors.newCachedThreadPool();
		for ( int i = 0; i < count; i++ ) {
			exec.execute( new EvenChecker(gp ,count ) );
		}
	}

	public static void test(thinkinjava.chapter21_concurrency.c3.IntGenerator gp){
		test(gp, 10);
	}
}
