package com.shang.concurrency.c5.concurrent019;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 */
public class UseCountDownLatch {

	public static void main(String[] args) {
		
		final CountDownLatch countDown = new CountDownLatch(2);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("进入线程t1" + "等待其他线程处理完成...");
					countDown.await();
					System.out.println("t1线程继续执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {

			String s = "F:\\work\\004、江苏\\4.江苏经研院能源实验室项目17（研发）\\0.过程文档\\0.数据\\ems\\QS\\江苏_20170827_2255.QS";
			@Override
			public void run() {
				try {
					System.out.println("t2线程进行初始化操作...");
					Thread.sleep(3000);
					System.out.println("t2线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t3线程进行初始化操作...");
					Thread.sleep(4000);
					System.out.println("t3线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		
		
	}
}
