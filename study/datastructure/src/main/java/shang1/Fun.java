package shang1;

public class Fun {
	
	/**
	 * ��������������һ���������ĳ�������ƣ����ַ������룩�Ƿ�������ַ�����ʾ��ʮ������
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
