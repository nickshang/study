package com.shang.pool;

/**
 * 功能描述：对象池的对象重用
 * @author NICK
 *
 */
public class Pair {
	
	//第一个值
	public int firstValue;
	
	//第二个值
	public int secondValue;
	
	//引用对象池中下一个对象
	private Pair next;
	
	//同步锁
	private static final Object sPoolSync = new Object();
	
	//对象池中第一个可用的对象
	private static Pair sPool;
	
	//当前对象池大小
	private static int sPoolSize = 0;
	
	//对象池最大对象个数
	private static final int MAX_POOL_SIZE = 3;
	
	/**
	 * 只能用obtain()方法获取对象
	 */
	private Pair(){
	}
	
	/**
	 * 返回回收的对象或者当对象池为空时创建一个新对象
	 * @return
	 */
	public static Pair obtain(){
		synchronized (sPoolSync) {
			//obtain方法首先会检查
			//对象池中是否包含任何存在的对象，并删除列表中的第一个元素然后返
			//回它。如果对象池为空，obtain方法会创建一个新的对象
			if ( sPool != null ) {
				Pair m = sPool;  //定义一个变量m = 线程池第一个变量
				sPool = m.next;  //现场一个变量等于 = m.
				System.out.println( "m.next:" + m.next );
				m.next = null;   //
				sPoolSize--;
				System.out.println("sPoolSize--:"+sPoolSize);
				return m;
			}
		}
		return new Pair();
	}
	
	/**
	 * 回收改对象。调用该方法后需要释放所所有该对象的实例引用
	 */
	public void recyle(){
		synchronized(sPoolSync){
			if (sPoolSize < MAX_POOL_SIZE) {
				next = sPool;
				sPool = this;
				sPoolSize++;
				System.out.println("sPoolSize++:"+sPoolSize);
			}
		}
	}
}
