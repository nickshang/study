package thinkinjava.chapter21_concurrency.c8;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ��������������������
 * @author NICK
 *
 */
public class CarBuilder {
	public static void main(String[] args) throws InterruptedException {
		
		//1�������������С����������������
		CarQueue chassisQueue = new CarQueue(),finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//2�������˼���
		RobotPool robotPool = new RobotPool();
		exec.execute(new EngineRobot(robotPool));
		exec.execute(new DriveTrainRobot(robotPool));
		exec.execute(new WhellRobot(robotPool));
		
		//3��װ�乤
		exec.execute(new Assembler(chassisQueue,finishingQueue, robotPool));
		
		//4�������깤����
		exec.execute(new Reporter(finishingQueue));
		
		//5��������������
		exec.execute(new ChassisBuilder(chassisQueue));
		
		TimeUnit.SECONDS.sleep(7);
		exec.shutdownNow();
	}
}

/**
 * ������������������
 * @author NICK
 *
 */
class Car {
	
	//ID
	private final int id ;
	
	//���� ����������ϵͳ������
	private boolean engine = false, driveTrain = false, wheels = false;
	
	//��ʼ��
	public Car(int idn) { id = idn; }
	
	/**
	 * ��ȡID
	 * @return
	 */
	public synchronized int getID() {
		return id;
	}
	
	/**
	 * �����������������
	 */
	public synchronized void addEngine() {
		engine = true;
	}
	
	/**
	 * ������������Ӵ���ϵͳ
	 */
	public synchronized void addDriveTrain() {
		driveTrain = true;
	}
	
	/**
	 * ������������ӳ���
	 */
	public synchronized void addWheels() {
		wheels = true;
	}
	
	public synchronized String toString() {
		return "Car " + id + "[engine:" + engine +",driveTrain:" + driveTrain +",wheels:" + wheels + "]";
	}
}

/**
 * ������������������
 * @author NICK
 *
 */
class CarQueue extends LinkedBlockingQueue<Car> {}


/**
 * ��������������������
 * @author NICK
 *
 */
class ChassisBuilder implements Runnable{
	
	//��������
	private CarQueue carQueue;
	
	//�������
//	private volatile int counter = 0;
	
	AtomicInteger atomici = new AtomicInteger();
	
	/**
	 * ������������������
	 * @param cq
	 */
	public ChassisBuilder(CarQueue cq) { carQueue = cq; }
	
