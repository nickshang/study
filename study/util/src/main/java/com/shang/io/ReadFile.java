package com.shang.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	/**
	 * 根据文件路径,读取文件内容
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件内容
	 * @throws IOException 
	 */
	public String readFile(File file ,String type)  {

		// 读取文件内容
		StringBuffer bf = new StringBuffer();
		String str = "";
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader in = new BufferedReader(read);

			while ((str = in.readLine()) != null) {
				bf.append(str);
				
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return bf.toString();

	}
}
