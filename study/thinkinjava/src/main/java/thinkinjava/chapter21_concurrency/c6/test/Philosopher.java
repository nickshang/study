package thinkinjava.chapter21_concurrency.c6.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 功能测试：Philosopher
 * @author NICK
 *
 */
public class Philosopher implements Runnable{
	
	private final ChopstickQueue<Chopstick> bin;
	
	private final int id;
	
	private final int ponderFactor ;
	
	private Random rand = new Random();
	
	/**
	 * 停顿
	 * @throws InterruptedException
	 */
	private void pause() throws InterruptedException{
		if (ponderFactor == 0) return ;
		TimeUnit.MILLISECONDS.sleep(
		rand.nextInt(ponderFactor*250));
	}
	
	/**
	 * 新建一个哲学家对象
	 * @param chopstickQueue 放筷子队列
	 * @param id 
	 * @param ponderFactor
	 */
	public Philosopher(ChopstickQueue<Chopstick> bin,int id, int ponderFactor){
		this.bin = bin;
		this.ponderFactor = ponderFactor;
		this.id = id;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println( this + "  think " );
				pause();
				
				System.out.println( this + " grabbing right ");  //1  //0    
				Chopstick c1 = bin.take();
				
				System.out.println( this + " grabbing left ");  //0  //1
				Chopstick c2 = bin.take();
				
				System.out.println( this + " eating ");
				pause();
				
				bin.put(c1);
				bin.put(c2);
				
				System.out.println( this + " eating  over !");
				
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return  " Philosopher " + id ;
	}
}
