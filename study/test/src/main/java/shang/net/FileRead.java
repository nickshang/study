package shang.net;

import com.shang.io.ReadFile;

import java.io.File;


public class FileRead {

	public static void main(String[] args) {
		ReadFile read = new  ReadFile();
		String str= read.readFile( new File("C:\\Users\\Think\\Desktop\\2013\\hz_m_20131202_16.wfd"), "null");
		
		System.out.println( str.length()/100 );
	}
	
	
	
}
