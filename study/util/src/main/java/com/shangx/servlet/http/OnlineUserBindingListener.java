package com.shangx.servlet.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class OnlineUserBindingListener implements HttpSessionBindingListener {
	
	public OnlineUserBindingListener(String username)
	{
		setUsername(username);
	}
	
	//用户名
	private String username = "null"; 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	//绑定数据
	public void valueBound(HttpSessionBindingEvent e) {
	
		javax.servlet.http.HttpSession session = e.getSession();
		ServletContext application = session.getServletContext();
		
		List list = (List)application.getAttribute("onlineUserList");
		if(null == list )
		{
			list = new ArrayList();
			application.setAttribute("onlineUserList", list);
		}
		list.add(this.getUsername());
		
		System.out.println("#########绑定用户:"+ com.shang.util.DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss") +",用户名:"+ this.getUsername() );
		System.out.println("#########数量用户:"+ list.size()  );
		
	}
	
	//取消绑定
	public void valueUnbound(HttpSessionBindingEvent e) {
		
		javax.servlet.http.HttpSession session = e.getSession();
		ServletContext application = session.getServletContext();
		
		List  list = (List)application.getAttribute("onlineUserList");
		 
		int index = -1;
		for(int i=0; i<list.size(); i++)
		{
			if(  list.get(i)!= null && list.get(i).toString().equals(this.getUsername()) )
			{
				index = i;
			}
		}
		if(index >=0 )list.remove(index);
		
		System.out.println("#########取消用户:"+ com.shang.util.DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+ ",用户名:" + this.getUsername() );
		System.out.println("#########数量用户:"+ list.size()  );
	}
	
	

}
