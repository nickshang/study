package thinkinjava.chapter15_generator.c3.coffee;

import java.util.Iterator;

public class Fibonacci7 {

	private int count = 0;
	private int m;
	public Integer next() { return fib(count++); }
	private int fib(int n) {
		if(n < 2) return 1;
		return fib(n - 2) + fib(n - 1);
	}
	public Fibonacci7() {}
	public Fibonacci7(int m) { this.m = m; }
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			public boolean hasNext() { return m > 0; }
			public Integer next() {
				m--;
				return Fibonacci7.this.next();
			}
			public void remove() { // Not implemented
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		Fibonacci7 gen = new Fibonacci7();
		for(int i = 0; i < 18; i++)
			System.out.print(gen.next() + " ");
		System.out.println();
		Iterator it = new Fibonacci7(20).iterator();
		while(it.hasNext())
			System.out.print(it.next() + " ");
	}
	
	
}
