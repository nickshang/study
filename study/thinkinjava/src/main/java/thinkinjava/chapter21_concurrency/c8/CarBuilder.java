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
 * 功能描述：汽车制造类
 * @author NICK
 *
 */
public class CarBuilder {
	public static void main(String[] args) throws InterruptedException {
		
		//1、底盘汽车队列、完成汽车汽车队列
		CarQueue chassisQueue = new CarQueue(),finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//2、机器人集合
		RobotPool robotPool = new RobotPool();
		exec.execute(new EngineRobot(robotPool));
		exec.execute(new DriveTrainRobot(robotPool));
		exec.execute(new WhellRobot(robotPool));
		
		//3、装配工
		exec.execute(new Assembler(chassisQueue,finishingQueue, robotPool));
		
		//4、汽车完工报道
		exec.execute(new Reporter(finishingQueue));
		
		//5、汽车底盘制造
		exec.execute(new ChassisBuilder(chassisQueue));
		
		TimeUnit.SECONDS.sleep(7);
		exec.shutdownNow();
	}
}

/**
 * 功能描述：汽车对象
 * @author NICK
 *
 */
class Car {
	
	//ID
	private final int id ;
	
	//引擎 、动力传动系统、车轮
	private boolean engine = false, driveTrain = false, wheels = false;
	
	//初始化
	public Car(int idn) { id = idn; }
	
	/**
	 * 获取ID
	 * @return
	 */
	public synchronized int getID() {
		return id;
	}
	
	/**
	 * 功能描述：添加引擎
	 */
	public synchronized void addEngine() {
		engine = true;
	}
	
	/**
	 * 功能描述：添加传动系统
	 */
	public synchronized void addDriveTrain() {
		driveTrain = true;
	}
	
	/**
	 * 功能描述：添加车轮
	 */
	public synchronized void addWheels() {
		wheels = true;
	}
	
	public synchronized String toString() {
		return "Car " + id + "[engine:" + engine +",driveTrain:" + driveTrain +",wheels:" + wheels + "]";
	}
}

/**
 * 功能描述：汽车队列
 * @author NICK
 *
 */
class CarQueue extends LinkedBlockingQueue<Car> {}


/**
 * 功能描述：底盘制作类
 * @author NICK
 *
 */
class ChassisBuilder implements Runnable{
	
	//汽车队列
	private CarQueue carQueue;
	
	//汽车编号
//	private volatile int counter = 0;
	
	AtomicInteger atomici = new AtomicInteger();
	
	/**
	 * 功能描述：构建汽车
	 * @param cq
	 */
	public ChassisBuilder(CarQueue cq) { carQueue = cq; }
	
	public void run() {
		try{
			while(!Thread.interrupted()) {
				
				TimeUnit.MILLISECONDS.sleep(500);
				
				//制作底盘
				Car c = new Car(atomici.addAndGet(1));
				System.out.println("ChassisBuilder create " + c );
				
				//放入汽车队列
				carQueue.put(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "底盘制作完成" );
	}
}

/**
 * 功能描述：装配工
 * @author NICK
 *
 */
class Assembler implements Runnable {
	
	//底盘队列、完成装配队列
	private CarQueue chasisQueue, finishingQueue;
	
	//汽车
	private Car car;
	
	//机器人装配集合
	private RobotPool robotPool;
	
	//循环栅栏
	private CyclicBarrier barrier = new CyclicBarrier(4);
	
	/**
	 * 初始化
	 * @param cq 汽车队列
	 * @param fp 完成创建队列
	 * @param rp 机器人对象
	 */
	public Assembler(CarQueue cq, CarQueue fp, RobotPool rp ) {
		chasisQueue = cq;
		finishingQueue = fp;
		robotPool = rp;
	}

	/**
	 * 功能描述：返回汽车
	 * @return
	 */
	public Car car(){ return car; }
	
	/**
	 * 功能描述：返回循环栅栏
	 * @return
	 */
	public CyclicBarrier barrier(){ return barrier; }
	
	/**
	 * 功能描述：任务
	 */
	public void run() {
		try{
			while(!Thread.interrupted()){
				
				//Blocks until chassis is available
				//阻塞直到底盘队列中有数据（有效）
				car = chasisQueue.take();  
				
				//Hire robots to perform work
				//雇佣机器人完成工作
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
 * 功能描述：记录者
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
		
		System.out.println("报道结束!");
	}
}

/**
 * 功能描述：机器人抽象类
 * @author NICK
 *
 */
abstract class Robot implements Runnable {
	
	private RobotPool pool;
	
	/**
	 * 功能描述：机器人
	 * @param p
	 */
	public Robot(RobotPool p) { pool = p; }
	
	/**
	 * 功能描述：装配工
	 */
	protected Assembler assembler;
	
	/**
	 * 功能描述：分配装配工
	 * @param assembler
	 * @return
	 */
	public Robot assignAssembler(Assembler assembler){
		this.assembler = assembler;
		return this;
	}
	
	/**
	 * 是否占用
	 */
	private boolean engage = false;
	
	/**
	 * 占用
	 */
	public synchronized  void engage() {
		engage = true;
		notifyAll();
	}
	
	/**
	 * 装配任务
	 */
	abstract protected void performService();
	
	/**
	 * 线程任务
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
	 * 功能描述：完成装配任务，释放装配工，释放机器人
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
 * 功能描述：引擎装配工
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
 * 功能描述：传动系统机器人
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
 * 功能描述：车轮机器人
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
 * 功能描述：机器人集合
 */
class RobotPool {
	
	/**
	 * 机器人集合
	 */
	private Set<Robot> pool = new HashSet<Robot>();
	
	/**
	 * 功能描述：添加机器人->唤醒其他技术
	 * @param r
	 */
	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}
	
	/**
	 * 功能描述：雇佣机器人
	 * @param robotType 机器人类型 
	 * @param d 装配工
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
	 * 功能描述：释放机器人
	 * @param r
	 */
	public synchronized void release(Robot r) { add(r); }
		
}