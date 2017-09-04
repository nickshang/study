package thinkinjava.chapter21_concurrency.c7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 功能描述：对象线程池
 * @author NICK
 *
 * @param <T>
 */
public class Pool<T> {
	
	// 线程池数量
	private int size;
	
	// 对象存储集合
	private List<T> items = new ArrayList<T>();
	
	// 对象状态：是否被检出
	private volatile boolean[] checkedOut;
	
	// 是否允许检出
	private Semaphore  available;
	
	// 初始化
	public Pool(Class<T> classObject, int size) {
		this.size = size;
		checkedOut = new boolean[size];
		available = new Semaphore(size,true);
		for(int i = 0; i < size; i++){
			try {
				items.add(classObject.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 功能描述：检出对象
	 * @return
	 * @throws InterruptedException
	 */
	public T checkOut() throws InterruptedException{
		available.acquire();
		return getItem();
	}
	
	/**
	 * 功能描述：放回对象
	 * @return
	 * @throws InterruptedException
	 */
	public void checkIn(T t) throws InterruptedException{
		if( releaseItem(t) ) 
			available.release();  //释放一个许可，将其返回给信号量
	}
	
	/**
	 * 功能描述：获取对象
	 * @return
	 */
	public synchronized T getItem(){
		for ( int i = 0; i < size; i++ ){
			if( !checkedOut[i] ){
				checkedOut[i] = true;
				return items.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 功能描述：放回对象
	 * @return
	 */
	public synchronized boolean releaseItem(T t){
		//是否存在对象
		for(int i = 0; i < size; i++){
			if( t == items.get(i)){
				checkedOut[i] = false;
				return true;
			}
		}
		return false;
	}
}
