package thinkinjava.chapter15_generator.c2;

/**
 * 
 * @类描述：简单泛型 [对象持有一个具体类对象]
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月24日 上午9:59:28
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class Holder1 {
	
	private Automobile a;
	
	public Holder1(){
		this.a = a;
	}

	public Automobile get(){
		return a;
	}
	
}

class Automobile {}
