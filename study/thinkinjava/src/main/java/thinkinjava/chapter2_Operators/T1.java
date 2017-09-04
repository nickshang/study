package thinkinjava.chapter2_Operators;

public class T1 {
	public static void main(String[] args) {
		int i = 0x21;
		System.out.println(Integer.toBinaryString(i));
		while(i!=0){
			i >>=1;
			System.out.println(Integer.toBinaryString(i));
		}
		
		
		System.out.println("----------------------------");
		
		i = Integer.MAX_VALUE;
		System.out.println(Integer.toBinaryString(i));
		
		i = 1<<1;
		System.out.println(Integer.toBinaryString(i));
		while(i!=0){
			i >>=1;
			System.out.println(Integer.toBinaryString(i));
		}
		
		System.out.println("----------------------------");
		char c = 'c';
		System.out.println(Integer.toBinaryString(Integer.valueOf(c)));
		
	}
}
