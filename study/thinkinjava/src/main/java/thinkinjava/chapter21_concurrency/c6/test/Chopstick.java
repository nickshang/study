package thinkinjava.chapter21_concurrency.c6.test;

/**
 * 功能描述：筷子
 * @author NICK
 *
 */
public class Chopstick {
	
	/**
	 * 是否拿起
	 */
	private boolean taken = false;
	
	/**
	 * 功能描述：拿起筷子（如果筷子是拿起状态，对象将等待。知道筷子挂起。）
	 * @throws InterruptedException
	 */
	public synchronized  void take() throws InterruptedException {
		while(taken)
			wait();
		taken = true;
	}
	
	/**
	 * 功能描述：将快递放下
	 */
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
