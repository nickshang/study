package thinkinjava.chapter15_generator.c5;

/**
 * 
 * @类描述： 类型同配声明问题
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月29日 下午4:06:50
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class GenericTest <T> {

	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	public static void main(String[] args) {
	
		GenericTest <? extends Object>  t2 =   new GenericTest <String>();
		
		t2.setT(null);
		
		System.out.println(t2.getT());
		
		// 编译错误
		// the method setT(capture#3-of ? extends Object) in the type GenericTest<capture#3-of ? extends 
		// Object> is not applicable for the arguments (String)
//		t2.setT( new T1());
		
		System.out.println(t2.getT());
	}
	
}
