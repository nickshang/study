package shang.net;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

public class T2 {

	
	public static Connection getType(  String rq ){
		
		String dbdriver = "oracle.jdbc.driver.OracleDriver";
		String database = "jdbc:oracle:thin:@10.34.20.93:1521:orcl";
		String dbusername = "stlf";
		String dbpassword = "stlf";
		
		try{
            Class.forName(dbdriver);
            return DriverManager.getConnection(database, dbusername, dbpassword);      
        }catch(Exception ex){
            System.err.println("���ݿ������ļ�����:" + ex.getMessage());
           
        }
		
		
		return null;
	}
	
	public static void main(String[] args) {
		
		Random rand = new Random(1);
		for(int i = 0; i< 10; i++){
			System.out.println(rand.nextInt());
		}
		
//		
//		String str=null;
//	    str=String.format("Hi, %s", "�ּ���"); // ��ʽ���ַ���
//	    System.out.println(str); // ����ַ�������str������
//	    System.out.printf("3>7�Ľ���ǣ�%b %n", 3>7);
//	    System.out.printf("100��һ���ǣ�%d %n", 100/2);
//	    System.out.printf("50Ԫ�����8.5�ۿ��ǣ�%f Ԫ%n", 50*0.85);
//	    System.out.printf("������ۿ���%d%% %n", 85);
	    
//	    
//	    String str=null;  
//	    //$ʹ��  
//	    str=String.format("��ʽ����$��ʹ�ã�%1$.1f,%2$.2f", 99.99,88.88);             
//	    System.out.println(str);                       
//	    //+ʹ��  
//	    System.out.printf("��ʾ�������ķ��ţ�%+d��%d%n", 99,-99);  
//	    //��Oʹ��  
//	    System.out.printf("��ţ�ı���ǣ�%03d%n", 7);  
//	    //�ո�ʹ��  
//	    System.out.printf("Tab����Ч���ǣ�% 8d%n", 7);  
//	    //.ʹ��  
//	    System.out.printf("���������Ч���ǣ�%,d%n", 9989997);  
//	    //�ո��С����������  
//	    System.out.printf("һ����ļ۸��ǣ�% 50.5fԪ%n", 49.8);  
	    
		
//		String url = "http://10.34.20.94:8080/reportServlet";
//		Vector<TreeMap> reqs = new Vector<TreeMap>();
//		Vector<TreeMap> responses = null;
//		
//	   
//	        
//		
//		for( int i=0; i<10000 ; i++){
//			
//			TreeMap<String, String> param = new TreeMap<String, String>();
//			   param.put("operation", "query_caliber");
//			   Date date = new Date();
//			   param.put("num", String.valueOf(  date.getTime() ) );
//			   reqs.add(param);
//			   System.out.println( "######date:" +  date.getTime() );
//			   
//			URL destURL;
//			try {
//				destURL = new URL(url);
//				
//				URLConnection conn = null;
//				if ((url.toLowerCase()).startsWith("https://")) {
//						conn = (HttpsURLConnection) destURL.openConnection();
//					
//				} else if ((url.toLowerCase()).startsWith("http://")) {
//					conn = (HttpURLConnection) destURL.openConnection();
//				} else {
//					conn = destURL.openConnection();
//				}
//
//				conn.setDoOutput(true);
//				conn.setDoInput(true);
//				conn.setUseCaches(false);
//
//				ObjectOutputStream out = new ObjectOutputStream(
//						conn.getOutputStream());
//				out.writeObject(reqs);
//				out.flush();
//				out.close();
//				ObjectInputStream in = new ObjectInputStream(
//						new BufferedInputStream(conn.getInputStream())); 
//				
//				try {
//					responses = (Vector) in.readObject();
//					System.out.println( responses );
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//				
//				in.close();
//				
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
		
		
		
		
//		List<Connection> conns = new ArrayList<Connection>();
//		T1 t = new T1();
//		int max = 90;
//		for( int i=0; i<max; i++ )
//		{
//			conns.add( t.getType( "" ) ); 
//			System.out.println( "������:" + i );
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
//				System.out.println( "�ر�����:" + z++ );
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		
		
		
		
	}
	
	

}
