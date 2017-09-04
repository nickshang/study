package thinkinjava.chapter21_concurrency.c5;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：学习notify和notifyall区别。
 * @author NICK
 *
 */
public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		
		//线程管理器
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5 ; i++ ){
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		
		//定时任务工具
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			boolean prod = true;
			public void run(){
				if(prod){
					System.out.println(" notify() ");
					Task.blocker.prod();
					prod = false;
				}else{
					System.out.println(" notifyAll() ");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();  //定时器取消
		System.out.println("\n Timer canceled");
		
		
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task2.blocker.prodAll");
		Task2.blocker.prodAll();
		
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\n Shutting down");
		exec.shutdown();
		
	}
}

/**
 * 阻塞类
 * @author NICK
 *
 */
class Blocker {
	
	/*
	 * 描述：当前线程没有被打断，当前现场等待，当被唤醒时输出当前线程的名称
	 */
	synchronized void waittingCall(){  
		try{
			while(!Thread.interrupted()){
				wait();
				System.out.println( " 线程名称:" + Thread.currentThread() );
			}
		}catch (Exception e) {
			System.out.println( "异常类!" );
		}
	}
	
	//
	synchronized void prod(){ notify(); }
	
	//
	synchronized void prodAll(){ notifyAll(); }
}

//任务类1
class Task implements Runnable{
	
	//静态阻塞类
	static Blocker blocker = new Blocker();
	
	//任务
	public void run(){
		
		//调用等待方法
		blocker.waittingCall();
	}
}

//任务类2
class Task2 implements Runnable{
	//静态阻塞类
	static Blocker blocker = new Blocker();
	
	//任务
	public void run(){
		
		//调用等待方法
		blocker.waittingCall();
	}
}