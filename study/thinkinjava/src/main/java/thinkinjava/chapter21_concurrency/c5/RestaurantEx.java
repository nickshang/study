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
 *  ���BusBoy�ࡣ���ϲ˺󣬽�����������
 *  1����WaitPerson�ϲ���ɺ󣬱��BusBoy��notified=true֪ͨ�Ѿ�����ϲˣ����Խ�����������
 *  2����BusBoy��������Ϻ󣬱��WaitPerson��notified=true֪ͨ�Ѿ�������ϣ������ϲˣ�
 *  3����������Ƿ��Ѿ��ϲ���ϣ�����ϲ�δ��Ͻ�������������
 * @author NICK
 *
 */
public class RestaurantEx {
	
	public MealEx mealEx ;
	
	public ExecutorService exec;
	
	public WaitPersonEx  waitPersonEx =  new WaitPersonEx(this);
	
	public ChefEx  chefEx =  new ChefEx(this);
	
	public BusBoy  busBoyEx =  new BusBoy(this);
	
	public RestaurantEx(String name){
		System.out.println( name + "������ʼӪҵ!" );
		exec = Executors.newCachedThreadPool();
		exec.execute(waitPersonEx);
		exec.execute(chefEx);
		exec.execute(busBoyEx);
	}
	
	public static void main(String[] args) {
		new RestaurantEx("���˽�");
	}
	
}

/**
 * ʳ��
 */
class MealEx {
	
	//ʳ����
	private final int orderNum;
	
	/**
	 * �������������캯��
	 * @param i ʳ�����
	 */
	public MealEx(int i) { this.orderNum = i; }

	/**
	 * ��д
	 */
	public String toString(){ return "Meal " + orderNum; }
}

/**
 * ��������:�͹���๤
 * @author NICK
 *
 */
class BusBoy implements Runnable{
	
	/**
	 * �͹�
	 */
	private RestaurantEx restaurantEx ; 
	
	/**
	 * �Ƿ���
	 */
	public  boolean notified;  
	
	
	/**
	 * �Ƿ���
	 */
	public volatile  MealEx  mealEx;  
	
	/**
	 * ���캯��
	 * @param r
	 */
	public BusBoy(RestaurantEx r ){
		this.restaurantEx = r;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				
				//���ϲ˺�֪ͨ�͹���๤��������
				synchronized (this) {
					while(!notified)
						wait();
					notified = false;
				}
				
				//����͵�
				System.out.println("��ʼ����͵㣺" + mealEx );
				System.out.println("��������͵㣺" + mealEx );
				
				//֪ͨ����Ա
				synchronized (restaurantEx.waitPersonEx) {
					restaurantEx.waitPersonEx.notified = true; 
					restaurantEx.waitPersonEx.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println( "�����̱߳��ж�!" );
		}
	}
}

/**
 * ��������������Ա
 * @author NICK
 *
 */
class WaitPersonEx implements Runnable{
	
	/**
	 * �����������͹�
	 */
	private RestaurantEx restaurantEx;
	
	/**
	 * �����������Ƿ���
	 */
	public  boolean notified;
	
	/**
	 * �������������캯��
	 * @param r
	 */
	public WaitPersonEx(RestaurantEx r ){ this.restaurantEx = r; }
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				
				//ʳ��Ϊ�գ��ȴ�
				synchronized (this) {
					
					//Ϊ��ֹ�ڲ���Ӧ���У�ĳ���������������WaitPerson������ʱ����ͻȻ���Ҳ����Ŷ���
					//Ψһ���յķ�����Ӧ while(conditions) wait();
					//�������Ա�֤���˳��ȴ�ѭ��֮ǰ���������õ����㡣(Ҫ��ǡ����ͬ���ڲ�������ֹ��ʧ�źŵĿ����Եĳ������)
					//����������յ�����ĳ�����֪ͨ��������������޹�ϵ(��������ʹ��notifyAll()ʱ���ܷ��������һ��)
					//�������㰲ȫ�˳��ȴ�ѭ��֮ǰ��������������˱߹���������ȷ��������ط��ȴ���״̬��
				
					//�͵�Ϊ�� �ȴ�
					while (restaurantEx.mealEx == null) {  
						wait();
					}
				}

				//���Ͳ͵�
				System.out.println("��ʼ���Ͳ͵㣺" + restaurantEx.mealEx.toString() );
				TimeUnit.SECONDS.sleep(1);
				System.out.println("�������Ͳ͵㣺" + restaurantEx.mealEx.toString() );
			
				
				//֪ͨ����(��������֪ͨ����Ա�Ѿ��������)
				synchronized (restaurantEx.busBoyEx) {
					restaurantEx.busBoyEx.notified = true;
					restaurantEx.busBoyEx.mealEx = restaurantEx.mealEx;
					restaurantEx.busBoyEx.notifyAll();
				}
				
				
				//�Ͳ���� ������ ֪ͨ��ʦ
				synchronized (restaurantEx.chefEx) {
					if( restaurantEx.mealEx != null ){
						restaurantEx.mealEx = null;
						restaurantEx.chefEx.notifyAll();
					}
				}
				
				synchronized (this) {
					if( !notified )
						wait(); //�ȴ������߳̽���������
					notified = false;
				}
			}
		} catch (InterruptedException e) {
			System.out.println( "����Ա�̱߳��ж�!" );
		}
	}
}

/**
 * ������������ʦ
 * @author NICK
 *
 */
class ChefEx implements Runnable{
	
	/**
	 * �͹�
	 */
	private RestaurantEx restaurantEx;
	
	/**
	 * ��������
	 */
	private int count = 0;
	
	//���캯��
	public ChefEx(RestaurantEx  r ) { this.restaurantEx = r; }
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				
				//ʳ�ﲻΪ�գ��̵߳ȴ�
				synchronized (this) {
					
					//Ϊ��ֹ�ڲ���Ӧ���У�ĳ���������������WaitPerson������ʱ����ͻȻ���Ҳ����Ŷ���
					//Ψһ���յķ�����Ӧ while(conditions) wait();
					//�������Ա�֤���˳��ȴ�ѭ��֮ǰ���������õ����㡣(Ҫ��ǡ����ͬ���ڲ�������ֹ��ʧ�źŵĿ����Եĳ������)
					//����������յ�����ĳ�����֪ͨ��������������޹�ϵ(��������ʹ��notifyAll()ʱ���ܷ��������һ��)
					//�������㰲ȫ�˳��ȴ�ѭ��֮ǰ��������������˱߹���������ȷ��������ط��ȴ���״̬��
					
					//�͵�Ϊnull �ȴ�
					while(restaurantEx.mealEx != null  ){
						wait();
					}
				}
								
				//����10�����̹߳�����ֹͣ
				if(++count > 10){
					System.out.println("���ţ�ֹͣӪҵ!");
					restaurantEx.exec.shutdownNow();
					//return ;
				}
				
				//�����͵�
				System.out.println("��ʼ�����͵㣺"   );
				TimeUnit.SECONDS.sleep(1);
				
				//֪ͨ����Ա�߳�
				synchronized (restaurantEx.waitPersonEx) {
					restaurantEx.mealEx = new MealEx(count);
					System.out.println("���������͵㣺" + restaurantEx.mealEx.toString() );
					restaurantEx.waitPersonEx.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println( "��ʦ�̱߳��ж�!" );
		}
	}
}
