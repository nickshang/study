package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.TimeUnit;

/**
 * 一个线程可以在其他线程之上调用join()方法，其效果是等待一段时间指导第二线程结束，才恢复执行。
 * 如果一个线程在另一个线程t上调用t.join()，此线程将被挂起，直到目标线程t结束才恢复(即t.isAlive()返回为假)。<p/>
 *
 *  也可以在调用join()时带上一个超时参数(单位可以是毫秒、或者毫秒和纳秒)，这样目标线程在这段时间到期还没有结束，join（）方法总能返回。<p/>
 *
 * 功能描述:测试现场join方法
 * @author Think
 *
 */
public class Joining extends Thread {
	
	public static void main(String[] args) {
		Sleeper  sleepy = new Sleeper("sleepy",1500),
				 grumpy = new Sleeper("grumpy",1500);
		
		Joiner   dopey = new Joiner( "dopey", sleepy ),
				 doc = new Joiner( "doc", sleepy );
		
		grumpy.interrupt();
	}
}

class Sleeper extends Thread {
	private int duration;
	
	public Sleeper(String name, int sleepTime){
		super(name);
		duration = sleepTime;
		start();
	}

	public void run() {
		try {
			TimeUnit.MICROSECONDS.sleep(duration);
		}catch (Exception e) {
			System.out.println( "线程中断:" + getName() +  " was interrupted. " +
					"isInterrupted():" + isInterrupted() );
			return ;
		}
		System.out.println( getName() + " has awakened.");
	}
}

/**
 * 功能描述：线程添加者
 * @author NICK
 *
 */
class Joiner extends Thread {
	
	private Sleeper sleeper;
	
	public  Joiner(String name ,Sleeper s){
		super(name);
		this.sleeper = s;
		start();
	}

	@Override
	public void run() {
		try{
			sleeper.join();
		}catch (InterruptedException e) {
			System.out.println( "Interrupted" );
		}
		System.out.println( getName() + " join completed.");
	}
	
}