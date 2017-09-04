package thinkinjava.chapter15_generator.c4;

import java.util.Random;

/**
 * 
 * @��������ʵ��һ�����ַ���
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��25�� ����11:45:42
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class GenericMethods {
	
	public <T> void f(T t){
			System.out.println( t.getClass().getSimpleName() );
	}
	
	public <T> void f2(T t, String s){
		System.out.println( t.getClass().getSimpleName() + " s:" + s );
}
	
	public static void main(String[] args) {
		GenericMethods method = new GenericMethods();
//		method.f(1);
//		method.f(1f);
//		method.f(1d);
//		method.f("1");
//		method.f(new Random());
		
		method.f2(1,"s1");
		method.f2(1f,"s1");
		method.f2(1d,"s1");
		method.f2("1","s1");
		method.f2(new Random(),"s1");
		
	}
}
