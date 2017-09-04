package thinkinjava.chapter15_generator.c8;


/**
 * 
 * @�������������߳����� -> ʹ��ģ�巽�� -> ���ɷ������ͱ�ǩ
 * �������ͳ�����->ָ�����ͳ������ʼ��ʱ�������create()���������������ͱ�ǩ
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��5��1�� ����1:46:01
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��5��1�� ����1:46:01
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public abstract class GenericCreate<T> {
	
	/**
	 * �������ͱ�ǩ
	 */
	protected T elemnet;
	
	/**
	 * 
	 * Title:��ʼ��
	 * Description: �������ͱ�ǩ�ڳ�ʼ��ʱ�����create()�������
	 */
	public GenericCreate(){
		this.elemnet = create();
	}
	
	/**
	 * 
	 * @����:�����������ͱ�ǩ
	 * @return
	 * @�������� T
	 * @since
	 * @throws
	 */
	public abstract  T create();
}

/**
 * 
 * @�������� ʵ������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
class X {} 

/**
 * 
 * @��������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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