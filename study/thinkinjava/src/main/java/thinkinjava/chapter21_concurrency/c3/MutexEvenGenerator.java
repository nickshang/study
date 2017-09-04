package thinkinjava.chapter21_concurrency.c3;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 功能描述：采用显示的互斥机制
 * @author NICK
 *
 */
public class MutexEvenGenerator   extends IntGenerator{

	private   int  intcurrentEvenValue = 0 ;
	
	private Lock lock = new ReentrantLock();
	
	@Override
	public  int next() {
		lock.lock();
		try{
			++intcurrentEvenValue;
			++intcurrentEvenValue;
			return intcurrentEvenValue;	
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new thinkinjava.chapter21_concurrency.c3.SynchronizedGenerator() );
	}
}
