package thinkinjava.chapter21_concurrency.c4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * ����������������Java SE5�������������һ�����ԣ�����ReentrantLock������������߱����Ա��жϵ�������
 * @author NICK
 *
 */
public class Interrupting2 {
	
	public static void main(String[] args) throws InterruptedException {
	
		Thread t = new Thread( new Blocked2() );
		t.start();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println( " [��ʼ����t.interrupt] Issuing t.interrupt ");
		t.interrupt();
		
	}
}

//������
class Blocked2 implements Runnable{
	BlockedMutex block = new BlockedMutex();
	
	public void run() {
		System.out.println(" [�ȴ�����BlockedMutex�����е�f()] waitring from f() in BlockedMutex.");
		block.f();
		System.out.println(" [���жϵķ���������] Broken out of blocked call ");
	}
}

//��������
class BlockedMutex {
	
	//��������
	private Lock lock = new java.util.concurrent.locks.ReentrantLock();
	
	//���췽��
	public BlockedMutex(){
		lock.lock();
	}
	
	//�����ǰ�߳�δ���жϣ����ȡ��
	public void f(){
		try{
			lock.lockInterruptibly();
			System.out.println(" [��f()������ȡ��] lock acquired in f() ");
		}catch(InterruptedException e){
			//e.printStackTrace();
			System.out.println(" [��f()�����л�ȡ�����ж�] Interrupted from lock acquisition in f() ");
		}
	}
	
}
