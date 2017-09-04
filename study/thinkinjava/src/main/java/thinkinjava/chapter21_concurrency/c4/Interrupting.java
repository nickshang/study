package thinkinjava.chapter21_concurrency.c4;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * �������������������ֳ��ж�
 * 1��SleepBlocked�����ж�;
 * 2��IOBlocked�����ж�
 * 3��SynchronizedBlocked�����жϣ�
 * @author NICK
 *
 */
public class Interrupting {
	
	private static ExecutorService exec = 
		Executors.newCachedThreadPool();
	
	static void test(Runnable r) throws InterruptedException{
		Future<?> f = exec.submit(r);
		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("Intterupting " + r.getClass().getName() );
		f.cancel(true);
		while(true){
			TimeUnit.MICROSECONDS.sleep(100);
			if(f.isDone()){
				System.out.println(" f is done() ");
				break;
			}
		}
		System.out.println("Interrupt sent to " + r.getClass().getName());
	}
	
	public static void main(String[] args) throws InterruptedException {
//		test(new SleepBlocked());
//		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}

//˯���߳�����
class SleepBlocked implements Runnable{

	public void run() {
		try{
			TimeUnit.SECONDS.sleep(100);  //����ʱ��100�룻
		}catch(InterruptedException e){
			//e.printStackTrace();
			System.out.println("Interrupted for bloack Sleep");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

//IO����
class IOBlocked implements Runnable {

	//����������
	private InputStream in;
	
	//���췽��
	public IOBlocked(InputStream is ) { in = is; };
	
	public void run() {
		try{
			System.out.println("Watring for read()");
			in.read();
		}catch (IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted for bloack I/O. " + in.getClass().getName());
			} else {
				throw new RuntimeException(e);
			}
		}
		System.out.println( "Exiting IOBlocked.run()");
	}
}

//ͬ������
class SynchronizedBlocked implements Runnable{
	
	public synchronized void f(){
		while(true)
			Thread.yield();
	}
	
	public SynchronizedBlocked(){
		new Thread(){
			public void run(){
				f();
			}
		}.start();
	}
	
	public void run() {
		System.out.println("Try to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}
}

