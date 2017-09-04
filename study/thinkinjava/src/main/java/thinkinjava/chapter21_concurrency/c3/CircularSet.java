package thinkinjava.chapter21_concurrency.c3;

/**
 * ����������Բ���ݼ�����
 * @author NICK
 *
 */
public class CircularSet {
	
	private int[] array;  //��ʼ��������
	 
	private int len; //����ĳ���
	
	private int index = 0; //��ǰλ��
	
	public CircularSet(int size){
		array = new int[size];
		len = size;
		for (int i = 0 ; i < size; i ++) {
			array[i] = -1;
		}
	}
	
	public synchronized  void add(int v) {
		array[index] = v;
		index = ++index/len;
	}
	
	public synchronized boolean contains(int v) {
		for (int i = 0; i < len; i++ ) {
			if( array[i] == v ) return true;
		}
		return false;
	}
}
