package thinkinjava.chapter15_generator.c5;

/**
 * 
 * @�������� ����ͬ����������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��29�� ����4:06:50
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
		
		// �������
		// the method setT(capture#3-of ? extends Object) in the type GenericTest<capture#3-of ? extends 
		// Object> is not applicable for the arguments (String)
//		t2.setT( new T1());
		
		System.out.println(t2.getT());
	}
	
}
