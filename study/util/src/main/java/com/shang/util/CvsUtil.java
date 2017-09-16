package com.shang.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：删除文件
 * @author NICK
 *
 */
public class CvsUtil {

	public static void main(String[] args) {
//		
		File file = new File( "C:\\Users\\Think\\Desktop\\CoLLECT_AH_D5000" );
		deleteCvs( file );
		
//		boolean bl  = true; 	//  32bit(虚拟机解释为int),数组为8bit
//		byte 	b   = 1;	  	//  8bit
//		char 	c 	= 1;	 	// 16bit
//		short 	s 	= 1;		// 16bit
//		int 	i 	= 1;		// 32bit
//		long 	l   = 1;		// 64bit
//		float 	f  	= 1f;		// 32bit
//		double 	d 	= 1f;		// 64bit
//		
//		ConcurrentHashMap  map1 = new ConcurrentHashMap();
//		
//		Map<String, String> map = new HashMap<String,String>();
//		
//		map.put(null, "1");
//		
//		System.out.println( map.get(null) );
	}
	
	/**
	 * 功能描述：删除文件夹的文件目录
	 * @param file
	 */
	public static void deleteCvs(File file ){
		
		//1、列出所有文件夹
		if( file.isDirectory() ) {
			
			File[] files = file.listFiles( );
			for(File f:files){
				
				//2、删除CVS问价下的文件和文件夹
				if( f.isDirectory() &&  f.getName().equals( "CVS" ) ){
			
					File[] fs = f.listFiles();
					for(File f_cvs:fs){
						f_cvs.delete();
						System.out.println( "###########删除文件:" + f.getAbsolutePath() + File.separator+ f_cvs.getName() );
					}
					f.delete();
					System.out.println( "###########删除文件夹:" + f.getAbsolutePath() );
				
				//3、继续查找删除
				}else if( f.isDirectory() ) {
					deleteCvs( f );
				}
			}
		}
		
	}
}
