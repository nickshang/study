package thinkinjava.chapter21_concurrency.c6;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 功能测试：Philosopher 哲学家
 * @author NICK
 *
 */
public class Philosopher implements Runnable{
	
	private Chopstick left;
	
	private Chopstick right;
	
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
	 * 停顿
	 * @throws InterruptedException
	 */
	private void pause2() throws InterruptedException{
		if (ponderFactor == 0) return ;
		TimeUnit.MILLISECONDS.sleep(
		rand.nextInt(ponderFactor*2500));
	}

	/**
	 * 哲学家
	 * @param left 左手筷子
 	 * @param right 右手uaizi
	 * @param id  哲学家标示ID
	 * @param ponderFactor 停顿时间标示
     */
	public Philosopher(Chopstick left,Chopstick right,int id, int ponderFactor){
		this.left = left;
		this.right = right;
		this.ponderFactor = ponderFactor;
		this.id = id;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println( this + "  think " );
				if (id == 0) 
					pause2();
				else
					pause();
				
				
				System.out.println( this + " grabbing right ");  //1  //0    
				right.take();
				
				System.out.println( this + " grabbing left ");  //0  //1
				left.take();
				
				System.out.println( this + " eating ");
				pause();
				
				right.drop();
				left.drop();
				
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