	public void run() {
		try{
			while(!Thread.interrupted()) {
				
				TimeUnit.MILLISECONDS.sleep(500);
				
				//��������
				Car c = new Car(atomici.addAndGet(1));
				System.out.println("ChassisBuilder create " + c );
				
				//������������
				carQueue.put(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "�����������" );
	}
}

/**
 * ����������װ�乤
 * @author NICK
 *
 */
class Assembler implements Runnable {
	
	//���̶��С����װ�����
	private CarQueue chasisQueue, finishingQueue;
	
	//����
	private Car car;
	
	//������װ�伯��
	private RobotPool robotPool;
	
	//ѭ��դ��
	private CyclicBarrier barrier = new CyclicBarrier(4);
	
	/**
	 * ��ʼ��
	 * @param cq ��������
	 * @param fp ��ɴ�������
	 * @param rp �����˶���
	 */
	public Assembler(CarQueue cq, CarQueue fp, RobotPool rp ) {
		chasisQueue = cq;
		finishingQueue = fp;
		robotPool = rp;
	}

	/**
	 * ������������������
	 * @return
	 */
	public Car car(){ return car; }
	
	/**
	 * ��������������ѭ��դ��
	 * @return
	 */
	public CyclicBarrier barrier(){ return barrier; }
	
	/**
	 * ��������������
	 */
	public void run() {
		try{
			while(!Thread.interrupted()){
				
				//Blocks until chassis is available
				//����ֱ�����̶����������ݣ���Ч��
				car = chasisQueue.take();  
				
				//Hire robots to perform work
				//��Ӷ��������ɹ���
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WhellRobot.class, this);
				
				barrier.await();
				finishingQueue.put(car);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

/**
 * ������������¼��
 * @author NICK
 *
 */
class Reporter implements Runnable {
	
	private CarQueue carQueue;
	
	public Reporter(CarQueue cq){ carQueue = cq; }

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println(carQueue.take());
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("��������!");
	}
}

/**
 * ���������������˳�����
 * @author NICK
 *
 */
abstract class Robot implements Runnable {
	
	private RobotPool pool;
	
	/**
	 * ����������������
	 * @param p
	 */
	public Robot(RobotPool p) { pool = p; }
	
	/**
	 * ����������װ�乤
	 */
	protected Assembler assembler;
	
	/**
	 * ��������������װ�乤
	 * @param assembler
	 * @return
	 */
	public Robot assignAssembler(Assembler assembler){
		this.assembler = assembler;
		return this;
	}
	
	/**
	 * �Ƿ�ռ��
	 */
	private boolean engage = false;
	
	/**
	 * ռ��
	 */
	public synchronized  void engage() {
		engage = true;
		notifyAll();
	}
	
	/**
	 * װ������
	 */
	abstract protected void performService();
	
	/**
	 * �߳�����
	 */
	public void run() {
		try {
			powerDown();  //Wait until needed
			while(!Thread.interrupted()){
				
				performService();
				assembler.barrier().await(); //synchronize
				
				//We're done with that job...
				powerDown(); 
				
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch (BrokenBarrierException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println( this + " off");
	}
	
	/**
	 * �������������װ�������ͷ�װ�乤���ͷŻ�����
	 * @throws InterruptedException
	 */
	private synchronized void powerDown() throws InterruptedException {
		engage = false;
		assembler = null; //Disconnect from the Assembler
		
		//Put ourselvers back in the avaliable pool
		pool.release(this);
		
		while(engage == false )
			wait();
	}
	public String toString(){ return getClass().getName(); }
	
}

/**
 * ��������������װ�乤
 * @author NICK
 *
 */
class EngineRobot extends Robot {
	public EngineRobot(RobotPool pool){ super(pool);} 
	protected void performService() {
		System.out.println( this + " installing engine ");
		assembler.car().addEngine();
	}
}

/**
 * ��������������ϵͳ������
 * @author NICK
 *
 */
class DriveTrainRobot extends Robot {
	public DriveTrainRobot(RobotPool pool ){ super(pool); }
	protected void performService() {
		System.out.println( this + " installing DriveTrain");
		assembler.car().addDriveTrain();
	}
}

/**
 * �������������ֻ�����
 * @author NICK
 *
 */
class WhellRobot extends Robot {
	public WhellRobot(RobotPool pool){ super(pool); }
	protected void performService(){
		System.out.println( this  + " installing Wheels");
		assembler.car().addWheels();
	}
}

/**
 * ���������������˼���
 */
class RobotPool {
	
	/**
	 * �����˼���
	 */
	private Set<Robot> pool = new HashSet<Robot>();
	
	/**
	 * ������������ӻ�����->������������
	 * @param r
	 */
	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}
	
	/**
	 * ������������Ӷ������
	 * @param robotType ���������� 
	 * @param d װ�乤
	 * @throws InterruptedException
	 */
	public synchronized  void hire(Class<? extends Robot> robotType, Assembler d) 
		throws InterruptedException {
		for (Robot r : pool ){
			if(r.getClass().equals( robotType) ){
				pool.remove(r);
				r.assignAssembler(d);
				r.engage();
				return ;
			}
		}
		
		wait();
		hire(robotType,d); //Try again, recursively
	}
	
	/**
	 * �����������ͷŻ�����
	 * @param r
	 */
	public synchronized void release(Robot r) { add(r); }
		
}