package thinkinjava.chapter21_concurrency.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ��������������NIO�������ж�
 * @author NICK
 *
 */
public class NIOInterrupting {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		//�����̳߳�
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//�����������׽���
		ServerSocket serverSocket = new ServerSocket(9090);
		
		//�����ʾ������Э�� (IP) ��ַ
		InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",9090);
		
		//����������������׽��ֵĿ�ѡ��ͨ����
		SocketChannel sc1 =  SocketChannel.open(inetSocketAddress) ;
		SocketChannel sc2 =  SocketChannel.open(inetSocketAddress) ;
		
		//�ύ���Է��ز����߳�
		Future<?> f = exec.submit( new NIOBlocked( sc1 ) );
		exec.execute( new NIOBlocked( sc2 ));
		 
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(3);
		System.out.println("��ʼ�ر�NIO");
		f.cancel(true);
		
		System.out.println("�˳�NIOInterrupting");
		sc2.close();
		System.exit(0);
	}
}


//��IO����
class NIOBlocked implements Runnable {
	
	private final SocketChannel sc;
	
	public NIOBlocked(SocketChannel sc) { this.sc = sc; }
	
	public void run(){
		try{
			System.out.println("Watring for read() in " +  this.getClass().getName() );
			sc.read(ByteBuffer.allocate(1));
		}catch (ClosedByInterruptException e) {
			System.out.println("ClosedByInterruptException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
		System.out.println("Extring NIOBlock.run() " + this.getClass().getName() );
	}
}
