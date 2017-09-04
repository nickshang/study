package io;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ����������ѧϰFileChannel.map���ļ�����һ���Ĵ�Сӳ�䵽�ڴ�����
 * �������������ڴ�����ʱ��ֱ�Ӳ�������ļ����ݣ�������ʽʡȥ�����ݵ��ں˿ռ䵽�û��ռ�ĸ�����ġ�
 * @author NICK
 *
 */
public class FileChannelTest {
	
	public static void main(String[] args) {
		
		int BUFFER_SIZE = 1024;											// ���建���ֽ���
		String filename = "D:\\dmdbms\\data\\FJ_D5000_STLF.dbf";		// �����ļ���
		long fileLength = new File(filename).length();					// ��ȡ�ļ��ֽ���
		System.out.println( "fileLenght:" +  fileLength );

		int bufferCont = 1 + (int) (fileLength / BUFFER_SIZE);			// �����ļ��г���1024�ֽ�����ֵ
		MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCont];	// �ļ�ӳ�����
		
		RandomAccessFile file = null; 												// ��������ļ��Ķ�ȡ��д��

		long remaining = fileLength;												// �����ļ��ֽڳ���
		for (int i = 0; i < bufferCont; i++) {
			try{
				if(file == null )file  = new RandomAccessFile(filename,"r");		// ��ʼ��������ļ�����Ϊֻ��ģʽ
				buffers[i] = file.getChannel().map(FileChannel.MapMode.READ_ONLY,	// ��ȡ���ݴ洢���ļ�ӳ�����
						i * BUFFER_SIZE , (int) Math.min(remaining, BUFFER_SIZE) );
			}catch(Exception e){
				e.printStackTrace();
			}
			remaining -= BUFFER_SIZE;
		}
		
	}
}
