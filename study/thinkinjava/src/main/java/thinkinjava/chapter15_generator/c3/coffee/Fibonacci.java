package thinkinjava.chapter15_generator.c3.coffee;

import thinkinjava.net.mindview.util.Generator;

public class Fibonacci implements Generator<Integer> {

	int count = 0;
	
	public int fib(int i){
		if( i <= 2 ) 
			return 1;
		return fib( i - 1) + fib( i - 2);
	}
	
	@Override
	public Integer next() {
		return fib(++count);
	}
	
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		for(int i = 0; i < 5; i++){
			System.out.print(f.next() + " " );
		}
	}

}
