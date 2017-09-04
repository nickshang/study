package thinkinjava.chapter15_generator.c8;


/**
 * 
 * @类描述：泛型檫除补偿 -> 使用模板方法 -> 生成泛型类型标签
 * 建立泛型抽象类->指定泛型抽象类初始化时必须调用create()方法生产泛型类型标签
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年5月1日 下午1:46:01
 * @修改人：NICK
 * @修改时间：2016年5月1日 下午1:46:01
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public abstract class GenericCreate<T> {
	
	/**
	 * 泛型类型标签
	 */
	protected T elemnet;
	
	/**
	 * 
	 * Title:初始化
	 * Description: 泛型类型标签在初始化时候调用create()方法完成
	 */
	public GenericCreate(){
		this.elemnet = create();
	}
	
	/**
	 * 
	 * @描述:生产泛型类型标签
	 * @return
	 * @返回类型 T
	 * @since
	 * @throws
	 */
	public abstract  T create();
}

/**
 * 
 * @类描述： 实例化类
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
class X {} 

/**
 * 
 * @类描述：
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
class XGeneric extends GenericCreate<X>{

	@Override
	public X create() {
		return new X();
	}
	
	public void f(){
		System.out.println(this.elemnet.getClass().getName() );
	}
	
} 