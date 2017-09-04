package thinkinjava.chapter21_concurrency.c3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：临界区测试
 * @author NICK
 */

public class CriticalSection {
	
	static void testApporaches(PairManager pman1,PairManager pman2){
		
		//1.线程管理器 
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//2.圆点控制器
		PairManipulator pm1 = new PairManipulator(pman1),
						pm2 = new PairManipulator(pman2);
		
		//3.圆点检查类
		PairCheck 
			pcheck1 = new PairCheck(pman1),
			pcheck2 = new PairCheck(pman2);
		
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		
		//4.主线程睡眠
		try{
			TimeUnit.MICROSECONDS.sleep(1111500);
		}catch (InterruptedException e) {
			System.out.println( "sleep interruped");
		}
		
		System.out.println( "pm1:" + pm1 +"\npm2:" + pm2);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		PairManager pman1 = new PairManager1(),
					pman2 = new PairManager2();
		testApporaches(pman1,pman2);
	}		
}

/**
 * 圆点类(no thread-safe one)
 * @author NICK
 *
 */
class Pair {
	
	private int x,y;
	
	public Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public Pair() { this(0,0); }
	public int getX() { return x; }
	public int getY() { return y; }
	public void incremnetX() { x++; }
	public void incremnetY() { y++; }
	
	public String toString() {
		return "x:" + x + ",y:" + y;
	}
	
	public class PariValueNotEqualExcetion extends RuntimeException {
		public PariValueNotEqualExcetion() {
			super("pair values not equal :" + Pair.this  );
		}
	}
	
	public synchronized void checkState(){
		if ( x != y ){
			throw new PariValueNotEqualExcetion();
		}
	}
}

//圆点管理抽象类
abstract class PairManager {
	
	//检查次数
	AtomicInteger checkCount = new AtomicInteger(0); 
	
	//圆点对象
	protected Pair p = new Pair();
	
	//同步存储器
	private List<Pair> storage = java.util.Collections.synchronizedList(new ArrayList<Pair>());
	
	//获取圆点数据（复制返回）
	public synchronized Pair getPair(){
		return new Pair(p.getX(),p.getY());
	}
	
	//存储圆点数据
	protected void store(Pair p){
		storage.add(p);
		try{
			TimeUnit.MICROSECONDS.sleep(50);
		}catch (InterruptedException e) {
			System.out.print(e);
		}
	}
	
	//自增
	public abstract void incremnet();
}


//圆点数据管理1 (显示锁)
class PairManager1 extends PairManager {
	
	private final java.util.concurrent.locks.Lock lock = new ReentrantLock();
	
	public synchronized void incremnet(){
		lock.lock();
		try{
			Pair temp;
			p.incremnetX();
			p.incremnetY();
			
//			try{   //确保increment方法长时间持有这把锁  
//	           Thread.sleep(10000);  
//	        }catch(Exception e){}  
	            
			temp = getPair();
			store(temp);
		}finally{
			lock.unlock();
		}
	}
}


//圆点数据管理2 (显示锁)
class PairManager2 extends PairManager {
	
	private final java.util.concurrent.locks.Lock lock = new ReentrantLock();
	
	public void incremnet(){
		Pair temp = null;
		lock.lock();
		try{
			p.incremnetX();
			p.incremnetY();
			temp = getPair();
		}finally {
			lock.unlock();
		}
		store(temp);
	}
}

//
////圆点数据管理1 (利用synchronized同步方法)
//class PairManager1 extends PairManager {
//	
//	public synchronized void incremnet(){
//		Pair temp;
//		p.incremnetX();
//		p.incremnetY();
//		temp = getPair();
//		store(temp);
//	}
//}
//
//
////圆点数据管理2 (利用临界区进行受限资源管理)
//class PairManager2 extends PairManager {
//	
//	public void incremnet(){
//		Pair temp;
//		synchronized(this){
//			p.incremnetX();
//			p.incremnetY();
//			temp = getPair();
//		}
//		store(temp);
//	}
//}

//圆点控制器
class PairManipulator implements Runnable {

	//圆点管理器
	private PairManager pm;
	
	//初始化
	public PairManipulator(PairManager pm){
		this.pm = pm;
	}
	
	//任务
	public void run() {
		while(true)
			pm.incremnet();
	}
	
	public String toString(){
		return "Pair:" +  pm.getPair() +",checkCount = " + pm.checkCount.get();
	}
}

//圆点检查类
class PairCheck implements Runnable {
	
	//圆点管理器
	private PairManager pm;
	
	//圆点检查类
	public PairCheck(PairManager pm){
		this.pm = pm;
	}
	
	//线程任务
	public void run() {
		while(true){
			pm.checkCount.incrementAndGet();
//			pm.getPair().checkState();
			
			try {  
                pm.getPair().checkState();  
//                System.out.println("check Thread get the lock");  
            } catch (Exception e) {  
                e.printStackTrace();  
                System.out.println(pm.getClass().getSimpleName());  
                break;  
            }  
            
		}
	}
}