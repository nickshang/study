package io;

import java.io.*;

/**
 * �����������Զ���InputStreamװ����
 * ���������ڵ����д�д�ַ�ת��Сд
 * @author NICK
 *
 */
public class LowerCaseInputStream extends FilterInputStream {
	
	public LowerCaseInputStream(InputStream in){
		super(in);
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : Character.toLowerCase((char)c));
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int result = super.read(b, off, len);
		for (int i = off; i < off + result; i++ ){
			b[i] = (byte)Character.toLowerCase((char)b[i]);
		}
		return result;
	}
	
	/**
	 * ����LowerCaseInputStream
	 * @param args
	 */
	public static void main(String[] args) {
		int c;
		try{
			InputStream in = 
					new LowerCaseInputStream( new BufferedInputStream(
							new FileInputStream("C:\\Users\\Think\\Desktop\\�Ƽ�����.txt")
							));
			while( ( c = in.read() ) >= 0 ){
				System.out.println(Character.toChars(c));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
