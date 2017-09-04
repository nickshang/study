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
public class RandomList2<T> {
	
	private ArrayList<T> storage = new ArrayList<T>();
	
	private Random rand = new Random(47);
	
	public void add(T t){
		storage.add(t);
	}
	
	public T select(){
		return storage.get(rand.nextInt(storage.size()));
	}
	
	public static void main(String[] args) {
		RandomList2<String> t1 = new RandomList2<String>();
		
		t1.add(new String("����1"));
		t1.add(new String("����2"));
		t1.add(new String("����3"));
		
		System.out.println(t1.select());
	}
}
