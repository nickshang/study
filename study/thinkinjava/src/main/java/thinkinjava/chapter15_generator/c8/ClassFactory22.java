package thinkinjava.chapter15_generator.c8;

import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @类描述：泛型檫除补偿 -> 内置工厂模式 -> 产生泛型类型标签
 * 使用类型标签与反射来创建一个方法，使用inInstance()的参数版本来创建
 * 某个对象。
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月30日 下午1:25:53
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class ClassFactory22<T> {
	
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
	public ClassFactory22(Class<T> t,Object ... initargs){
//		try {
//			x = t.newInstance();
//		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
//		}
		
		try {
//			Class cls = Class.forName( t.getName() );
//			Class[] cs = new Class[initargs.length];
//			for(int i = 0; i < initargs.length; i++ ){
//				cs[i] = initargs[i].getClass();
//			}
//			Constructor<T> c = cls.getConstructor( cs );
//			x = c.newInstance(initargs);
			
			Class[] cs = new Class[initargs.length];
			for(int i = 0; i < initargs.length; i++ ){
				cs[i] = initargs[i].getClass();
			}
			
			x = (T)Class.forName( t.getName() ).getConstructor( cs ).newInstance(initargs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public T get(){
		return this.x;
	}

	public static void main(String[] args) {
		ClassFactory22<Person> f = new ClassFactory22<>(Person.class,28,"nick");
		System.out.println("age = " + f.get().getAge());
		System.out.println("name = " + f.get().getName());
	}
	
}

class Person{
	
	private Integer age;
	
	private String name;
	
	public Person(Integer age ,String name){
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

