package thinkinjava.chapter15_generator.c3.coffee;

import java.util.Iterator;

/**
 * 
 * @类描述：实现基于Iterable接口的Fibonacci数列的迭代器
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月25日 上午8:53:50
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
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
