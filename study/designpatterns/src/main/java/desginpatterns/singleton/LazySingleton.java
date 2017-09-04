package desginpatterns.singleton;

/**
 * 
 * @��������
 *   ˫�ؼ������(Double-Check Locking)
 * 
 * 	 <br/><br/>
 *   ������ĳһ˲���߳�A���߳�B���ڵ���getInstance()��������ʱinstance����Ϊnullֵ������ͨ��instance == null���жϡ�
 *   ����ʵ����synchronized�������ƣ��߳�A����synchronized�����Ĵ�����ִ��ʵ���������룬�߳�B�����Ŷӵȴ�״̬��
 *   ����ȴ��߳�Aִ����Ϻ�ſ��Խ���synchronized�������롣����Aִ�����ʱ���߳�B����֪��ʵ���Ѿ�������
 *   �����������µ�ʵ�������²��������������Υ������ģʽ�����˼�룬�����Ҫ���н�һ���Ľ���
 *   ��synchronized���ٽ���һ��(instance == null)�жϣ����ַ�ʽ��Ϊ˫�ؼ������(Double-Check Locking)��
 *   
 *   <br/><br/>
 *   ��Ҫע����ǣ����ʹ��˫�ؼ��������ʵ������ʽ�����࣬��Ҫ�ھ�̬��Ա����instance֮ǰ�������η�volatile��
 *   ��volatile���εĳ�Ա��������ȷ������̶߳��ܹ���ȷ�����Ҹô���ֻ����JDK 1.5�����ϰ汾�в�����ȷִ�С�
 *        
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��7�� ����10:06:36
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class LazySingleton {
	
	Object o = new Object();

	private static volatile LazySingleton instance = null;
	
	private LazySingleton(){
	}
	
	public static LazySingleton getInstance(){
		
//		���̲߳���ȫ
//		if(instance == null){
//			instance = new LazySingleton();
//		}
		
		// ��һ���ж�  
		if(instance == null){
			// ��������� 
			synchronized(LazySingleton.class){
				// �ڶ����ж�  	
				if(instance == null){
					instance = new LazySingleton();  //��������ʵ��  
				}
			}
		}
		
		return instance;
	}
}
