package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.ThreadFactory;

/**
 * 功能描述： 定制线程工厂：将线程设置为后台进程
 * @author NICK
 *
 */
public class DeamonThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true); //设置为线程
		return t;
	}

}
