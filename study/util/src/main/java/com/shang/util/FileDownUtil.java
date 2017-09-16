package com.shang.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;

public class FileDownUtil {

	
	/**
	 * ???????
	 * @param pc
	 * @param out
	 * @param response
	 * @param file
	 * @param fileName
	 */
	public static void download(HttpServletResponse response,File file,String fileName){
		BufferedInputStream bis = null;         
		BufferedOutputStream bos = null; 
		OutputStream ost = null;
		try {
			response.reset();
			response.setContentType("application/vnd.ms-Excel; charset=gb2312");
			response.setHeader("Content-disposition","attachment; filename="+ new String(fileName.getBytes("gbk"),"iso8859-1"));            
			bis = new BufferedInputStream(
					new FileInputStream(
							file));  
			ost = response.getOutputStream();
			bos = new BufferedOutputStream(ost);            
			byte[] buff = new byte[2048];            
			int bytesRead;             
			while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) { 
				bos.write(buff,0,bytesRead);             
			}  
			bos.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(ost != null){
				try {
					ost.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bis != null) {    
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}       
			}                
			if(bos != null) {        
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}           
			}
		}
	}
}
