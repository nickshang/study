package thinkinjava.chapter15_generator.c7;

/**
 * 
 * @��������ʵ�ַ��͵����ͱ�ǩ����
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��29�� ����5:27:16
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
