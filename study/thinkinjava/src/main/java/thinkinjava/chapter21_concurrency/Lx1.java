package thinkinjava.chapter21_concurrency;

/**
 * ������������ϰ1
 * 
 * @author NICK
 *
 */
public class Lx1 implements Runnable{
	
	private static int count = 0;

	/**
	 * �����������̱߳�ʾID
	 */
	private final int id = count++;
	
	public Lx1(){
		System.out.println( " ---------��ϰ1 Start--------------");
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
