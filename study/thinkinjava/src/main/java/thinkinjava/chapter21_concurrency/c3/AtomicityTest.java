package thinkinjava.chapter21_concurrency.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 功能描述：实验原子性概念
 * getValue()符合原子性，但是return i 确实是原子性操作,
 * 但是缺少同步使得数值可以在不稳定的中间状态被读取，由于i也不是volatile,还存在可视性问题。
 * @author NICK
 *
 */
public class AtomicityTest  implements Runnable{
	
	private volatile int i = 0;
	//private int i = 0;  //缺少volatile存在可视性问题
	
	public int getValue() { return i; }  //缺少同步会使数据在不稳定的中间状态读取
	//public synchronized int getValue() { return i; }
	
	public synchronized void evenIncremnet() {
		i++;
		i++;
	}
	
	public void run() {
		while(true)
			 evenIncremnet();
	}
	
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while(true){
			int val = at.getValue();
			if (val % 2 != 0){
				System.out.println( "发现奇数 val:" + val );
				System.exit(0);
			}
		}
	}
}
