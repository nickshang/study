package thinkinjava.chapter5_init;

public class Counter {

	int i ;
	
	Counter(){
		System.out.println(i);
		i = 5;
		System.out.println(i);
	}
	
//	public static void main(String[] args) {
//		Counter c = new Counter();
//	}
	
	public static void main(String[] args) {
		System.out.println(Spoon.i);
	}
}
