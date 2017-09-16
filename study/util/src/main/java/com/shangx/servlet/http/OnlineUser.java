package com.shangx.servlet.http;

import java.util.List;

import javax.servlet.ServletContext;

public class OnlineUser {


	/**
	 * 功能描述：判断用户是否存在系统用
	 * @param application  ServletContext
	 * @param user 用户名
	 * @return true:存在 false:不存在
	 */
	public boolean existUser(ServletContext application , String user) {
		
		boolean result = false;
		
		List list = (List)application.getAttribute("onlineUserList");
		
		if(list != null )
		{
			int index = -1;
			for(int i=0; i<list.size(); i++)
			{
				if( list.get(i) !=  null && list.get(i).toString().equals(user) )
				{
					index = i;
				}
			}
			if(index >=0 ) result =true;
		}
		
		System.out.println("#########检查用户:"+ user+ ",是否登录:"+ result );
		
		return result;
		
		
	}
	
	
}
