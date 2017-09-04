package thinkinjava.chapter15_generator.c3.coffee;

import thinkinjava.net.mindview.util.Generator;

import java.util.Iterator;
import java.util.Random;


public class CoffeeGenerator implements Generator<Coffee> , Iterable<Coffee>{

	private Class[] types = {Latte.class, Mocha.class,
			Cappuccino.class, Americano.class,Breve.class};
	
	public CoffeeGenerator() {}
	
	private int size = 0 ;
	
	public CoffeeGenerator(int sz) { size = sz; }
	
	private final static Random read = new Random();
	
	@Override
	public Coffee next() {
		Coffee coffee = null;
		try{
			coffee = (Coffee)( types [ read.nextInt(types.length) ]).newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return coffee;
	}
	
	class CoffeeInterator implements Iterator<Coffee> {

		int count = size;
		
		@Override
		public boolean hasNext() {
			return count > 0 ;
		}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	};
	
	
	@Override
	public Iterator<Coffee> iterator() {
		return new CoffeeInterator();
	}

	public static void main(String[] args) {
		CoffeeGenerator gen = new CoffeeGenerator();
		for(int i = 0; i < 5; i++)
			System.out.println(gen.next());
		for(Coffee e : new CoffeeGenerator(5)) 
			System.out.println(e);
	}

}
