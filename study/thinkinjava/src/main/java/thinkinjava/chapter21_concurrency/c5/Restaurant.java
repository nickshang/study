package thinkinjava.chapter21_concurrency.c5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：生产者与消费者问题
 * 一个饭店，有一个厨师和一个服务员。
 * 服务员必须等厨师准备好食物。厨师准备好食物后通知服务员，服务员上完才后，返回继续等待。
 * 
 * 厨师：代码生产者
 * 服务员：代表：消费者
 * 
 * 根据jdk的void notifyAll()的描述，“解除那些在该对象上调用wait()方法的线程的阻塞状态。
 * 该方法只能在同步方法或同步块内部调用。如果当前线程不是对象所得持有者，该方法抛出一个java.lang.IllegalMonitorStateException 异常”
 * 
 * @author NICK
 *
 */
public class Restaurant {
	
	public Meal meal;
	
	public ExecutorService exec;
	
	public WaitPerson  waitPerson =  new WaitPerson(this);
	
	public Chef  chef =  new Chef(this);
	
	public Restaurant(String name){
		System.out.println( name + "餐厅开始营业!" );
		exec = Executors.newCachedThreadPool();
		exec.execute(waitPerson);
		exec.execute(chef);
	}
	
	public static void main(String[] args) {
		new Restaurant("唐人街");
	}
}

/**
 * 食物
 */
class Meal {
	
	//食物编号
	private final int orderNum;
	
	/**
	 * 功能描述：构造函数
	 * @param i 食物编码
	 */
	public Meal(int i) { this.orderNum = i; }
	
	
	/**
	 * 重写toString()方法
	 */
	public String toString(){ return "Meal " + orderNum; }
}


/**
 * 功能描述：服务员
 * @author NICK
 *
 */
class WaitPerson implements Runnable{
	
	/**
	 * 功能描述：餐馆
	 */
	private Restaurant restaurant;
	
	/**
	 * 功能描述：
	 * @param r
	 */
	public WaitPerson(Restaurant r ){ this.restaurant = r; }
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				
				//餐点为空，等待
				synchronized (this) {
					
					//为防止在并发应用中，某个其他的任务会在WaitPerson被唤醒时，会突然查找并拿着订单
					//唯一按照的方法适应 while(conditions) wait();
					//这样可以保证在退出等待循环之前，条件将得到满足。(要在恰当的同步内部，并防止错失信号的可能性的程序设计)
					//并且如果你收到关于某事物的通知，而他这个条件无关系(就想在在使用notifyAll()时可能发生的情况一样)
					//或者在你安全退出等待循环之前，这个条件发生了边哈，都可以确保你可以重返等待的状态。
					while( restaurant.meal == null ){  
						wait();
					}
				}
				
				//送餐
				System.out.println("开始送餐：" + restaurant.meal.toString() );
				
				//通知厨师
				synchronized (restaurant.chef) {
					if( restaurant.meal != null ){
						TimeUnit.SECONDS.sleep(1);
						restaurant.meal = null;
						restaurant.chef.notifyAll();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("服务员线程被中断!");
		}
	}
}

/**
 * 功能描述：厨师
 * @author NICK
 *
 */
class Chef implements Runnable{
	
	private Restaurant restaurant;
	
	private int count = 0;
	
	//构造函数
	public Chef(Restaurant  r ) { this.restaurant = r; }
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				//食物不为空，线程等待
				synchronized (this) {
					//为防止在并发应用中，某个其他的任务会在WaitPerson被唤醒时，会突然查找并拿着订单
					//唯一按照的方法适应 while(conditions) wait();
					//这样可以保证在退出等待循环之前，条件将得到满足。(要在恰当的同步内部，并防止错失信号的可能性的程序设计)
					//并且如果你收到关于某事物的通知，而他这个条件无关系(就想在在使用notifyAll()时可能发生的情况一样)
					//或者在你安全退出等待循环之前，这个条件发生了边哈，都可以确保你可以重返等待的状态。
					while(restaurant.meal != null ){
						wait();
					}
				}
				
				//制作食物
				
				//超过10个，线程管理器停止
				if(++count > 10){
					System.out.println("关门，停止营业!");
					restaurant.exec.shutdownNow();
//					return ;
				}
				
				//订单生成
				System.out.println(" 订单生成! ");
				
				//制作餐点-》通知服务员
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					TimeUnit.SECONDS.sleep(1);
					System.out.println("制作食物：" + restaurant.meal.toString() );
					restaurant.waitPerson.notifyAll();
				}
				
			} catch (InterruptedException e) {
				System.out.println("厨师线程被中断!");
			}
		}
	}
}
