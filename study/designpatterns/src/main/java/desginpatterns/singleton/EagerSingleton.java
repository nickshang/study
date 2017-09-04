package desginpatterns.singleton;

/**
 * 
 * @类描述：饿汉式单例类
 * </br>
 * 当类被加载时，静态变量instance会被初始化，
 * 此时类的私有构造函数会被调用，单例类的唯一实例将被创建。
 * 如果使用饿汉式单例来实现负载均衡器LoadBalancer类的设计，
 * 则不会出现创建多个单例对象的情况，可确保单例对象的唯一性。
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月7日 上午9:57:14
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class EagerSingleton {
	
	/**
	 * 
	 * @描述:私有化构造函数
	 */
	private EagerSingleton(){
	}
	
	/**
	 * 私有静态变量私有化
	 */
	private final static EagerSingleton instance = new EagerSingleton();
	
	/**
	 * 
	 * @描述: 定义静态方法
	 * @返回类型 EagerSingleton
	 * @创建人 NICK
	 * @创建时间 2016年4月7日 上午9:58:22
	 * @since
	 * @throws
	 */
	public final static EagerSingleton getInstance(){
		return instance;
	}
}
