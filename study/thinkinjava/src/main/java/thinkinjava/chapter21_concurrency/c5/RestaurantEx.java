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
 *  添加BusBoy类。在上菜后，进行清理工作。
 *  1、当WaitPerson上菜完成后，标记BusBoy的notified=true通知已经完成上菜，可以进行清理工作；
 *  2、当BusBoy清理工作完毕后，标记WaitPerson的notified=true通知已经清理完毕，可以上菜；
 *  3、任务测试是否已经上菜完毕，如果上菜未完毕将进行清理工作；
 * @author NICK
 *
 */
public class RestaurantEx {
	
	public MealEx mealEx ;
	
	public ExecutorService exec;
	
	public WaitPersonEx  waitPersonEx =  new WaitPersonEx(this);
	
	public ChefEx  chefEx =  new ChefEx(this);
	
	public BusBoy  busBoyEx =  new BusBoy(this);
	
	public RestaurantEx(String name){
		System.out.println( name + "餐厅开始营业!" );
		exec = Executors.newCachedThreadPool();
		exec.execute(waitPersonEx);
		exec.execute(chefEx);
		exec.execute(busBoyEx);
	}
	
	public static void main(String[] args) {
		new RestaurantEx("唐人街");
	}
	
}

/**
 * 食物
 */
class MealEx {
	
	//食物编号
	private final int orderNum;
	
	/**
	 * 功能描述：构造函数
	 * @param i 食物编码
	 */
	public MealEx(int i) { this.orderNum = i; }

	/**
	 * 重写
	 */
	public String toString(){ return "Meal " + orderNum; }
}

/**
 * 功能描述:餐馆清洁工
 * @author NICK
 *
 */
class BusBoy implements Runnable{
	
	/**
	 * 餐馆
	 */
	private RestaurantEx restaurantEx ; 
	
	/**
	 * 是否唤醒
	 */
	public  boolean notified;  
	
	
	/**
	 * 是否唤醒
	 */
	public volatile  MealEx  mealEx;  
	
	/**
	 * 构造函数
	 * @param r
	 */
	public BusBoy(RestaurantEx r ){
		this.restaurantEx = r;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				
				//当上菜后，通知餐馆清洁工进行清理
				synchronized (this) {
					while(!notified)
						wait();
					notified = false;
				}
				
				//清理餐点
				System.out.println("开始清理餐点：" + mealEx );
				System.out.println("结束清理餐点：" + mealEx );
				
				//通知服务员
				synchronized (restaurantEx.waitPersonEx) {
					restaurantEx.waitPersonEx.notified = true; 
					restaurantEx.waitPersonEx.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println( "清理工线程被中断!" );
		}
	}
}

/**
 * 功能描述：服务员
 * @author NICK
 *
 */
class WaitPersonEx implements Runnable{
	
	/**
	 * 功能描述：餐馆
	 */
	private RestaurantEx restaurantEx;
	
	/**
	 * 功能描述：是否唤醒
	 */
	public  boolean notified;
	
	/**
	 * 功能描述：构造函数
	 * @param r
	 */
	public WaitPersonEx(RestaurantEx r ){ this.restaurantEx = r; }
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				
				//食物为空，等待
				synchronized (this) {
					
					//为防止在并发应用中，某个其他的任务会在WaitPerson被唤醒时，会突然查找并拿着订单
					//唯一按照的方法适应 while(conditions) wait();
					//这样可以保证在退出等待循环之前，条件将得到满足。(要在恰当的同步内部，并防止错失信号的可能性的程序设计)
					//并且如果你收到关于某事物的通知，而他这个条件无关系(就想在在使用notifyAll()时可能发生的情况一样)
					//或者在你安全退出等待循环之前，这个条件发生了边哈，都可以确保你可以重返等待的状态。
				
					//餐点为空 等待
					while (restaurantEx.mealEx == null) {  
						wait();
					}
				}

				//端送餐点
				System.out.println("开始端送餐点：" + restaurantEx.mealEx.toString() );
				TimeUnit.SECONDS.sleep(1);
				System.out.println("结束端送餐点：" + restaurantEx.mealEx.toString() );
			
				
				//通知清理工(进行清理，通知服务员已经清理完成)
				synchronized (restaurantEx.busBoyEx) {
					restaurantEx.busBoyEx.notified = true;
					restaurantEx.busBoyEx.mealEx = restaurantEx.mealEx;
					restaurantEx.busBoyEx.notifyAll();
				}
				
				
				//送菜完成 ——》 通知厨师
				synchronized (restaurantEx.chefEx) {
					if( restaurantEx.mealEx != null ){
						restaurantEx.mealEx = null;
						restaurantEx.chefEx.notifyAll();
					}
				}
				
				synchronized (this) {
					if( !notified )
						wait(); //等待清理工线程进行清理工作
					notified = false;
				}
			}
		} catch (InterruptedException e) {
			System.out.println( "服务员线程被中断!" );
		}
	}
}

/**
 * 功能描述：厨师
 * @author NICK
 *
 */
class ChefEx implements Runnable{
	
	/**
	 * 餐馆
	 */
	private RestaurantEx restaurantEx;
	
	/**
	 * 制作次数
	 */
	private int count = 0;
	
	//构造函数
	public ChefEx(RestaurantEx  r ) { this.restaurantEx = r; }
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				
				//食物不为空，线程等待
				synchronized (this) {
					
					//为防止在并发应用中，某个其他的任务会在WaitPerson被唤醒时，会突然查找并拿着订单
					//唯一按照的方法适应 while(conditions) wait();
					//这样可以保证在退出等待循环之前，条件将得到满足。(要在恰当的同步内部，并防止错失信号的可能性的程序设计)
					//并且如果你收到关于某事物的通知，而他这个条件无关系(就想在在使用notifyAll()时可能发生的情况一样)
					//或者在你安全退出等待循环之前，这个条件发生了边哈，都可以确保你可以重返等待的状态。
					
					//餐点为null 等待
					while(restaurantEx.mealEx != null  ){
						wait();
					}
				}
								
				//超过10个，线程管理器停止
				if(++count > 10){
					System.out.println("关门，停止营业!");
					restaurantEx.exec.shutdownNow();
					//return ;
				}
				
				//制作餐点
				System.out.println("开始制作餐点："   );
				TimeUnit.SECONDS.sleep(1);
				
				//通知服务员线程
				synchronized (restaurantEx.waitPersonEx) {
					restaurantEx.mealEx = new MealEx(count);
					System.out.println("结束制作餐点：" + restaurantEx.mealEx.toString() );
					restaurantEx.waitPersonEx.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println( "厨师线程被中断!" );
		}
	}
}
