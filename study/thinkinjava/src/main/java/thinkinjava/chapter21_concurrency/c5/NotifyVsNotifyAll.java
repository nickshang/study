package thinkinjava.chapter21_concurrency.c5;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ����������ѧϰnotify��notifyall����
 * @author NICK
 *
 */
public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		
		//�̹߳�����
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5 ; i++ ){
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		
		//��ʱ���񹤾�
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			boolean prod = true;
			public void run(){
				if(prod){
					System.out.println(" notify() ");
					Task.blocker.prod();
					prod = false;
				}else{
					System.out.println(" notifyAll() ");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();  //��ʱ��ȡ��
		System.out.println("\n Timer canceled");
		
		
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task2.blocker.prodAll");
		Task2.blocker.prodAll();
		
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\n Shutting down");
		exec.shutdown();
		
	}
}

/**
 * ������
 * @author NICK
 *
 */
class Blocker {
	
	/*
	 * ��������ǰ�߳�û�б���ϣ���ǰ�ֳ��ȴ�����������ʱ�����ǰ�̵߳�����
	 */
	synchronized void waittingCall(){  
		try{
			while(!Thread.interrupted()){
				wait();
				System.out.println( " �߳�����:" + Thread.currentThread() );
			}
		}catch (Exception e) {
			System.out.println( "�쳣��!" );
		}
	}
	
	//
	synchronized void prod(){ notify(); }
	
	//
	synchronized void prodAll(){ notifyAll(); }
}

//������1
class Task implements Runnable{
	
	//��̬������
	static Blocker blocker = new Blocker();
	
	//����
	public void run(){
		
		//���õȴ�����
		blocker.waittingCall();
	}
}

//������2
class Task2 implements Runnable{
	//��̬������
	static Blocker blocker = new Blocker();
	
	//����
	public void run(){
		
		//���õȴ�����
		blocker.waittingCall();
	}
}