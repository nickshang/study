package thinkinjava.chapter15_generator.c8;

import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @�������������߳����� -> ���ù���ģʽ -> �����������ͱ�ǩ
 * ʹ�����ͱ�ǩ�뷴��������һ��������ʹ��inInstance()�Ĳ����汾������
 * ĳ������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��30�� ����1:25:53
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class ClassFactory22<T> {
	
	/**
	 * �������ͱ�ǩ
	 */
	private T x;
	
	/**
	 * 
	 * Title: ��ʼ��
	 * Description: ����class����newInstance()�����������ͱ�ǩ
	 * @param t ��
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

