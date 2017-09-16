package shang.net;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.TreeMap;
import java.util.Vector;

public class T6 {

	
	public static Connection getType(  String rq ){
		
		String dbdriver = "oracle.jdbc.driver.OracleDriver";
		String database = "jdbc:oracle:thin:@10.34.20.93:1521:orcl";
		String dbusername = "stlf";
		String dbpassword = "stlf";
		
		try{
            Class.forName(dbdriver);
            return DriverManager.getConnection(database, dbusername, dbpassword);      
        }catch(Exception ex){
            System.err.println("数据库驱动文件错误:" + ex.getMessage());
           
        }
		
		
		return null;
	}
	
	public static void main(String[] args) {
		
		String url = "http://10.34.20.94:8080/reportServlet";
		Vector<TreeMap> reqs = new Vector<TreeMap>();
		Vector<TreeMap> responses = null;
		
	   
	        
		
		for( int i=0; i<10000 ; i++){
			
			TreeMap<String, String> param = new TreeMap<String, String>();
			   param.put("operation", "query_caliber");
			   Date date = new Date();
			   param.put("num", String.valueOf(  date.getTime() ) );
			   reqs.add(param);
			   System.out.println( "######date:" +  date.getTime() );
			   
			URL destURL;
			try {
				destURL = new URL(url);
				
				URLConnection conn = null;
				if ((url.toLowerCase()).startsWith("https://")) {
						conn = (HttpsURLConnection) destURL.openConnection();
					
				} else if ((url.toLowerCase()).startsWith("http://")) {
					conn = (HttpURLConnection) destURL.openConnection();
				} else {
					conn = destURL.openConnection();
				}

				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);

				ObjectOutputStream out = new ObjectOutputStream(
						conn.getOutputStream());
				out.writeObject(reqs);
				out.flush();
				out.close();
				ObjectInputStream in = new ObjectInputStream(
						new BufferedInputStream(conn.getInputStream())); 
				
				try {
					responses = (Vector) in.readObject();
					System.out.println( responses );
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				in.close();
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
//		List<Connection> conns = new ArrayList<Connection>();
//		T1 t = new T1();
//		int max = 90;
//		for( int i=0; i<max; i++ )
//		{
//			conns.add( t.getType( "" ) ); 
//			System.out.println( "打开连接:" + i );
//		}
//		
//		try {
//			Thread.sleep( 1000 * 60 *2 );
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		int z = 0;
//		for(Connection con:conns){
//			try {
//				con.close();
//				System.out.println( "关闭连接:" + z++ );
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		
		
		
		
	}
	
	

}
