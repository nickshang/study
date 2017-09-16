package com.shang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxUitl {
 
	/**
	 * 功能描述：利用正则表达式获取解析数据组
	 * @param conect 需要解析内容
	 * @param pattern 正则表达式
	 * @return 获取解析的内容
	 */
	public Matcher getMatcher( String conect , String regx ){
		Pattern p = Pattern.compile( regx , Pattern.CASE_INSENSITIVE);
		return  p.matcher( conect );
	}
}
