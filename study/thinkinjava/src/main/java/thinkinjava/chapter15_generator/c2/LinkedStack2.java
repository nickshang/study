package thinkinjava.chapter15_generator.c2;

/**
 * 
 * @类描述：实现一个堆栈类
 * 		 [移除Node类型参数，并修改代买，证明内部类可以访问外部类的方法]
 * 		 静态内部类不能访问外部的参数类型
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月24日 上午11:30:46
 * @修改人：NICK
 * @修改时间：2016年4月24日 上午11:30:46
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class LinkedStack2<T> {
	
		
	
	/***
	 * 
	 * @类描述： 内部节点对象
	 * @创建人：NICK
	 * @mail sjshang@tsingsoft.com
	 * @创建时间：2016年4月24日 上午11:36:06
	 * @version v1.0
	 * @see [nothing]
	 * @bug [nothing]
	 * @Copyright 北京清软创新科技股份有限公司
	 */
	private class Node {
		T item;
		Node next;
		Node() { item = null; next = null;}
		Node(T item,Node next){
			this.item = item;
			this.next = next;
		}
		boolean end(){ return item == null && next == null; }; 			//end sentinel 末端哨兵LinkedStack.java
	}
	
	/**
	 * 第一个元素
	 */
	private Node top = new Node();
	
	/**
	 * 
	 * @描述: 向栈中添加元素
	 * @param t
	 * @返回类型 void
	 * @创建人 NICK
	 * @修改备注
	 * @since
	 * @throws
	 */
	public void put(T t){
		top = new Node(t,top);
		// 链表实现方式
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
	 * @描述: 获取栈顶的元素
	 * @return
	 * @返回类型 T
	 * @创建人 NICK
	 * @创建时间 2016年4月24日 下午12:01:53
	 * @修改备注
	 * @since
	 * @throws
	 */
	public T peek(){ return top.item;}
	
	
	/**
	 * 
	 * @描述:弹出栈顶的元素
	 * @return
	 * @返回类型 T 
	 * @创建人 NICK
	 * @创建时间 2016年4月24日 下午12:02:17
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
