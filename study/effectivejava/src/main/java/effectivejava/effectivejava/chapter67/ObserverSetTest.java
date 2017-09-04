package effectivejava.effectivejava.chapter67;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 检验observerSet类型
 * @author Think
 *
 */
public class ObserverSetTest {

	public static void main(String[] args) {
		m3();
	}

	/**
	 * Observer  that uses background thread needlessly
	 */
	public static void m3(){
		ObserverSet<Integer> observerSet  = new ObserverSet<Integer>( new HashSet<Integer>() );

		observerSet.addObserver( new SetObserver<Integer>(){
			public void added(final ObserverSet<Integer> s, Integer e){
				System.out.println(s);
				if( e.intValue() == 23 ) {
					ExecutorService excutor = Executors.newSingleThreadScheduledExecutor();
					final SetObserver<Integer> setObserver = this;
					try {
						excutor.submit( new Runnable(){
							public void run(){
								s.removeObserver(setObserver);
							}
						}).get();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} catch (ExecutionException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		for(int i = 0; i < 99; i++)
			observerSet.add(i);
	}

	/**
	 * 添加观察着打印0-23的值，添加打印到23时.删除观察着
	 */
	public static void m2(){
		ObserverSet<Integer> observerSet  = new ObserverSet<Integer>( new HashSet<Integer>() );

		observerSet.addObserver( new SetObserver<Integer>(){
			public void added(ObserverSet<Integer> s, Integer e){
				System.out.println(s);
				if (e.intValue() == 23) s.removeObserver(this);
			}
		});

		for(int i = 0; i < 99; i++)
			observerSet.add(i);
	}

	/**
	 * 添加观察着打印0-9的值
	 */
	public static void m1(){
		ObserverSet<Integer> observerSet  = new ObserverSet<Integer>( new HashSet<Integer>() );

		observerSet.addObserver( new SetObserver<Integer>(){
			public void added(ObserverSet<Integer> s, Integer e){
				System.out.println(e);
			}
		});

		for(int i = 0; i < 99; i++)
			observerSet.add(i);
	}
}
