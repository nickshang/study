package thinkinjava.chapter21_concurrency.c6.test;

/**
 * ��������������
 * @author NICK
 *
 */
public class Chopstick {
	
	/**
	 * �Ƿ�����
	 */
	private boolean taken = false;
	
	/**
	 * ����������������ӣ��������������״̬�����󽫵ȴ���֪�����ӹ��𡣣�
	 * @throws InterruptedException
	 */
	public synchronized  void take() throws InterruptedException {
		while(taken)
			wait();
		taken = true;
	}
	
	/**
	 * ��������������ݷ���
	 */
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
