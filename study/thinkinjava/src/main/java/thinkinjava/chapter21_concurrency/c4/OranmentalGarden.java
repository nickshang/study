package thinkinjava.chapter21_concurrency.c4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ������������԰ίԱ��ϣ���˽�����ͨ��������Ž��빫԰����������
 * ÿ�����Ŷ���ʮ��ת�Ż���������ʽ�ļ���������������һ��ʮ��ת�ŵļ���ֵ����ʱ��
 * �ͱ�ʾ��԰���������Ĺ������Ҳ�������
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

//��������
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

//�����
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
