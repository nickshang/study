package thinkinjava.chapter21_concurrency.c3;

/**
 * ���������������������ֵ���,ÿ�β���������ΪΨһ��Ϣ
 * @author NICK
 *
 */
public class SerialNumberGenerator {

	private static volatile int serialNumber = 0;
	
	public static synchronized int nextSerialNumber() {
		 return serialNumber++;
	}
	 
}
