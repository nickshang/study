package thinkinjava.chapter21_concurrency.c2;

import java.util.concurrent.ExecutorService;

/**
 * 功能描述：测试线程捕获异常
 * 不能捕获从线程中逃逸的线程:一旦有Exception有逃出任务的run方法，他就会向外传播到控制台，出发采用特殊的步骤捕获这样异常
 * @author NICK
 *
 */
public class ExceptionThread implements Runnable{

	public void run() {
		throw new RuntimeException("x");
	}
	
	public static void main(String[] args) {
		 
		//使用执行器捕获异常
		try{
			ExecutorService exec = java.util.concurrent.Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
			exec.shutdown();
		}catch (Exception e) {
			System.out.println( "捕获异常 " ); //永远不会执行
			e.printStackTrace();
		}
		
		//使用线程捕获异常
		try{
			Thread t1 = new Thread( new ExceptionThread() ); 
			t1.start();
		}catch (Exception e) {
			System.out.println( "捕获异常 " ); //永远不会执行
			e.printStackTrace();
		}
	}
}
