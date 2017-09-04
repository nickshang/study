package thinkinjava.chapter21_concurrency.c5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ����������������������������
 * һ�����꣬��һ����ʦ��һ������Ա��
 * ����Ա����ȳ�ʦ׼����ʳ���ʦ׼����ʳ���֪ͨ����Ա������Ա����ź󣬷��ؼ����ȴ���
 * 
 * ��ʦ������������
 * ����Ա������������
 * 
 * ����jdk��void notifyAll()���������������Щ�ڸö����ϵ���wait()�������̵߳�����״̬��
 * �÷���ֻ����ͬ��������ͬ�����ڲ����á������ǰ�̲߳��Ƕ������ó����ߣ��÷����׳�һ��java.lang.IllegalMonitorStateException �쳣��
 * 
 * @author NICK
 *
 */
public class Restaurant {
	
	public Meal meal;
	
	public ExecutorService exec;
	
	public WaitPerson  waitPerson =  new WaitPerson(this);
	
	public Chef  chef =  new Chef(this);
	
	public Restaurant(String name){
		System.out.println( name + "������ʼӪҵ!" );
		exec = Executors.newCachedThreadPool();
		exec.execute(waitPerson);
		exec.execute(chef);
	}
	
	public static void main(String[] args) {
		new Restaurant("���˽�");
	}
}

/**
 * ʳ��
 */
class Meal {
	
	//ʳ����
	private final int orderNum;
	
	/**
	 * �������������캯��
	 * @param i ʳ�����
	 */
	public Meal(int i) { this.orderNum = i; }
	
	
	/**
	 * ��дtoString()����
	 */
	public String toString(){ return "Meal " + orderNum; }
}


/**
 * ��������������Ա
 * @author NICK
 *
 */
class WaitPerson implements Runnable{
	
	/**
	 * �����������͹�
	 */
	private Restaurant restaurant;
	
	/**
	 * ����������
	 * @param r
	 */
	public WaitPerson(Restaurant r ){ this.restaurant = r; }
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				
				//�͵�Ϊ�գ��ȴ�
				synchronized (this) {
					
					//Ϊ��ֹ�ڲ���Ӧ���У�ĳ���������������WaitPerson������ʱ����ͻȻ���Ҳ����Ŷ���
					//Ψһ���յķ�����Ӧ while(conditions) wait();
					//�������Ա�֤���˳��ȴ�ѭ��֮ǰ���������õ����㡣(Ҫ��ǡ����ͬ���ڲ�������ֹ��ʧ�źŵĿ����Եĳ������)
					//����������յ�����ĳ�����֪ͨ��������������޹�ϵ(��������ʹ��notifyAll()ʱ���ܷ��������һ��)
					//�������㰲ȫ�˳��ȴ�ѭ��֮ǰ��������������˱߹���������ȷ��������ط��ȴ���״̬��
					while( restaurant.meal == null ){  
						wait();
					}
				}
				
				//�Ͳ�
				System.out.println("��ʼ�Ͳͣ�" + restaurant.meal.toString() );
				
				//֪ͨ��ʦ
				synchronized (restaurant.chef) {
					if( restaurant.meal != null ){
						TimeUnit.SECONDS.sleep(1);
						restaurant.meal = null;
						restaurant.chef.notifyAll();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("����Ա�̱߳��ж�!");
		}
	}
}

/**
 * ������������ʦ
 * @author NICK
 *
 */
class Chef implements Runnable{
	
	private Restaurant restaurant;
	
	private int count = 0;
	
	//���캯��
	public Chef(Restaurant  r ) { this.restaurant = r; }
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			try {
				//ʳ�ﲻΪ�գ��̵߳ȴ�
				synchronized (this) {
					//Ϊ��ֹ�ڲ���Ӧ���У�ĳ���������������WaitPerson������ʱ����ͻȻ���Ҳ����Ŷ���
					//Ψһ���յķ�����Ӧ while(conditions) wait();
					//�������Ա�֤���˳��ȴ�ѭ��֮ǰ���������õ����㡣(Ҫ��ǡ����ͬ���ڲ�������ֹ��ʧ�źŵĿ����Եĳ������)
					//����������յ�����ĳ�����֪ͨ��������������޹�ϵ(��������ʹ��notifyAll()ʱ���ܷ��������һ��)
					//�������㰲ȫ�˳��ȴ�ѭ��֮ǰ��������������˱߹���������ȷ��������ط��ȴ���״̬��
					while(restaurant.meal != null ){
						wait();
					}
				}
				
				//����ʳ��
				
				//����10�����̹߳�����ֹͣ
				if(++count > 10){
					System.out.println("���ţ�ֹͣӪҵ!");
					restaurant.exec.shutdownNow();
//					return ;
				}
				
				//��������
				System.out.println(" ��������! ");
				
				//�����͵�-��֪ͨ����Ա
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					TimeUnit.SECONDS.sleep(1);
					System.out.println("����ʳ�" + restaurant.meal.toString() );
					restaurant.waitPerson.notifyAll();
				}
				
			} catch (InterruptedException e) {
				System.out.println("��ʦ�̱߳��ж�!");
			}
		}
	}
}
