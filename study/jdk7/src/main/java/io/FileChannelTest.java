package io;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 功能描述：学习FileChannel.map将文件按照一定的大小映射到内存区域，
 * 当程序访问这个内存区域时候讲直接操作这个文件数据，这样方式省去了数据到内核空间到用户空间的复制损耗。
 * @author NICK
 *
 */
public class FileChannelTest {
	
	public static void main(String[] args) {
		
		int BUFFER_SIZE = 1024;											// 定义缓冲字节数
		String filename = "D:\\dmdbms\\data\\FJ_D5000_STLF.dbf";		// 定义文件名
		long fileLength = new File(filename).length();					// 获取文件字节数
		System.out.println( "fileLenght:" +  fileLength );

		int bufferCont = 1 + (int) (fileLength / BUFFER_SIZE);			// 计算文件中除以1024字节数数值
		MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCont];	// 文件映射对象
		
		RandomAccessFile file = null; 												// 随机访问文件的读取和写入

		long remaining = fileLength;												// 定义文件字节长度
		for (int i = 0; i < bufferCont; i++) {
			try{
				if(file == null )file  = new RandomAccessFile(filename,"r");		// 初始随机访问文件对象为只读模式
				buffers[i] = file.getChannel().map(FileChannel.MapMode.READ_ONLY,	// 获取数据存储在文件映射对象
						i * BUFFER_SIZE , (int) Math.min(remaining, BUFFER_SIZE) );
			}catch(Exception e){
				e.printStackTrace();
			}
			remaining -= BUFFER_SIZE;
		}
		
	}
}
