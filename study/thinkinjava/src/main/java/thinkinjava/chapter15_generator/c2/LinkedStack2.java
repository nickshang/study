package thinkinjava.chapter15_generator.c2;

/**
 * 
 * @��������ʵ��һ����ջ��
 * 		 [�Ƴ�Node���Ͳ��������޸Ĵ���֤���ڲ�����Է����ⲿ��ķ���]
 * 		 ��̬�ڲ��಻�ܷ����ⲿ�Ĳ�������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��24�� ����11:30:46
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��4��24�� ����11:30:46
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class LinkedStack2<T> {
	
		
	
	/***
	 * 
	 * @�������� �ڲ��ڵ����
	 * @�����ˣ�NICK
	 * @mail sjshang@tsingsoft.com
	 * @����ʱ�䣺2016��4��24�� ����11:36:06
	 * @version v1.0
	 * @see [nothing]
	 * @bug [nothing]
	 * @Copyright ���������¿Ƽ��ɷ����޹�˾
	 */
	private class Node {
		T item;
		Node next;
		Node() { item = null; next = null;}
		Node(T item,Node next){
			this.item = item;
			this.next = next;
		}
		boolean end(){ return item == null && next == null; }; 			//end sentinel ĩ���ڱ�LinkedStack.java
	}
	
	/**
	 * ��һ��Ԫ��
	 */
	private Node top = new Node();
	
	/**
	 * 
	 * @����: ��ջ�����Ԫ��
	 * @param t
	 * @�������� void
	 * @������ NICK
	 * @�޸ı�ע
	 * @since
	 * @throws
	 */
	public void put(T t){
		top = new Node(t,top);
		// ����ʵ�ַ�ʽ
//		if(top.item == null){
//			top.item = t;
//		}else {
//			while(top.next != null){
//				Node node = new Node(t,null);
//				top.next = node;
//			}
//		}
	}
	
	/**
	 * 
	 * @����: ��ȡջ����Ԫ��
	 * @return
	 * @�������� T
	 * @������ NICK
	 * @����ʱ�� 2016��4��24�� ����12:01:53
	 * @�޸ı�ע
	 * @since
	 * @throws
	 */
	public T peek(){ return top.item;}
	
	
	/**
	 * 
	 * @����:����ջ����Ԫ��
	 * @return
	 * @�������� T 
	 * @������ NICK
	 * @����ʱ�� 2016��4��24�� ����12:02:17
	 * @since
	 * @throws
	 */
	public T pop(){
		T t = null;
		if(!top.end()){
			t = top.item;
		} 
		top = top.next;
		return t;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
