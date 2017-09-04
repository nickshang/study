package thinkinjava.chapter21_concurrency.c4;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ���������� ͨ���رշ��������ĵײ���Դ���Ӷ��ж��̡߳�
 * 1.�ж�Socket���Ӷ��ж��̣߳�
 * 2.����IO�������޷��жϣ�
 * @author NICK
 *
 */
public class CloseResource {
	
	public static void main(String[] args) throws Exception{
		ExecutorService exec  = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(9090);
		InputStream socketInput = new Socket("localhost",9090).getInputStream();
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(System.in));
		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("Shutdown down all threads");
		
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + socketInput.getClass().getName());
		socketInput.close();
		System.out.println("Closing " + System.in.getClass().getName() );
		System.in.close();
	}
}
