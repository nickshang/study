package thinkinjava.chapter15_generator.c8;

import java.util.Map;


public class ClasstTypeCapture<T> {
	Class<T> kind;
	
	T t;
	
	public ClasstTypeCapture(Class<T> kind,Map<T,IFactory<T>> map){
		this.kind =  kind;
		t =  map.get(kind).create();
	}
	
	public boolean f(Object arg){
		return kind.isInstance(arg);
	}
	
	public static void main(String[] args) {
//		ClassTypeCapture<Building> 
//		c = new  ClassTypeCapture(Building.class);
//		
//		System.out.println( c.f( new Building() ));
//		System.out.println( c.f( new House() ));
//		
//		ClassTypeCapture<House> 
//		c2 = new  ClassTypeCapture(House.class);
//		
//		System.out.println( c2.f( new Building() ));
//		System.out.println( c2.f( new House() ));
	}
}

class Building {}

class House extends Building{
	
}