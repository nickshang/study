package thinkinjava.chapter21_concurrency.c2;


/**
 * 功能描述：实现Runable接口，实现线程的自我管理
 * @author NICK
 *
 */
public class SelfManaged  implements Runnable{

	private int countDown = 5;
	
	private Thread t = new Thread(this);
	
	public SelfManaged(){
		t.start();
	}
	
	@Override
	public String toString() {
		return t.getName() + ",counDwon: " + countDown;
//		return t.getName() + ",counDwon: " + countDown;
//		return super.toString();
	}


	public void run() {
		while ( countDown-- > 0 ){
			System.out.println( this );
			Thread.yield();
		}
	}

	public static void main(String[] args) {
		for ( int i =0; i < 5; i++ ){
			SelfManaged s = new SelfManaged();
		}
		
	}
	
}
