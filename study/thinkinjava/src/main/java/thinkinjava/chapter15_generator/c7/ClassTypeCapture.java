package thinkinjava.chapter15_generator.c7;

/**
 * 
 * @类描述：实现泛型的类型标签引入
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月29日 下午5:27:16
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class ClassTypeCapture <T> {
	
	private Class<T> c;
	
	public ClassTypeCapture(Class<T> c){
		this.c = c;
	}
	
	public boolean f(Object ob){
		return c.isInstance(ob);
	}
}

class Building{}

class House extends Building{}
