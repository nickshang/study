package thinkinjava.chapter15_generator.c11;

public class Holder30 {

	public static void main(String[] args) {
		 
		byte b = 1;
		Holder<Byte> h1 = new Holder<>();
		h1.set(b);
		Byte b1 = h1.get();
		
		System.out.println(b1);
	}
}
