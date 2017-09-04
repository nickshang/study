package thinkinjava.chapter15_generator.c9;

import java.util.Arrays;
import java.util.List;

public class GenericReading {
	static <T> T readExact(List<T> list){
		return list.get(0);
		
	}
	
	static List<Apple> apples = Arrays.asList(new Apple());
	
	static List<Fruit> fruits = Arrays.asList(new Fruit());
	
	static void f1(){
		Apple a = readExact(apples);
		Fruit f = readExact(fruits);
		f = readExact(apples);
	}
	
	public static void main(String[] args) {
//		java.util.String;
		
		int a = 5;
		int b = 3;
		
		int max = a - ((a-b)&((a-b)>>31));
		
		// byte short chart int long double
		System.out.println( "max:" + Short.MAX_VALUE );
		System.out.println( "min:" + Short.MIN_VALUE );
		System.out.println( 1<<15);
		System.out.println( 1<<8);
		
	}
	
	
}

class Fruit { }

class Apple extends Fruit { }


