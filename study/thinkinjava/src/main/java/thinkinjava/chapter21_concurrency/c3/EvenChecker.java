package thinkinjava.chapter21_concurrency.c3;

import java.util.concurrent.ExecutorService;

/**
 * �������������������񣬼��������ż������Ч��
 * @author Nick
 *
 */
public class EvenChecker implements Runnable {
	
	//������
	private thinkinjava.chapter21_concurrency.c3.IntGenerator intGenerator;
	
	//�߳�ID
	private final int id;
	
	/**
	 * �ֳ����캯��
	 * @param intGenerator
	 * @param id
	 */
	public EvenChecker(thinkinjava.chapter21_concurrency.c3.IntGenerator intGenerator , int id){
		this.intGenerator = intGenerator;
		this.id = id;
	}

	/**
	 * �ֳ�����
	 */
	public void run() {
		//���ż������Ч��
		while( !intGenerator.isCanceled() ){
			int i = intGenerator.next();
			if (i % 2 != 0){
				System.out.println( "i:" + i + ",����ż�� ,�˳�!" );
				intGenerator.cancel();
			}
		}
	}
	
	/**
	 * 
	 * @param
	 * @param count
	 */
	public static void test(thinkinjava.chapter21_concurrency.c3.IntGenerator gp, int count){
		System.out.println( "press control + c to exit;" );
		ExecutorService exec = java.util.concurrent.Executors.newCachedThreadPool();
		for ( int i = 0; i < count; i++ ) {
			exec.execute( new EvenChecker(gp ,count ) );
		}
	}

	public static void test(thinkinjava.chapter21_concurrency.c3.IntGenerator gp){
		test(gp, 10);
	}
}
