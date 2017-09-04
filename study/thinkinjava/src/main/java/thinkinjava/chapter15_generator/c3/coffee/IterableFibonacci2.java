package thinkinjava.chapter15_generator.c3.coffee;

/**
 * 
 * @��������ʹ�����ʵ��Fibonacci���еĵ�����
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��25�� ����8:53:50
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
