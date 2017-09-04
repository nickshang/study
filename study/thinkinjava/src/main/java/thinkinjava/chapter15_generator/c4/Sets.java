package thinkinjava.chapter15_generator.c4;

import java.util.Set;
import java.util.HashSet;

public class Sets {
	
	/**
	 * 
	 * @����: ʵ�ֲ���
	 * @param a ����
	 * @param b ���� 
	 * @return  ��������
	 * @�������� Set<T>
	 * @������ NICK
	 * @����ʱ�� 2016��4��26�� ����10:20:33
	 * @since
	 * @throws
	 */
	public static <T> Set<T> union(Set<T> a, Set<T> b){
		 Set<T> result = new HashSet<T>(a);
		 result.addAll(b);
		 return result ;
	}
	
	/**
	 * 
	 * @����: ʵ�ֽ���
	 * @param a ����
	 * @param b ���� 
	 * @return  ��������
	 * @�������� Set<T>
	 * @������ NICK
	 * @����ʱ�� 2016��4��26�� ����10:20:33
	 * @since
	 * @throws
	 */
	public static <T> Set<T> intersection(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		 return result ;
	}
	
	/**
	 * 
	 * @����:ʵ�ֲ
	 * @param a ����
	 * @param b ����
	 * @return
	 * @�������� Set<T>
	 * @������ NICK
	 * @����ʱ�� 2016��4��26�� ����10:25:55
	 * @since
	 * @throws
	 */
	public static <T> Set<T> difference(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a);  				// �ظ�����һ�ݲ��ı�ԭ����
		result.removeAll(b);
		 return result ;
	}
	
	

}
