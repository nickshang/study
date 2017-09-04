package thinkinjava.chapter21_concurrency.c7;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @类描述： 创建一个30量车去泊车，只有10个车位的常见
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月31日 下午2:37:57
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class SemaphoreDemo2 {
	
	/**
	 * 
	 * @类描述：车辆
	 * @创建人：NICK
	 * @mail sjshang@tsingsoft.com
	 * @创建时间：2016年3月31日 下午2:40:18
	 * @Copyright 北京清软创新科技股份有限公司
	 */
	public static class Car implements Runnable{
		
		/**
		 * 车辆ID
		 */
		private int id;
		
		/**
		 * 信号量
		 */
		private Semaphore semaphore;
		
		/**
		 * 
		 * Title:构造函数
		 * @param semaphore
		 * @param id
		 */
		public  Car(Semaphore semaphore, int id){
			this.semaphore = semaphore;
			this.id = id;
		}
		
		/**
		 * 
		 * @描述: 进入停车场
		 * @返回类型 void
		 * @创建人 NICK
		 * @创建时间 2016年3月31日 下午3:37:50
		 * @since
		 * @throws
		 */
		public void inset(){
			System.out.println( String.format("%d进入停产场", id));
		}

		/**
		 * 
		 * @描述: 离开停车场
		 * @返回类型 void
		 * @创建人 NICK
		 * @创建时间 2016年3月31日 下午3:37:50
		 * @since
		 * @throws
		 */
		public void out(){
			System.out.println( String.format("############%d离开停产场", id));
		}

		
		@Override
		public void run() {
				try {
					semaphore.acquire();
					inset();
					
					sleep();
					
					out();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
		/**
		 * 
		 * @描述: 随机睡眠
		 * @返回类型 void
		 * @创建人 NICK
		 * @创建时间 2016年3月31日 下午3:45:12
		 * @since
		 * @throws
		 */
		public void sleep(){
			Random random = new Random();
			try {
				TimeUnit.MILLISECONDS.sleep( random.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		// 车辆数量
		final int NUMBER_OF_CAR = 30;
		
		// 车位数量
		final int NUMBER_OF_SEMAPHORE = 2;
		
		// first-in first-out 
		Semaphore semaphore = new Semaphore(NUMBER_OF_SEMAPHORE,true);
		
		// 创建线程池
		java.util.concurrent.ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0; i < NUMBER_OF_CAR; i++){
			executor.execute( new Car(semaphore,i));
		}
		// 关闭线程池
		executor.shutdown();
	}
}
