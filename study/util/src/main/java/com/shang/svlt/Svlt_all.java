package com.shang.svlt;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.*;


public class Svlt_all extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3117994919686858221L;
	public Svlt_all() {
		classpath = "com.dd.svlt.";
		urlpath = "/(.[^\\./]*?)\\.shtml$";
	}

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=GBK");
		java.io.PrintWriter out = response.getWriter();
		String page = request.getRequestURI();
		Pattern p = Pattern.compile(urlpath);
		Matcher m = p.matcher(page);
		boolean ifpage = m.find();
		if (ifpage)
			try {
				Class<?> c = Class.forName(classpath
						+ m.group(1).replaceAll("--", "."));
				BaseSvlt cs = (BaseSvlt) (BaseSvlt) c.newInstance();
				cs.init(this, out, request, response);
				cs.setServlet(this);
				cs.todo();
			} catch (ClassNotFoundException ex) {
				System.out.println("\u627E\u4E0D\u5230\u7C7B:" + classpath
						+ m.group(1).replaceAll("--", "."));
				getServletConfig().getServletContext().getRequestDispatcher(
						"/manage/error.jsp?code=nopage").forward(request,
						response);
				ex.printStackTrace();
			} catch (IllegalAccessException ex1) {
				getServletConfig().getServletContext().getRequestDispatcher(
						"/manage/error.jsp?code=nopage").forward(request,
						response);
				ex1.printStackTrace();
			} catch (InstantiationException ex1) {
				getServletConfig().getServletContext().getRequestDispatcher(
						"/manage/error.jsp?code=nopage").forward(request,
						response);
				ex1.printStackTrace();
			}
		else
			getServletConfig().getServletContext().getRequestDispatcher(
					"/manage/error.jsp?code=nopage").forward(request, response);
	}

	public void destroy() {
		super.destroy();
	}

	public void setSBasePath(String sBasePath) {
		classpath = sBasePath;
	}

	public void setSPtString(String sPtString) {
		urlpath = sPtString;
	}

	public String getSBasePath() {
		return classpath;
	}

	public String getSPtString() {
		return urlpath;
	}

	private static final String ContentType = "text/html; charset=GBK";
	private String classpath;
	private String urlpath;

}
