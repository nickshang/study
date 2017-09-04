package desginpatterns.singleton;

/**
 * 
 * @������������ʽ������
 * </br>
 * ���౻����ʱ����̬����instance�ᱻ��ʼ����
 * ��ʱ���˽�й��캯���ᱻ���ã��������Ψһʵ������������
 * ���ʹ�ö���ʽ������ʵ�ָ��ؾ�����LoadBalancer�����ƣ�
 * �򲻻���ִ����������������������ȷ�����������Ψһ�ԡ�
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��7�� ����9:57:14
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class EagerSingleton {
	
	/**
	 * 
	 * @����:˽�л����캯��
	 */
	private EagerSingleton(){
	}
	
	/**
	 * ˽�о�̬����˽�л�
	 */
	private final static EagerSingleton instance = new EagerSingleton();
	
	/**
	 * 
	 * @����: ���徲̬����
	 * @�������� EagerSingleton
	 * @������ NICK
	 * @����ʱ�� 2016��4��7�� ����9:58:22
	 * @since
	 * @throws
	 */
	public final static EagerSingleton getInstance(){
		return instance;
	}
}
