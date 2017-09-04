package thinkinjava.chapter21_concurrency.c5;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述： 
 * 1、学习使用BlockingQueue;
 * 2、有一天机器有三个任务：一个制作吐司；一个给吐司抹黄油；另外一个在黄油吐司上添果酱；
 * @author NICK
 *
 */
public class ToastOMatic {
	
	public static void main(String[] args) throws InterruptedException {
		
		//制作完成的吐司存储对象
		ToastQueue<Toast>  toasterQueue = new ToastQueue<Toast>();
		
		//制作完成的吐司->涂完黄油的吐司存储对象
		ToastQueue<Toast>  butterQueue = new ToastQueue<Toast>();
		
		//涂完黄油的吐司->涂完果酱的吐司存储对象
		ToastQueue<Toast>  jammQueue = new ToastQueue<Toast>();
		
		
		//新建三个任务，分布一个制作吐司；一个给吐司抹黄油；另外一个在黄油吐司上添果酱
		Toaster toaster = new Toaster(toasterQueue);
		
		Butter butter = new Butter(toasterQueue,butterQueue);
		
		Jamm jamm = new Jamm(butterQueue,jammQueue);
		
		
		//加入线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute( toaster );
		exec.execute( butter );
		exec.execute( jamm );
		
		//关闭线程
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
		
	}
}

/**
 * 给吐司抹黄油线程任务
 * @author Think
 *
 */
class Jamm implements Runnable{

	/**
	 * 给制作完的吐司吐完黄油的吐司的存储对象
	 */
	private ToastQueue<Toast>  butterQueue;
	
	/**
	 * 给涂完黄油的吐司涂果酱的吐司的存储对象
	 */
	private ToastQueue<Toast>  jammQueue;
	
	
	/**
	 * 新建一个给吐司涂果酱的任务
	 * @param butterQueue
	 * @param jammQueue
	 */
	public Jamm(ToastQueue<Toast>  butterQueue,  ToastQueue<Toast>  jammQueue ){
		this.butterQueue = butterQueue;
		this.jammQueue = jammQueue;
	}
	
	@Override
	public void run( ) {
		try{
			while(!Thread.interrupted()){
				
				TimeUnit.MILLISECONDS.sleep(100);
				Toast t = butterQueue.take();
				t.jamm();
				jammQueue.put(t);
				
				System.out.println("给吐司涂果酱完成:" + t );
			}	
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("吐司抹黄油线程结束!");
	}
}


/**
 * 给吐司抹黄油线程任务
 * @author NICK
 *
 */
class Butter implements Runnable{
	
	/**
	 * 制作完成的吐司的存储对象
	 */
	private ToastQueue<Toast>  toasterQueue;
	
	/**
	 * 给制作完的吐司吐完黄油的吐司的存储对象
	 */
	private ToastQueue<Toast>  butterQueue;
	
	/**
	 * 新建一个给吐司抹黄油线程任务
	 * @param toasterQueue 制作完成的吐司的存储对象
	 * @param butterQueue 给制作完的吐司吐完黄油的吐司的存储对象
	 */
	public Butter( ToastQueue<Toast>  toasterQueue,  ToastQueue<Toast>  butterQueue){
		this.toasterQueue = toasterQueue;
		this.butterQueue = butterQueue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				Toast t = toasterQueue.take();
				
				TimeUnit.MILLISECONDS.sleep(200);
				t.butter();
				butterQueue.put(t);
				
				System.out.println("给吐司涂黄油完成:" + t );
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


/**
 * 制作吐司线程任务
 * @author NICK
 *
 */
class  Toaster implements Runnable{
	
	/**
	 * 吐司队列：将制作好的吐司放入队列中
	 */
	private ToastQueue  toasterQueue;
	
	/**
	 * 新建一个吐司制作任务，将做好的吐司放入toasterQueue队列
	 * @param toasterQueue  
	 */
	public Toaster( ToastQueue<Toast>  toasterQueue){
		this.toasterQueue = toasterQueue;
	}

	/**
	 * 制作吐司编号
	 */
	private int count = 0;
	
	@Override
	public void run()  {
		try {
			while(!Thread.interrupted()){
				Random random = new Random(100);
				int id = random.nextInt();
				
				TimeUnit.MILLISECONDS.sleep(300);
				Toast t = new Toast(String.valueOf(count++));
				
				toasterQueue.put( t );
				
				System.out.println( "制作完成吐司: " + t );
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println( "制作完成吐司线程结束!" );
	}
}


/**
 * 功能描述：吐司阻塞队列
 * @author NICK
 *
 */
class ToastQueue<Toast>  extends LinkedBlockingQueue<Toast>{  }

/**
 * 功能描述：吐司对象
 * @author Think
 *
 */
class Toast{
	
	//编号
	private final String id ;
	
	//构造函数
	public Toast(String id){
		this.id  = id ;
	}
	
	//3个状态 没有涂其他东西、抹黄油完成，添果酱完成
	public enum Status {
		
		DRY("1"),BUTTERED("2"),JAMMED("3");
		
		private String id;
		
		Status(String id){
			this.id = id;
		}
		
		public String toString(){ return  id;}
	};
	
	//默认吐司状态
	private Status status = Status.DRY;
	
	//抹黄油
	public void butter(){ status = Status.BUTTERED; };
	
	//添果酱
	public void jamm(){ status = Status.JAMMED; };
	
	//返回吐司状态
	public Status getStatus(){ return status; }
	
	//返回ID
	public String getID() { return id; }
	
	//重新toString()方法
	public String toString(){ return "吐司(ID:+" + id + "," + "状态:" +  status + ")"; }
	
}
