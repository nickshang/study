package thinkinjava.chapter21_concurrency.c3;

/**
 * 功能描述：圆数据集合类
 * @author NICK
 *
 */
public class CircularSet {
	
	private int[] array;  //初始化的数组
	 
	private int len; //数组的长度
	
	private int index = 0; //当前位置
	
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
