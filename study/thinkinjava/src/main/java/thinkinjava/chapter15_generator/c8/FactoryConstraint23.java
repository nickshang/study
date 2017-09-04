package thinkinjava.chapter15_generator.c8;


/**
 * 
 * @类描述：泛型补偿 -> 显示工厂解决方案 ->  引入泛型标签 
 * 通过同在类添加工厂方法->（实现接口）生产对象。
 * 在泛型类初始化时在设置泛型类的同时，传递泛型类的工厂方法->泛型类通过通过工厂获取类的实例
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月30日 下午1:45:08
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class FactoryConstraint23<T> {
	
	/**
	 * 泛型类型标签
	 */
	private T x;
	
	/**
	 * 
	 * Title:初始化
	 * Description:通过接口方式，调用接口方法实例化泛型类型标签 -> 
	 * 要求具体类实例生产实现或者组合方式生产泛型标签
	 * @param f
	 */
	public <F extends IFactory2<T>>  FactoryConstraint23(F f,Object o){
		x = f.create2(o);
	}
	
	
	@Override
	public String toString() {
		return "class name:" + x.getClass().getSimpleName() + " class  " + x;
	}


	public static void main(String[] args) {
		FactoryConstraint23<F2> f = new FactoryConstraint23<F2>( new F2.Factroy(), "nick");
		System.out.println(f);
	}
}


/**
 * 
 * @类描述：生产泛型的接口
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
interface IFactory2<T> {
	
	public T create2(Object o);
	
}


class F2 {
	
	private String s ;
	
	public F2(String s){
		this.s = s;
	}
	
	@Override
	public String toString() {
		return this.s;
	}

	public static class Factroy implements IFactory2<F2>{
		
		@Override
		public F2 create2(Object o) {
			return new F2((String)o);
		}
		
	} 
}