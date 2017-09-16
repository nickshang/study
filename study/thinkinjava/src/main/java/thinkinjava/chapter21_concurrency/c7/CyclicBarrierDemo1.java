package thinkinjava.chapter21_concurrency.c7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @类描述：循环栅栏
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。 
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月30日 下午3:42:10
 * @version v1.0
 * @Copyright 北京清软创新科技股份有限公司
 */
public class CyclicBarrierDemo1 {
	
	// 栅栏管理的线程数
	private static final int n = 4;
	
	public static class C1 implements Runnable{
		// 循环栅栏
		private final CyclicBarrier cyclicBarrier;
		
		public C1(CyclicBarrier cyclicBarrier){
			this.cyclicBarrier = cyclicBarrier;
		}
		
		public void run()  {
			for( int i = 0; i <= 2; i++ ){  // 循环调用
				try {
					System.out.println( Thread.currentThread().getName() + "开始工作！");
				
					TimeUnit.SECONDS.sleep(2);
					System.out.println( Thread.currentThread().getName() + " 工作完成!");
			
					this.cyclicBarrier.await();
					System.out.println( Thread.currentThread().getName() + "等待其他线程工作");
					TimeUnit.SECONDS.sleep(5);
					
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		
		}
	}
	
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
		for(int i = 0; i < n; i++){
			new Thread( new C1(cyclicBarrier)).start();
		}
		
	}
}
