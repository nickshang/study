package thinkinjava.chapter15_generator.c2;

public class Holder3<T> {
	
	private T a;
	
	public Holder3(T a){
		this.a = a;
	}
	
	public T get(){
		return a;
	}
	
	public static void main(String[] args) {
		Holder3<Integer> h1 = new Holder3<Integer>(new Integer(3));
		System.out.println(h1.get());
		
		Holder3<String> h2 = new Holder3<String>( "string");
		System.out.println(h2.get());
		
		Holder3<Double> h3 = new Holder3<Double>(new Double(3.3));
		System.out.println(h3.get());
	}
	
}
