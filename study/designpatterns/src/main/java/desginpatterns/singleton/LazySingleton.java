package desginpatterns.singleton;

/**
 * 
 * @类描述：
 *   双重检查锁定(Double-Check Locking)
 * 
 * 	 <br/><br/>
 *   假如在某一瞬间线程A和线程B都在调用getInstance()方法，此时instance对象为null值，均能通过instance == null的判断。
 *   由于实现了synchronized加锁机制，线程A进入synchronized锁定的代码中执行实例创建代码，线程B处于排队等待状态，
 *   必须等待线程A执行完毕后才可以进入synchronized锁定代码。但当A执行完毕时，线程B并不知道实例已经创建，
 *   将继续创建新的实例，导致产生多个单例对象，违背单例模式的设计思想，因此需要进行进一步改进，
 *   在synchronized中再进行一次(instance == null)判断，这种方式称为双重检查锁定(Double-Check Locking)。
 *   
 *   <br/><br/>
 *   需要注意的是，如果使用双重检查锁定来实现懒汉式单例类，需要在静态成员变量instance之前增加修饰符volatile，
 *   被volatile修饰的成员变量可以确保多个线程都能够正确处理，且该代码只能在JDK 1.5及以上版本中才能正确执行。
 *        
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月7日 上午10:06:36
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class LazySingleton {
	
	Object o = new Object();

	private static volatile LazySingleton instance = null;
	
	private LazySingleton(){
	}
	
	public static LazySingleton getInstance(){
		
//		多线程不安全
//		if(instance == null){
//			instance = new LazySingleton();
//		}
		
		// 第一重判断  
		if(instance == null){
			// 锁定代码块 
			synchronized(LazySingleton.class){
				// 第二重判断  	
				if(instance == null){
					instance = new LazySingleton();  //创建单例实例  
				}
			}
		}
		
		return instance;
	}
}
