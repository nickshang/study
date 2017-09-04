package thinkinjava.chapter21_concurrency.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ����������ʵ��ԭ���Ը���
 * getValue()����ԭ���ԣ�����return i ȷʵ��ԭ���Բ���,
 * ����ȱ��ͬ��ʹ����ֵ�����ڲ��ȶ����м�״̬����ȡ������iҲ����volatile,�����ڿ��������⡣
 * @author NICK
 *
 */
public class AtomicityTest  implements Runnable{
	
	private volatile int i = 0;
	//private int i = 0;  //ȱ��volatile���ڿ���������
	
	public int getValue() { return i; }  //ȱ��ͬ����ʹ�����ڲ��ȶ����м�״̬��ȡ
	//public synchronized int getValue() { return i; }
	
	public synchronized void evenIncremnet() {
		i++;
		i++;
	}
	
	public void run() {
		while(true)
			 evenIncremnet();
	}
	
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while(true){
			int val = at.getValue();
			if (val % 2 != 0){
				System.out.println( "�������� val:" + val );
				System.exit(0);
			}
		}
	}
}
