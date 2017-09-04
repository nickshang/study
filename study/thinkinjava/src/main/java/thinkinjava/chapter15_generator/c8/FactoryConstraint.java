package thinkinjava.chapter15_generator.c8;


/**
 * 
 * @�����������Ͳ��� -> ��ʾ����������� ->  ���뷺�ͱ�ǩ 
 * ͨ��ͬ������ӹ�������->��ʵ�ֽӿڣ���������
 * �ڷ������ʼ��ʱ�����÷������ͬʱ�����ݷ�����Ĺ�������->������ͨ��ͨ��������ȡ���ʵ��
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��30�� ����1:45:08
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class FactoryConstraint<T> {
	
	/**
	 * �������ͱ�ǩ
	 */
	private T x;
	
	/**
	 * 
	 * Title:��ʼ��
	 * Description:ͨ���ӿڷ�ʽ�����ýӿڷ���ʵ�����������ͱ�ǩ -> 
	 * Ҫ�������ʵ������ʵ�ֻ�����Ϸ�ʽ�������ͱ�ǩ
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
 * @���������������͵Ľӿ�
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
interface IFactory<T> {
	
	public T create();
}

/**
 * 
 * @�������� ������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
class F1 {
	public static class Factory implements IFactory<F1> {
			public F1 create(){
				return new F1(); 
			}
	}
}
