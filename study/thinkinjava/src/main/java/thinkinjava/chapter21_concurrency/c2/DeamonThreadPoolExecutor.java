package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：后台线程管理器<br>
 * 此类从ThreadPoolExecutor继承，构造函数重载父类的带线程工厂的构造函数
 * @author NICK
 *
 */
public class DeamonThreadPoolExecutor extends ThreadPoolExecutor {

	private DeamonThreadPoolExecutor() {
		   super(0, Integer.MAX_VALUE,
                   60L, TimeUnit.SECONDS,
                   new SynchronousQueue<Runnable>(),
                   new DeamonThreadFactory() );
	} 
	
}
