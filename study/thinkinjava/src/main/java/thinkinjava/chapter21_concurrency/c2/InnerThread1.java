package thinkinjava.chapter21_concurrency.c2;

/**
 * 功能描述：将线程类隐藏在内部
 * @author NICK
 *
 */
public class InnerThread1 {

	private Thread inner;
	
	public InnerThread1(String name){
		inner = new Thread( new Inner( name ) );
		inner.start();
	}
	
	//成员内部类
	private class Inner extends Thread{
		
		public Inner(String name ){
			super(name);
		}
		
		private int countDown = 5;

		@Override
		public void run() {
//			super.run();
			while( countDown-- > 0 ){
				System.out.println( this );
				Thread.yield();
			}
		}

		@Override
		public String toString() {
			return getName() + " ,countDown:" + countDown;
//			return super.toString();
		}
		
	}
	
	
	
}
