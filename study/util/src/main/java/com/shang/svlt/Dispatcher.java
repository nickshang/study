package com.shang.svlt;

public class Dispatcher {
	/**
	 * 相对路径跳转
	 * @param out
	 * @param strInfo
	 * @param strPage
	 * @param strFrame
	 */
	public static void gotoURL(java.io.PrintWriter out, String strInfo,
			String strPage, String strFrame) {
		String tempFrame;
		if (strFrame == "") {
			tempFrame = "self";
		} else {
			tempFrame = strFrame;
		}

		try {
			out.write("\n");
			if (strInfo != "") {
				out.println("<html>");
				out.println("<head><title>确认</title></head>");
				out.println("<body bgcolor=\"#ffffff\" srcoll=\"no\">");
				out
						.println("<table style=\"width:100%;height:100%;border:0;text-align:center\"><tr><td>");
				out
						.println("<table style=\"width:320;height:160;font-size:10.4pt;font-weight:bold;align:center\">");
				out
						.println("<tr><td style=\"BACKGROUND-COLOR: #0055E6;font-size:9pt;height:22px;color:white\">&nbsp;系统提示</td></tr>");
				out
						.println("<tr><td style=\"BACKGROUND-COLOR: #e6e6e6;text-align:center\">");
				out
						.write(strInfo
								+ "&nbsp;&nbsp;2秒钟后自动<a href=\"javascript:go1();\"><span style=\"\">跳转</span></a>！");
				out.println("</td></tr></table><br><br>");
				out.println("</td></tr></table>");
				out.println("</body></html>");

			}

			out.write("<script language=\"javascript\">\n");

			out.write("function go1(){\n");
			if (strPage != "") {
				if (strPage == "-1") {
					out.write(tempFrame + ".window.history.go(-1);\n");
				} else {
					out.write(tempFrame + ".window.location.replace(\""
							+ strPage + "\");\n");
				}
			}
			out.write("}\n");
			out.println("function window.onload(){");
			out.println("window.setTimeout(\"go1()\", 2000);");
			out.println("}");

			out.write("</script>");

		} catch (Exception e) {
		}
	}
	
	
	/**
	 * 项目绝对路径跳转
	 * @param out
	 * @param strInfo
	 * @param strPage
	 * @param strFrame
	 * @param request
	 */
	public static void gotoURL(java.io.PrintWriter out, String strInfo,
			String strPage, String strFrame,javax.servlet.http.HttpServletRequest request) {
		
		
		//跳转窗口
		String tempFrame;
		if (strFrame == "") {
			tempFrame = "self";
		} else {
			tempFrame = strFrame;
		}
		
		//路径
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		strPage = basePath + strPage;
		
		try {
			out.write("\n");
			if (strInfo != "") {
				out.println("<html>");
				out.println("<head><title>确认</title></head>");
				out.println("<body bgcolor=\"#ffffff\" srcoll=\"no\">");
				out
						.println("<table style=\"width:100%;height:100%;border:0;text-align:center\"><tr><td>");
				out
						.println("<table style=\"width:320;height:160;font-size:10.4pt;font-weight:bold;align:center\">");
				out
						.println("<tr><td style=\"BACKGROUND-COLOR: #0055E6;font-size:9pt;height:22px;color:white\">&nbsp;系统提示</td></tr>");
				out
						.println("<tr><td style=\"BACKGROUND-COLOR: #e6e6e6;text-align:center\">");
				out
						.write(strInfo
								+ "&nbsp;&nbsp;2秒钟后自动<a href=\"javascript:go1();\"><span style=\"\">跳转</span></a>！");
				out.println("</td></tr></table><br><br>");
				out.println("</td></tr></table>");
				out.println("</body></html>");

			}

			out.write("<script language=\"javascript\">\n");

			out.write("function go1(){\n");
			if (strPage != "") {
				if (strPage == "-1") {
					out.write(tempFrame + ".window.history.go(-1);\n");
				} else {
					out.write(tempFrame + ".window.location.replace(\""
							+ strPage + "\");\n");
				}
			}
			out.write("}\n");
			out.println("function window.onload(){");
			out.println("window.setTimeout(\"go1()\", 2000);");
			out.println("}");

			out.write("</script>");

		} catch (Exception e) {
		}
	}
}
