package thinkinjava.chapter21_concurrency.c7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：延迟队列例子
 * @author NICK
 *
 */
public class DelayQueueDemo {
	
	public static void main(String[] args) {
		
		
		//随机类
		Random rand  = new Random(47);
		
		//现场池
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//延迟队列
		DelayQueue<DelayedTask> queue = 
			new DelayQueue<DelayedTask>();
		
		//队列添加任务
		for( int i = 0; i < 20; i++ ){
			queue.put( new DelayedTask(rand.nextInt(5000)));
		}
		
		//队列添加任务
		queue.add(new DelayedTask.EndSentinel(5000,exec));
		
		exec.execute( new DaleyedTaskConsumer(queue));
	}
}

/**
 * 
 * @author NICK
 *
 */
class DaleyedTaskConsumer implements Runnable{
	
	//阻塞队列
	private DelayQueue<DelayedTask> q;
	
	//新建一个阻塞
	public DaleyedTaskConsumer(DelayQueue<DelayedTask> q){
		this.q = q;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted())  //取出队列数据
				q.take().run();
		}catch(InterruptedException e) {
				e.printStackTrace();
		}
		System.out.println("Finished DelayTaskConsumer.");
	}
}

/**
 * 功能描述：延时队列
 * @author NICK
 *
 */
class DelayedTask implements Runnable, Delayed{
	private static int count = 0;
	
	//ID
	private final int id = count++;
	
	//指定延迟时间
	private final int delta;
	
	//延迟时间（指定延迟时间+当前时间）
	private final long trigger;
	
	//延迟对象
	protected static List<DelayedTask> sequence = 
		new ArrayList<DelayedTask>();
	
	//初始化对象
	public DelayedTask(int delayInMilliseconds){
		this.delta = delayInMilliseconds;
		
		// System.nanoTime()   返回最准确的可用系统计时器的当前值，以毫微秒为单位。
		//将给定单元的时间段转换到此单元。 System.nanoTime() 
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}
	
	@Override
	public void run() {
		System.out.println( this + " " );
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask)o;
		if ( trigger < that.trigger) return -1;
		if ( trigger > that.trigger) return 1;
		return 0;
	}

	@Override
	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}
	
	public String summay(){
		return "(" + id + ":" + delta + ")";
	}
	
	
	//结束队列最后一个线程
	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;
		public EndSentinel(int delay, ExecutorService e){
			super(delay);
			exec = e;
		}
		
		public void run(){
			for(DelayedTask pt : sequence) {
				System.out.print(  pt.summay() + " ");
			}
			System.out.println();
			System.out.println( this + " Calling shutdownNow()" );
			exec.shutdownNow();
		}
	}
}
