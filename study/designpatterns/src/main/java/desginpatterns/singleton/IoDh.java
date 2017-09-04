package desginpatterns.singleton;


/**
 * 
 * @��������Initialization Demand Holder (IoDH)�ļ�����
 * </br></br>
 *  ͨ��ʹ��IoDH�����Ǽȿ���ʵ���ӳټ��أ��ֿ��Ա�֤�̰߳�ȫ��
 *  ��Ӱ��ϵͳ���ܣ���ʧΪһ����õ�Java���Ե���ģʽʵ�ַ�ʽ��
 *  ����ȱ�����������Ա����������أ��ܶ�����������Բ�֧��IoDH����
 *  </br></br>
 *  ���ھ�̬��������û����ΪSingleton�ĳ�Ա����ֱ��ʵ��������������ʱ����ʵ����Singleton��
 *  ��һ�ε���getInstance()ʱ�������ڲ���HolderClass���ڸ��ڲ����ж�����һ��static���͵ı���instance��
 *  ��ʱ�����ȳ�ʼ�������Ա��������Java���������֤���̰߳�ȫ�ԣ�ȷ���ó�Ա����ֻ�ܳ�ʼ��һ�Ρ�
 *  ����getInstance()����û���κ��߳���������������ܲ�������κ�Ӱ�졣</br>
 * @�����ˣ�NICK
 * @����ʱ�䣺2016��4��7�� ����9:38:22
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @mail sjshang@tsingsoft.com
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class IoDh {
	
	private IoDh(){
	}
	
	private static class holdClass {
		public final static  IoDh instance = new IoDh();
	}
	
	public static IoDh getInstance(){
		return holdClass.instance;
	}
	
	public static void main(String[] args) {
		IoDh d1 = IoDh.getInstance();
		IoDh d2 = IoDh.getInstance();
		System.out.println( d1 == d2 );
	}
}
