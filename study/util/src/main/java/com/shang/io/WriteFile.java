package com.shang.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 功能描述:
 * @author Nick
 */
public class WriteFile {

	/**
	 * 功能描述:创建文件
	 * @param str 创建文件的字符串
	 * @param filePath 文件
	 * @param type ture:成功 false:失败
	 * @return  ture:成功 false:失败
	 */
	public boolean WriteFlie(String str,String filePath,boolean type)
	{
		boolean check = false;
		
		if( null== str ) { str = ""; } 
		
		File file = new File(filePath);
		
		boolean isFile = file.exists();
		
		//文件存在 ，不覆盖
		if(isFile && !type)
		{
			return check = true;
		}
		
		//文件覆盖
		try {
			FileWriter write = new FileWriter(filePath);
			write.write(str);
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return check;
	}

}
