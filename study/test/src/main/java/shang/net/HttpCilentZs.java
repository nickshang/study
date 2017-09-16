package shang.net;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author Think
 *
 */
public class HttpCilentZs {

	public static final String LOGON_SITE = "www.zshyqx.com"; // 舟山气象局网站
	public static final int LOGON_PORT = 80; //  端口 

	public static final String LOGIN_USERNAME = "zsdw"; // 登录用户名
	public static final String LOGIN_PASSWORD = "zsdw"; // 登录密码 
	
	public static final String temperature = "temperature"; // 温度
	public static final String rainfall  = "rainfall"; // 降雨量 
	
	public static final String humidity = "humidity"; // 湿度
	public static final String pressure = "pressure"; // 气压
	
	public static final String windspeed = "windspeed"; // 风速
	public static final String winddirection = "winddirection"; // 风向

	public Map<String,String> get(String fromYear, String fromMonth, String fromDay,  String fromhour , String standid ) {
		Map<String,String> result = new HashMap<String, String>();
		
		try {

			org.apache.commons.httpclient.URI uri = new org.apache.commons.httpclient.URI("http://www.zshyqx.com");
			
			HttpClient client = new HttpClient(); 
			client.getHostConfiguration().setHost( uri );

			//1.模拟登录页面
			PostMethod post = new PostMethod("/logon_new.asp");
			NameValuePair name = new NameValuePair("username",  LOGIN_USERNAME );
			NameValuePair pass = new NameValuePair("id",  LOGIN_PASSWORD ); 
			NameValuePair x = new NameValuePair("submit.x",  "23" );
			NameValuePair y = new NameValuePair("submit.y",  "10" ); 
			 

			post.setRequestBody(new NameValuePair[] { name, pass, x ,   y });
			int status = client.executeMethod(post);
			System.out.println( "############获取页面#############"  );
			System.out.println(post.getResponseBodyAsString());
			post.releaseConnection();

			//2.查看 cookie 信息
//			CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
//			Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/",
//					false, client.getState().getCookies());
//			if (cookies.length == 0) {
//				System.out.println("None");
//			} else {
//				for (int i = 0; i < cookies.length; i++) {
//					System.out.println(cookies[i].toString());
//				}
//			} 
			
			//3.获取页面HTML
			StringBuffer str = new StringBuffer();
			str.append("http://" + LOGON_SITE + ":" + LOGON_PORT + "/zsdw/qxdb.asp?station=" + standid );
			post = new PostMethod(  str.toString()  );

			post.setRequestBody(new NameValuePair[] {  
					new NameValuePair("c_year",  fromYear ) , 
					new NameValuePair("c_mon",  fromMonth ), 
					new NameValuePair("c_day",  fromDay ) ,  
					new NameValuePair("c_hour", fromhour ),
					new NameValuePair("Submit",  "%B2%E9%D1%AF" ) 
					
					});
			client.getParams().setContentCharset("GBK");
			status = client.executeMethod(post);
			String html = post.getResponseBodyAsString() ;
			System.out.println( "############获取页面#############"  );
			System.out.println(post.getResponseBodyAsString());
			post.releaseConnection();
			
//			//3.获取页面HTML
//			StringBuffer str = new StringBuffer();
//			str.append("http://" + LOGON_SITE + ":" + LOGON_PORT + "/zsdw/qxdb.asp?station=" + standid );
//			str.append("&c_year=" + fromYear);
//			str.append("&c_mon=" + fromMonth);
//			str.append("&c_day=" + fromDay); 
//			str.append("&c_hour=" + fromhour); 
//			str.append("&Submit=%B2%E9%D1%AF"); 
//		
//			
//			GetMethod get = new GetMethod(str.toString());
//			client.executeMethod(get);
//			get.getParams().setContentCharset("GBK");
//			String html = get.getResponseBodyAsString() ;
//			  
//			System.out.println( new String(get.getResponseBodyAsString().getBytes() , "GBK").toString() );
//			get.releaseConnection();
			
			//5.解析 
			String regx = "<tr align=\"center\" valign=\"middle\">\\s*"
                  +"<td height=\"30\" bgcolor=\"#99CCFF\">气温</td>\\s*"
                  +"<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>\\s*" 
                  +"<td height=\"30\" bgcolor=\"#99CCFF\">雨量</td>\\s*"
                  +"<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>";;
			Matcher matcher = regx(regx, html);
			if(matcher.find()){
				result.put( temperature ,   matcher.group(1) );
				result.put( rainfall ,   matcher.group(2) );
			}
			
			
			//5.解析 
			 regx = " <tr align=\"center\" valign=\"middle\">\\s*" +
                  "<td height=\"30\" bgcolor=\"#99CCFF\">瞬时风向</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#99CCFF\">气压</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#99CCFF\">日最高气温</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>\\s*"+
                "</tr>\\s*"+
                "<tr align=\"center\" valign=\"middle\">\\s*"+
                  "<td height=\"30\" bgcolor=\"#99CCFF\">瞬时风速</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>"; 
			 matcher = regx(regx, html);
			if(matcher.find()){
				result.put( winddirection ,   matcher.group(1) );
				result.put( pressure ,   matcher.group(2) );
				result.put( windspeed ,   matcher.group(4) ); 
			}
			 
			//5.解析 
			 regx = " <td height=\"30\" bgcolor=\"#99CCFF\">相对湿度</td>\\s*"+
                  "<td height=\"30\" bgcolor=\"#CCCC66\">\\s*(.*)\\s*</td>"; 
			 matcher = regx(regx, html);
			if(matcher.find()){
				result.put( humidity ,   matcher.group(1) ); 
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
	 * 筹建适配器
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
		HttpCilentZs cilent = new HttpCilentZs();
		Map<String,String> map = cilent.get( "2014", "12", "01","14","58477" ); 
		 
		System.out.println( "温度:" +  map.get("temperature") );
		System.out.println( "降雨量:" + map.get("rainfall")  );
		System.out.println( "湿度:" + map.get("humidity") );
		System.out.println( "气压:" +  map.get("pressure")  );
		System.out.println( "风速:" +  map.get("windspeed")  );
		System.out.println( "风向:" + map.get("winddirection")  );
		
	}
	 
}
