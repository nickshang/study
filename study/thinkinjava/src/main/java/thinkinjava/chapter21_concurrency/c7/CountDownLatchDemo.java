package thinkinjava.chapter21_concurrency.c7;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：学习CountDownLatch
 * CountDownLatch用来同步一个或者多个任务的，强制他们等待由其他任务执行的一组操作完成
 * 
 * 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。 
 * 用给定的计数 初始化 CountDownLatch。由于调用了 countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。
 * 之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。这种现象只出现一次——计数无法被重置。如果需要重置计数，请考虑使用 CyclicBarrier。 
 * @author NICK
 *
 */
public class CountDownLatchDemo {
	
	public static void main(String[] args) {
		
		int size = 100;
		
		CountDownLatch countDownLatch = new CountDownLatch(size);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) 
			exec.execute( new WaitingTask(countDownLatch) );
		
		for (int i = 0; i < size; i++) 
			exec.execute( new TaskPortion(countDownLatch) );
		
		exec.shutdown();
		System.out.println( "主线程结束!" );
	}
	
}

/**
 * 功能描述：等待线程任务
 * @author NICK
 *
 */
class WaitingTask implements Runnable{

	private static int count;
	
	private final int id = count++ ;
	
	/**
	 * 线程阻塞管理对象
	 */
	private final CountDownLatch  countDownLatch;
	
	/**
	 * 
	 * Title: 初始化
	 * Description: 
	 * @param countDownLatch 
	 */
	public WaitingTask(CountDownLatch  countDownLatch){
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("等待线程结束:" + this );
	}
	

	public String toString(){
		return String.valueOf(id);
	}
}

/**
 * 功能描述：任务一部分
 * @author NICK
 *
 */
class TaskPortion implements Runnable{

	private static int counter = 0;
	
	/**
	 * 线程ID
	 */
	private final int id = counter++;
	
	/**
	 * 线程阻塞管理对象
	 */
	private  final CountDownLatch countDownLatch;
	
	/**
	 * 随机数生成对象
	 */
	private Random random = new Random(47);
	
	/**
	 * 新建一个线程
	 * @param countDownLatch
	 */
	public TaskPortion(CountDownLatch  countDownLatch){
		doWork();
		this.countDownLatch = countDownLatch;
	}
	
	/**
	 * 工作方法
	 */
	public void doWork(){
		try {
			TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		countDownLatch.countDown();
		System.out.println("线程完成:" + this  );
	}
	
	public String toString(){
		return "线程ID:" + id;
	}
	
}