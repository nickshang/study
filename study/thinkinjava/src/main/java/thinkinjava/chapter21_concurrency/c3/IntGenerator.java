package thinkinjava.chapter21_concurrency.c3;


/**
 * ����������ż��������  
 * (Ϊ�˽�EvenCheck�����Ǹ������͵ĵ��������������ż�������߳�����)
 * @author  NICK
 *
 */
public abstract class IntGenerator {
	
	private volatile boolean canceled = false ;
	
	public abstract int next();
	
	//����canceled״̬
	public void cancel() { canceled = true; }
	
	//�鿴�����Ƿ�ȡ��
	public boolean isCanceled() { return canceled; }
}
