package effectivejava.effectivejava.chapter35;

public class Sample2 {

	@ExceptionTest(ArithmeticException.class)
	public static void m1() {  //Test should pass
		int i = 0; 
		i = i/i;
	}
	
	@ExceptionTest( {ArithmeticException.class,ArrayIndexOutOfBoundsException.class})
	public static void m2() {  //Test should pass
		int[] a = new int[0]; 
		int i = a[1];
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void m3() { 
	}
}
