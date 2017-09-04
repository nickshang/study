package shang.net;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpCilent {

	static final String LOGON_SITE = "192.168.0.228"; // ĸ�߸���Ԥ��ϵͳ IP
	static final int LOGON_PORT = 8088; // ĸ�߸���Ԥ��ϵͳ �˿�
	static final String PROJECT_NAME = "/buslf"; // ������Ŀ����

	static final String LOGIN_USERNAME = "admin"; // ��¼�û���
	static final String LOGIN_PASSWORD = "admintsingsoft"; // ��¼����
	static final String LOGIN_COMMAND = "UserLogin"; // ��¼����
	
	static final String DS_NAME = "��ɽ"; // ��¼����
	
	static final String ZQL = "ZQL"; // ׼ȷ��
	static final String HGL = "HGL"; // �ϸ���
	

	public Map<String,String> get(String fromYear, String fromMonth, String fromDay) {
		
		Map<String,String> result = new HashMap<String, String>();
		
		try {

			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);

			//1.ģ���¼ҳ��
			PostMethod post = new PostMethod("/buslf/authorize");
			NameValuePair name = new NameValuePair("userName", "admin");
			NameValuePair pass = new NameValuePair("password", "admintsingsoft");
			NameValuePair command = new NameValuePair("command", "UserLogin");

			post.setRequestBody(new NameValuePair[] { name, pass, command });
			int status = client.executeMethod(post);
			System.out.println(post.getResponseBodyAsString());
			post.releaseConnection();

			//2.�鿴 cookie ��Ϣ
			CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
			Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/",
					false, client.getState().getCookies());
			if (cookies.length == 0) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.length; i++) {
					System.out.println(cookies[i].toString());
				}
			}

			//3.��ȡ׼ȷ��
			String toYear = fromYear;
			String toMonth = fromMonth;
			String toDay = fromDay;

			String fromDate = fromYear + fromMonth + fromDay;
			String toDate = toYear + toMonth + toDay;

			StringBuffer str = new StringBuffer();
			str.append("http://" + LOGON_SITE + ":" + LOGON_PORT + PROJECT_NAME
					+ "/dainty?command=CheckDayOrder");
			str.append("&fromYear=" + fromYear);
			str.append("&fromMonth=" + fromMonth);
			str.append("&fromDay=" + fromDay);

			str.append("&toYear=" + toYear);
			str.append("&toMonth=" + toMonth);
			str.append("&toDay=" + toDay);

			str.append("&fromDate=" + fromDate);
			str.append("&toDate=" + toDate);

			//4.��ȡҳ��HTML
			GetMethod get = new GetMethod(str.toString());
			client.executeMethod(get);
			String html = get.getResponseBodyAsString() ;
			
			System.out.println( get.getResponseBodyAsString() );
			get.releaseConnection();
			
			//5.����׼ȷ�ʡ��ϸ���
			String regx = "<td align='left'>&nbsp;"+DS_NAME+"</td>\\s*" + 
			    "<td>(.*)&nbsp;</td>\\s*"+
			    "<td>(.*)&nbsp;</td>";
			Matcher matcher = regx(regx, html);
			if(matcher.find()){
				result.put( ZQL ,   matcher.group(1) );
				result.put( HGL ,   matcher.group(2) );
			}
			
			return result;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * �ｨ������
	 * @param regex
	 * @param sDetail
	 * @return
	 */
	public Matcher regx(String regex, String sDetail) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sDetail);
		return m;
	}
	
	
	public static void main(String[] args) {
		HttpCilent cilent = new HttpCilent();
		Map<String,String> map = cilent.get("2013", "08", "01");
		System.out.println( "########׼ȷ��:"  + map.get( ZQL ) ); 
		System.out.println( "########�ϸ���:"  + map.get( HGL ) ); 
	}
	
	

