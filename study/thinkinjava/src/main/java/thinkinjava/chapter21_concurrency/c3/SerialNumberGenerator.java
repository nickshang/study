package thinkinjava.chapter21_concurrency.c3;

/**
 * 功能描述：产生序列数字的类,每次产生的数字为唯一信息
 * @author NICK
 *
 */
public class SerialNumberGenerator {

	private static volatile int serialNumber = 0;
	
	public static synchronized int nextSerialNumber() {
		 return serialNumber++;
	}
	 
}
