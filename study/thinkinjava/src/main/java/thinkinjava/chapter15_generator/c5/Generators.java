package thinkinjava.chapter15_generator.c5;

import thinkinjava.net.mindview.util.Generator;

import java.util.List;
import java.util.Queue;

public class Generators {
	
	public static <T> List<T> fill( List<T> q, Generator<T>  c , int n){
		for( int i = 0; i < n; i++ ){
			q.add( c.next() );
		}
		return q;
	}
	
	
	public static <T> Queue<T> fill( Queue<T> q, Generator<T>  c , int n){
		for( int i = 0; i < n; i++ ){
			q.add( c.next() );
		}
		
		return q;
	}
}