package thinkinjava.chapter15_generator.c2;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @��������ʵ��һ����������
 * ����������Ҫһ���ض������Ͷ�����б�
 * ÿ�ε������ϵ�select()����
 * ���Ϳ������ѡ��һ��Ԫ������
 * �������ϣ���Դ˹���һ������Ӧ����������͵Ķ�
 * 
 * �Ĺ��ߣ�����Ҫ�õ�����
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��24�� ����3:40:45
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��4��24�� ����3:40:45
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class RandomList<T> {
	
	private ArrayList<T> storage = new ArrayList<T>();
	
	private Random rand = new Random(47);
	
	public void add(T t){
		storage.add(t);
	}
	
	public T select(){
		return storage.get(rand.nextInt(storage.size()));
	}
	
	public static void main(String[] args) {
		RandomList<String> t1 = new RandomList<String>();
		
		for(String s :  "c c++  c# java go scala".split(" ") ){
			t1.add(s);
		}
		
		for(int i = 0; i < 10; i++){
			System.out.print( t1.select() + " ");
		}
		System.out.print("\n");
		
		RandomList<Double> t2= new RandomList<Double>();

		Double[] d = {11.1, 22.3, 33.4, 44.5, 55.6};
		for(Double s : d){
			t2.add(s);
		}
		
		for(int i = 0; i < 4; i++){
			System.out.print( t2.select() + " ");
		}
		
		System.out.print("\n");

		RandomList<Integer> t3= new RandomList<Integer>();

		int[] in = {11, 22, 33, 44, 55};
		for(int s : in){
			t3.add(s);
		}
		
		for(int i = 0; i < 4; i++){
			System.out.print( t3.select() + " ");
		}
		
	}
}
