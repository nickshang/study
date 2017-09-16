package com.shang.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：完成中文编码的过滤器
 * 创建时间：2009-7-8
 * @author Nick
 *
 */
public class CharsetEncodingFilter implements Filter {
	
	//默认编码
	private String encoding = "gb2312";  
	
	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
			HttpServletRequest request = (HttpServletRequest)servletRequest;
			HttpServletResponse  response = (HttpServletResponse)servletResponse;
			request.setCharacterEncoding(encoding);
			filterChain.doFilter(request, response);	
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");	
	}

}