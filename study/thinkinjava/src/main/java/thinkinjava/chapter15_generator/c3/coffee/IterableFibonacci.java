package thinkinjava.chapter15_generator.c3.coffee;

import java.util.Iterator;

/**
 * 
 * @��������ʵ�ֻ���Iterable�ӿڵ�Fibonacci���еĵ�����
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��25�� ����8:53:50
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
	
	private int n;
	
	public IterableFibonacci(int n ) {
		this.n = n;
	}
	
    public Iterator<Integer> iterator(){
    	
    	return new Iterator<Integer>() {
    		public boolean hasNext() {
    			return n > 0;
    		}
    	
    		public Integer next(){
    			n--;
    			return IterableFibonacci.this.next();
    		}
    		
    		public void remove(){
    			throw new UnsupportedOperationException();
    		}

		};
    }
    
    public static void main(String[] args) {
    	IterableFibonacci  iterableFibonacci = new IterableFibonacci(10);
    	while(iterableFibonacci.iterator().hasNext()){
    		System.out.println( iterableFibonacci.iterator().next());
    	}
	}
}
