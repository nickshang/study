package thinkinjava.chapter15_generator.c8;

/**
 * 
 * @�������������߳����� -> ���ù���ģʽ -> �����������ͱ�ǩ
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��30�� ����1:25:53
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class ClassFactory<T> {
	
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

