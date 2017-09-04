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
public class FactoryConstraint<T> {
	
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
	public <F extends IFactory<T>>  FactoryConstraint(F f){
		x = f.create();
	}
	
	
	@Override
	public String toString() {
		return "class name:" + x.getClass().getSimpleName();
	}


	public static void main(String[] args) {
		FactoryConstraint<F1> f = new FactoryConstraint<F1>( new F1.Factory());
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
interface IFactory<T> {
	
	public T create();
}

/**
 * 
 * @类描述： 具体类
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
class F1 {
	public static class Factory implements IFactory<F1> {
			public F1 create(){
				return new F1(); 
			}
	}
}
