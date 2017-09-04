package thinkinjava.chapter15_generator.c15;

import java.util.ArrayList;
import java.util.List;

public class T1 {
	public static void main(String[] args) {

		byte[] i = new byte[ 1<<10<<10<<9];	//2*8  2*16 * 30
		System.out.println( Integer.MAX_VALUE>>1 );
		System.out.println( Integer.MAX_VALUE>>2>>20 );
		
		System.out.println("Byte.MAX_VALUE:" + Byte.MAX_VALUE);
		System.out.println("Byte.MIN_VALUE:" + Byte.MIN_VALUE);
		System.out.println("Byte.MAX_VALUE:" + Long.toBinaryString(~-5+1));
		System.out.println("Byte.MIN_VALUE:"  + Long.toBinaryString(5));
		
	}
}
