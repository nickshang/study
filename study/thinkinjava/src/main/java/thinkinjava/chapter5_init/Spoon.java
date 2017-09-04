package thinkinjava.chapter5_init;

public class Spoon {
	public final static int i[] = new int[5];
	
	static {
		i[0] = 100;
	}
	
	public static void main(String[] args) {
		System.out.println(Spoon.i[0]);
	}
	
}
