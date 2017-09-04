package thinkinjava.chapter21_concurrency;

/**
 * 功能描述：练习1
 * 
 * @author NICK
 *
 */
public class Lx1 implements Runnable{
	
	private static int count = 0;

	/**
	 * 功能描述：线程标示ID
	 */
	private final int id = count++;
	
	public Lx1(){
		System.out.println( " ---------练习1 Start--------------");
	}
	
	public void run() {
		
		System.out.println( "id:" + id + " message" );
		Thread.yield();
		
		System.out.println( "id:" + id + " message" );
		Thread.yield();
		
		System.out.println( "id:" + id + " message" );
		Thread.yield();
		
		System.out.println( "id:" + id + " end" );
		return ;
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++ ) {
			Thread t = new Thread(new Lx1());
			t.start();
		}
	}
}