//	public static void main(String[] args) throws Exception {
//		HttpClient client = new HttpClient();
//		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
//
//		// ģ���¼ҳ��
//		PostMethod post = new PostMethod("/buslf/authorize");
//		NameValuePair name = new NameValuePair("userName", "admin");
//		NameValuePair pass = new NameValuePair("password", "admintsingsoft");
//		NameValuePair command = new NameValuePair("command", "UserLogin");
//
//		post.setRequestBody(new NameValuePair[] { name, pass, command });
//		int status = client.executeMethod(post);
//		System.out.println(post.getResponseBodyAsString());
//		post.releaseConnection();
//
//		// �鿴 cookie ��Ϣ
//		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
//		Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/", false,
//				client.getState().getCookies());
//		if (cookies.length == 0) {
//			System.out.println("None");
//		} else {
//			for (int i = 0; i < cookies.length; i++) {
//				System.out.println(cookies[i].toString());
//			}
//		}
//
//		// 2.��ȡ����ҳ������
//		String fromYear = "2013";
//		String fromMonth = "11";
//		String fromDay = "21";
//
//		String toYear = "2013";
//		String toMonth = "11";
//		String toDay = "21";
//
//		String fromDate = fromYear + fromMonth + fromDay;
//		String toDate = toYear + toMonth + toDay;
//
//		StringBuffer str = new StringBuffer();
//		str
//				.append("http://192.168.0.228:8088/buslf/dainty?command=CheckDayOrder");
//		str.append("&fromYear=" + fromYear);
//		str.append("&fromMonth=" + fromMonth);
//		str.append("&fromDay=" + fromDay);
//
//		str.append("&toYear=" + toYear);
//		str.append("&toMonth=" + toMonth);
//		str.append("&toDay=" + toDay);
//
//		str.append("&fromDate=" + fromDate);
//		str.append("&toDate=" + toDate);
//
//		// String s =
//		// html.getHtmlByPost("http://192.168.0.228:8088/buslf/dainty?command=CheckDayOrder",pairs);
//
//		// ���������ҳ�� main2.jsp
//		GetMethod get = new GetMethod(str.toString());
//		client.executeMethod(get);
//		System.out.println(get.getResponseBodyAsString());
//		get.releaseConnection();
//	}

	// public static void main(String[] args) {
	//		
	// HttpCilentHtml html = new HttpCilentHtml();
	// NameValuePair username = new NameValuePair("userName", "admin");
	// NameValuePair password = new NameValuePair("password", "admintsingsoft");
	//
	// try {
	// //1.��¼ϵͳ
	// html.HttpCilentLogin("http://192.168.0.228:8088/buslf/index.jsp",
	// "gb2312", username, password);
	//			
	// //2.��ȡ����ҳ������
	// String fromYear = "2013";
	// String fromMonth = "11";
	// String fromDay = "21";
	//			
	// String toYear = "2013";
	// String toMonth = "11";
	// String toDay = "21";
	//			
	// String fromDate = fromYear+fromMonth+fromDay;
	// String toDate = toYear+toMonth+toDay;
	//			
	// NameValuePair fromYear_ = new NameValuePair("fromYear", fromYear );
	// NameValuePair fromMonth_ = new NameValuePair("fromMonth", fromMonth );
	// NameValuePair fromDay_ = new NameValuePair("fromDay", fromDay );
	//		    
	// NameValuePair toYear_ = new NameValuePair("toYear", toYear );
	// NameValuePair toMonth_ = new NameValuePair("toMonth", toMonth );
	// NameValuePair toDay_ = new NameValuePair("toDay", toDay );
	//		    
	// NameValuePair fromDate_ = new NameValuePair("fromDate", fromDate );
	// NameValuePair toDate_ = new NameValuePair("toDate", toDate );
	//		    
	// NameValuePair[] pairs = new NameValuePair[]{fromYear_,fromMonth_,
	// fromDay_ , toYear_ , toMonth_ , toDay_,fromDate_ , toDate_ };
	//		    
	//		     
	// StringBuffer str = new StringBuffer();
	// str.append(
	// "http://192.168.0.228:8088/buslf/dainty?command=CheckDayOrder" );
	// str.append( "&fromYear=" +fromYear);
	// str.append( "&fromMonth=" +fromMonth);
	// str.append( "&fromDay=" +fromDay);
	//		    
	// str.append( "&toYear=" +toYear);
	// str.append( "&toMonth=" +toMonth);
	// str.append( "&toDay=" +toDay);
	//		    
	// str.append( "&fromDate=" +fromDate);
	// str.append( "&toDate=" +toDate);
	//		    
	// String s = html.getHtmlByGet( str.toString() );
	// System.out.println(s);
	//		    
	// // String s =
	// html.getHtmlByPost("http://192.168.0.228:8088/buslf/dainty?command=CheckDayOrder",pairs);
	// // System.out.println(s);
	//			
	// // String s1 =
	// html.getHtmlByGet("http://10.40.30.144:81/cps/cps1_mx.asp");
	// // System.out.println(s1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
