package thinkinjava.chapter15_generator.c3.coffee;

/**
 * 
 * @类描述：使用组合实现Fibonacci数列的迭代器
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月25日 上午8:53:50
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class IterableFibonacci2 extends Fibonacci {
	
	private int n;
	
	public IterableFibonacci2(int n ) {
		this.n = n;
	}
	
	public class  Iterator<Integer> {
		public boolean hasNext() {
			return n > 0;
		}
	
		public Integer next(){
			n--;
			return (Integer) IterableFibonacci2.this.next();
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}

	};
	
    public Iterator<Integer> iterator(){
    	return  new Iterator<Integer>();
    }
    
    public static void main(String[] args) {
    	IterableFibonacci2  iterableFibonacci = new IterableFibonacci2(10);
    	while(iterableFibonacci.iterator().hasNext()){
    		System.out.println( iterableFibonacci.iterator().next());
    	}
	}
}
