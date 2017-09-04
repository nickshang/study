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
 * 功能描述：测试NIO阻塞，中断
 * @author NICK
 *
 */
public class NIOInterrupting {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		//创建线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//创建服务器套接字
		ServerSocket serverSocket = new ServerSocket(9090);
		
		//此类表示互联网协议 (IP) 地址
		InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",9090);
		
		//针对面向流的连接套接字的可选择通道。
		SocketChannel sc1 =  SocketChannel.open(inetSocketAddress) ;
		SocketChannel sc2 =  SocketChannel.open(inetSocketAddress) ;
		
		//提交可以返回参数线程
		Future<?> f = exec.submit( new NIOBlocked( sc1 ) );
		exec.execute( new NIOBlocked( sc2 ));
		 
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
		TimeUnit.SECONDS.sleep(3);
		System.out.println("开始关闭NIO");
		f.cancel(true);
		
		System.out.println("退出NIOInterrupting");
		sc2.close();
		System.exit(0);
	}
}


//新IO阻塞
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
