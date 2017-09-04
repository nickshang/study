package thinkinjava.chapter21_concurrency.c4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：花园委员会希望了解明天通过多个大门进入公园的总人数。
 * 每个大门都有十字转门或者其他形式的计数器，并且任务一个十字转门的计数值递增时；
 * 就表示公园的总人数的共享计算也会递增。
 * @author NICK
 *
 */
public class OranmentalGarden {
	
	public static void main(String[] args ) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for ( int i = 0; i < 5; i++ ) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if(!exec.awaitTermination(250, TimeUnit.MICROSECONDS))
			System.out.println("some task were not terminated");
		System.out.println("Total:" + Entrance.getTotalCount());
		System.out.println("sum of Enterances : " + Entrance.sumEntrances());
	}
	
}

//计算器类
class Count {
	
	private int count = 0 ;
	
	private Random rand = new Random(47);
	
	public synchronized int increment() {
		int temp = count;
		if ( rand.nextBoolean() ){
			Thread.yield();
		}
		return (count = ++temp);
	}
	
	public synchronized int value() { return count ; }
}

//入口类
class Entrance implements Runnable {
	
	private static Count count = new Count();
	private static List<Entrance> entrances = 
		new ArrayList<Entrance>();
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;
	
	public static synchronized void cancel() { canceled = true ;}
	
	public Entrance(int id){
		this.id = id;
		entrances.add(this);
	}
	
	public void run() {
		while(!canceled){
			synchronized(this){
				++number;
			}
			System.out.println( this + " Total :" + count.increment() );
			try{
				TimeUnit.MILLISECONDS.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println( "Stopping " + this );
	}
	
	public synchronized  int getValue() { return number; }
	
	public String toString(){
		return "Entrance " + id + " : number : " + getValue();
	}
	
	public static int getTotalCount() {
		return count.value();
	}
	
	public static int sumEntrances() {
		int sum = 0;
		for ( Entrance entrance : entrances ) {
			sum += entrance.getValue();
		}
		return sum;
	}
}
