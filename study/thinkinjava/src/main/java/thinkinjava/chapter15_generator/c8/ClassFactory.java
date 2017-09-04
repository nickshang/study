package thinkinjava.chapter15_generator.c8;

/**
 * 
 * @类描述：泛型檫除补偿 -> 内置工厂模式 -> 产生泛型类型标签
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月30日 下午1:25:53
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class ClassFactory<T> {
	
	/**
	 * 泛型类型标签
	 */
	private T x;
	
	/**
	 * 
	 * Title: 初始化
	 * Description: 利用class对象newInstance()生产泛型类型标签
	 * @param t 类
	 */
	public ClassFactory(Class<T> t){
		try {
			x = t.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public T get(){
		return this.x;
	}
	
	
	
}

