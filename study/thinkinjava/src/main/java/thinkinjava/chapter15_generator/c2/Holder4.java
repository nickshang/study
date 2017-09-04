package thinkinjava.chapter15_generator.c2;

public class Holder4<T> {
	
	private T t1;
	
	private T t2;
	
	private T t3;
	
	
	public Holder4(T t1,T t2, T t3){
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
	
	public T getT1(){
		return t1;
	}
	
	public T getT2(){
		return t2;
	}
	
	public T getT3(){
		return t3;
	}
	public static void main(String[] args) {
		Holder4<Integer> h1 = new Holder4<Integer>(new Integer(1),new Integer(2),new Integer(2));
		System.out.println(h1.getT1());
		System.out.println(h1.getT2());
		System.out.println(h1.getT3());
		
		Holder4<String> h2 = new Holder4<String>( "string1", "string2", "string3");
		System.out.println(h2.getT1());
		System.out.println(h2.getT2());
		System.out.println(h2.getT3());
		
		Holder4<Double> h3 = new Holder4<Double>(new Double(1.3),new Double(2.3),new Double(3.3));
		System.out.println(h3.getT1());
		System.out.println(h3.getT2());
		System.out.println(h3.getT3());
	}
	
}
