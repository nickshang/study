package thinkinjava.chapter21_concurrency.c3;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：产生序列类，多线程测试
 * @author NICK
 *
 */
public class SerialNumberChecker {

	//线程数量
	private static final int SIZE = 10;
	
	//创建圆点集合类
	private static CircularSet serials = new CircularSet(1000);
	
	//线程管理器
	private static java.util.concurrent.ExecutorService exec = 
		java.util.concurrent.Executors.newCachedThreadPool();
	
	//线程类
	static class SerialChecker implements Runnable {
		public void run(){
			while(true){
				int serial = thinkinjava.chapter21_concurrency.c3.SerialNumberGenerator.nextSerialNumber();
				if( serials.contains( serial ) ) {
					System.out.println("[重复数据]Duplicate: " + serial );
					System.exit(0);
				}
				serials.add( serial );
			}
		}
	}
	
	//主类
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		for ( int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker() );
			if( args.length > 0 ){
				TimeUnit.SECONDS.sleep(1);
				System.out.println("No duplicates detected");
				System.exit(0);
			}
		}
	}
}
