package thinkinjava.chapter21_concurrency.c9;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class Tester2<C> {
	
	static int testReps = 10;
	
	static int testCycles = 1000;
	
	static int containerSize = 1000;
	
	/**
	 * 功能描述：初始化后的容器-》数据存储在testContainer对象中
	 * @return
	 */
	abstract C ContainerInitializer();
	
	abstract void startReadersAndWriters();
	
	C testContainer;
	
	String testId;
	
	int nReaders;
	
	int nWriters;
	
	volatile long readRestult = 0;
	
	volatile long readTime = 0;
	
	volatile long writeTime = 0;
	
	CountDownLatch endLatch ;
	
	static ExecutorService exec = Executors.newCachedThreadPool();
	
	Integer[] writeData;
	
	Tester2(String testid, int nReaders, int nWriters ){
		this.testId = testid + " " + nReaders + "r " + nWriters;
		this.nReaders = nReaders;
		this. nWriters =  nWriters;
		
//		Generator<java.lang.Integer> t = new RandomGenerator.Integer1();
		
//		 writeData = Generated.array(Integer.class,new RandomGenerator.Integer(), containerSize);
		
		for(int i = 0 ; i < testReps; i++) {
			runTest();
			readTime = 0;
			writeTime = 0;
		}
	}
	
	void runTest() {
		endLatch = new CountDownLatch( nReaders + nWriters );
		testContainer = ContainerInitializer();
		startReadersAndWriters();
		try{
			endLatch.await();
		}catch (InterruptedException e) {
			System.out.println("endLatch interrupted");
		}
		
		System.out.printf("%-27s %14d 14d\n", testId, readTime, writeTime);
		if(readTime != 0 && writeTime != 0 ){
			System.out.printf("%-27s %14d\n", "readTime + writeTime= ", readTime + writeTime );
		}
	}
	
	abstract class TestTask implements Runnable {
		abstract void test();
		
		abstract void pubResults();
		
		long duration;
		
		public void run() {
			long startTime = System.nanoTime();
			test();
			duration = System.nanoTime() - startTime;
			synchronized (Tester2.this) {
				pubResults();
			}
			endLatch.countDown();
		}
	}
	
	public static void initMain(String[] args ){
		if(args.length > 0)
			testReps = new Integer(args[0]);
		if(args.length > 1)
			testCycles = new Integer(args[1]);
		if(args.length > 2)
			containerSize = new Integer(args[2]);
		System.out.printf("%-27s %14s %14s\n", "Type", "Read time", "Write time");
	}
}
