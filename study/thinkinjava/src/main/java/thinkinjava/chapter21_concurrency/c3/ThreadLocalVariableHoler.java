package thinkinjava.chapter21_concurrency.c3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：线程本地存储
 * 线程存储是一种自动化机制，可以为
 * @author NICK
 */
public class ThreadLocalVariableHoler {

	private static ThreadLocal<Integer> value = 
		new ThreadLocal<Integer>(){
			private Random rand = new Random(47);
			protected synchronized Integer initialValue(){
				return rand.nextInt(10000);
			}
		};
		
	public static int get() { return value.get(); }
		
	public static void increment(){
		value.set(value.get()+1);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for ( int i = 0; i < 5; i++ ){
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		exec.shutdownNow();
	}
}

class Accessor implements Runnable {
	

	private final int id;
	
	public Accessor(int idn) { id = idn; }
	
	public void run(){
		while(!Thread.currentThread().isInterrupted()){
//			ThreadLocalVariableHoler.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	public String toString(){
		return "#" + id + ":" + ThreadLocalVariableHoler.get();
	}
}