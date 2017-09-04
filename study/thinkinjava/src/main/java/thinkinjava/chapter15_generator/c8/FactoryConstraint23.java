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
public class FactoryConstraint23<T> {
	
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
 * @���������������͵Ľӿ�
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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