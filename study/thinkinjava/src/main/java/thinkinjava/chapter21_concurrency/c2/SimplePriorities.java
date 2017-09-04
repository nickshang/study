package thinkinjava.chapter21_concurrency.c2;

/**
 * 功能描述：设置线程优先级，打印现场的输出结果，进行简单测试实现。
 * @author NICK
 *
 */
public class SimplePriorities implements Runnable{
	
	//现场状态记录数
	private  volatile int countDown = 5;
	
	private volatile double d;  //no optimiaztion 非最佳方案
	
	//线程优先级
	private int prioities ;
	
	public SimplePriorities(int prioities ){
		this.prioities = prioities;
	}
	
	//覆盖线程toString()方法，输出线程状态
	public String toString(){
		return Thread.currentThread() + ":" + countDown;
	}
	
	public void run() {
		//设置优先级
		Thread.currentThread().setPriority( prioities );
		
		//线程任务
		while(true){
			//进行循环计算
			for ( int i = 0; i < 10000; i++ ){
				d += ( Math.PI + Math.E ) / (double)i;
				if ( i % 100 == 0)
					Thread.yield(); //i为100的整数，线程让步. CPU优先给他其他线程进行调用.
			}
			//打印线程状态
			System.out.println(this);
			if( --countDown == 0) return;
		}
	}
	
	public static void main(String[] args) {
		
		java.util.concurrent.ExecutorService exec = java.util.concurrent.Executors.newCachedThreadPool();
		
		//添加5个优先级最低的线程
		for ( int i = 0; i < 5; i++ ){
			exec.submit( new SimplePriorities(Thread.MIN_PRIORITY));
		}
		//添加1个优先级最高的线程
		exec.submit(new SimplePriorities(Thread.MAX_PRIORITY));
		
		exec.shutdown();
	}
}
