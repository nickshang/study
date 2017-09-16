package com.shang.svlt;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseSvlt {
	
//	protected static Logger logger = Logger.getRootLogger();

	public BaseSvlt() {
		sv = null;
		out = null;
		request = null;
		response = null;
		servlet = null;
	}

	public void init(Svlt_all sv, PrintWriter out, HttpServletRequest request,
			HttpServletResponse response) {
		this.sv = sv;
		this.out = out;
		this.request = request;
		this.response = response;
	}

	public abstract void todo();

	public void setServlet(HttpServlet servlet) {
		this.servlet = servlet;
	}

	public HttpServlet getServlet() {
		return servlet;
	}

	public Svlt_all sv;
	public PrintWriter out;
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpServlet servlet;
	
	
	/**
	 * 功能描述:requestDispatcher跳转
	 * @param url
	 */
	public void requestDispatcher(String url)
	{
		//跳转
		try {
			request.getRequestDispatcher( url).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 功能描述：将对象转换成JSON字符,输出结果
	 * @param object
	 */
	public void outPrint(Object object ){
		String print = "";
		//结果
		
//		try {
//			print = JsonUtil.getJSONString(object);
//		} catch (Exception e) {
//			logger.error(e, e);
//		}
		
		//输出
		response.setContentType("text/html; charset=GBK");
		out.print(print);
		out.flush();
		out.close();
	}
	
}
