package com.shang.concurrency.c5.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPoolExecutor2 implements Runnable{

	private static AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			System.out.println("任务" + temp);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		//System.out.println(Runtime.getRuntime().availableProcessors());
		BlockingQueue<Runnable> queue = 
				//new LinkedBlockingQueue<Runnable>();  // 无界队列，当新任务到来，corePoolSize后，就不会继续增加，若后续仍有新的任务加入，而没有空的线程资源，这任务直接进入等待。若任务创建和处理的速度差异很大，无界队列保持快速增长，直到耗尽内存。
				new ArrayBlockingQueue<Runnable>(10);
		ExecutorService executor  = new ThreadPoolExecutor(
					5, 		//core
					10, 	//max  无效
					120L, 	//2fenzhong
					TimeUnit.SECONDS,
					queue);
		
		for(int i = 0 ; i < 20; i++){
			executor.execute(new UseThreadPoolExecutor2());
		}
		Thread.sleep(1000);
		System.out.println("queue size:" + queue.size());		//10
		Thread.sleep(2000);
	}


}
