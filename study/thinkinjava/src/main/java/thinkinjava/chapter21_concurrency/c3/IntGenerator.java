package thinkinjava.chapter21_concurrency.c3;


/**
 * 功能描述：偶数生成者  
 * (为了将EvenCheck与我们各自类型的的生成器解耦，定义偶数生成者抽象类)
 * @author  NICK
 *
 */
public abstract class IntGenerator {
	
	private volatile boolean canceled = false ;
	
	public abstract int next();
	
	//设置canceled状态
	public void cancel() { canceled = true; }
	
	//查看对象是否取消
	public boolean isCanceled() { return canceled; }
}
