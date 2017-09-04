package thinkinjava.chapter21_concurrency.c5;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ���������� 
 * 1��ѧϰʹ��BlockingQueue;
 * 2����һ���������������һ��������˾��һ������˾Ĩ���ͣ�����һ���ڻ�����˾���������
 * @author NICK
 *
 */
public class ToastOMatic {
	
	public static void main(String[] args) throws InterruptedException {
		
		//������ɵ���˾�洢����
		ToastQueue<Toast>  toasterQueue = new ToastQueue<Toast>();
		
		//������ɵ���˾->Ϳ����͵���˾�洢����
		ToastQueue<Toast>  butterQueue = new ToastQueue<Toast>();
		
		//Ϳ����͵���˾->Ϳ���������˾�洢����
		ToastQueue<Toast>  jammQueue = new ToastQueue<Toast>();
		
		
		//�½��������񣬷ֲ�һ��������˾��һ������˾Ĩ���ͣ�����һ���ڻ�����˾�������
		Toaster toaster = new Toaster(toasterQueue);
		
		Butter butter = new Butter(toasterQueue,butterQueue);
		
		Jamm jamm = new Jamm(butterQueue,jammQueue);
		
		
		//�����̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute( toaster );
		exec.execute( butter );
		exec.execute( jamm );
		
		//�ر��߳�
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
		
	}
}

/**
 * ����˾Ĩ�����߳�����
 * @author Think
 *
 */
class Jamm implements Runnable{

	/**
	 * �����������˾������͵���˾�Ĵ洢����
	 */
	private ToastQueue<Toast>  butterQueue;
	
	/**
	 * ��Ϳ����͵���˾Ϳ��������˾�Ĵ洢����
	 */
	private ToastQueue<Toast>  jammQueue;
	
	
	/**
	 * �½�һ������˾Ϳ����������
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
				
				System.out.println("����˾Ϳ�������:" + t );
			}	
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("��˾Ĩ�����߳̽���!");
	}
}


/**
 * ����˾Ĩ�����߳�����
 * @author NICK
 *
 */
class Butter implements Runnable{
	
	/**
	 * ������ɵ���˾�Ĵ洢����
	 */
	private ToastQueue<Toast>  toasterQueue;
	
	/**
	 * �����������˾������͵���˾�Ĵ洢����
	 */
	private ToastQueue<Toast>  butterQueue;
	
	/**
	 * �½�һ������˾Ĩ�����߳�����
	 * @param toasterQueue ������ɵ���˾�Ĵ洢����
	 * @param butterQueue �����������˾������͵���˾�Ĵ洢����
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
				
				System.out.println("����˾Ϳ�������:" + t );
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


/**
 * ������˾�߳�����
 * @author NICK
 *
 */
class  Toaster implements Runnable{
	
	/**
	 * ��˾���У��������õ���˾���������
	 */
	private ToastQueue  toasterQueue;
	
	/**
	 * �½�һ����˾�������񣬽����õ���˾����toasterQueue����
	 * @param toasterQueue  
	 */
	public Toaster( ToastQueue<Toast>  toasterQueue){
		this.toasterQueue = toasterQueue;
	}

	/**
	 * ������˾���
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
				
				System.out.println( "���������˾: " + t );
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println( "���������˾�߳̽���!" );
	}
}


/**
 * ������������˾��������
 * @author NICK
 *
 */
class ToastQueue<Toast>  extends LinkedBlockingQueue<Toast>{  }

/**
 * ������������˾����
 * @author Think
 *
 */
class Toast{
	
	//���
	private final String id ;
	
	//���캯��
	public Toast(String id){
		this.id  = id ;
	}
	
	//3��״̬ û��Ϳ����������Ĩ������ɣ���������
	public enum Status {
		
		DRY("1"),BUTTERED("2"),JAMMED("3");
		
		private String id;
		
		Status(String id){
			this.id = id;
		}
		
		public String toString(){ return  id;}
	};
	
	//Ĭ����˾״̬
	private Status status = Status.DRY;
	
	//Ĩ����
	public void butter(){ status = Status.BUTTERED; };
	
	//�����
	public void jamm(){ status = Status.JAMMED; };
	
	//������˾״̬
	public Status getStatus(){ return status; }
	
	//����ID
	public String getID() { return id; }
	
	//����toString()����
	public String toString(){ return "��˾(ID:+" + id + "," + "״̬:" +  status + ")"; }
	
}
