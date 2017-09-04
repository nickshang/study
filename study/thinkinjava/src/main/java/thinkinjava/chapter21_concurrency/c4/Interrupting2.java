package thinkinjava.chapter21_concurrency.c4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 功能描述：测试在Java SE5并发库中添加了一个特性，即在ReentrantLock上阻塞的任务具备可以被中断的能力。
 * @author NICK
 *
 */
public class Interrupting2 {
	
	public static void main(String[] args) throws InterruptedException {
	
		Thread t = new Thread( new Blocked2() );
		t.start();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println( " [开始调用t.interrupt] Issuing t.interrupt ");
		t.interrupt();
		
	}
}

//阻塞类
class Blocked2 implements Runnable{
	BlockedMutex block = new BlockedMutex();
	
	public void run() {
		System.out.println(" [等待调用BlockedMutex对象中的f()] waitring from f() in BlockedMutex.");
		block.f();
		System.out.println(" [被中断的方法被调用] Broken out of blocked call ");
	}
}

//阻塞互斥
class BlockedMutex {
	
	//可重入锁
	private Lock lock = new java.util.concurrent.locks.ReentrantLock();
	
	//构造方法
	public BlockedMutex(){
		lock.lock();
	}
	
	//如果当前线程未被中断，则获取锁
	public void f(){
		try{
			lock.lockInterruptibly();
			System.out.println(" [从f()方法获取锁] lock acquired in f() ");
		}catch(InterruptedException e){
			//e.printStackTrace();
			System.out.println(" [从f()方法中获取锁被中断] Interrupted from lock acquisition in f() ");
		}
	}
	
}
