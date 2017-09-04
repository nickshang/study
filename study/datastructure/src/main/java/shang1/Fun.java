package shang1;

public class Fun {
	
	/**
	 * 功能描述：编译一个函数检查某个二进制（以字符串传入）是否等于以字符串表示的十六进制
	 * @param str1
	 * @param sts2
	 * @return
	 */
	public static boolean check(String str1,String str2){
		int a = Integer.parseInt(str1.substring(2), 2);  //
		int b = Integer.parseInt(str2.substring(2), 16);
		return a == b;
	}  
	
	public static void main(String[] args) {
		int a = 0b1011;			// 11
		int b = 0xb;			// 11
		System.out.println(b );
	}
	
}
