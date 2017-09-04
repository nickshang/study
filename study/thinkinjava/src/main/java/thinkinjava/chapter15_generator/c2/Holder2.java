package thinkinjava.chapter15_generator.c2;

/**
 * 
 * @类描述：类对象持有一个通用的基类对象
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月24日 上午10:51:35
 * @修改人：NICK
 * @修改时间：2016年4月24日 上午10:51:35
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class Holder2 {
	private Object a;
	
	public Holder2(Object a){
		this.a = a;
	}
	
	public Object get(){
		return a;
	}
}
