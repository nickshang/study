package shang.net;

import java.io.File;

public class FtpUtilCommonsTest2 {

	//数据FTP配置
	public static String FTP_EMS_IP = "192.111.4.3";   //标识值
	public static int FTP_EMS_PORT = 21;   //标识值
	public static String FTP_EMS_USERNAME = "oracle";   //标识值
	public static String FTP_EMS_PW = "oracle";   //标识值

	public static String FTP_EMS_STATION_PATH = "/on3000/data/MeteorologicalData";   //标识值 
 
 
	 
	public static void main(String[] args )
	{ 
		String FILEPATH = "E:\\tsingsoftinf\\MeteorologicalData\\" + ( new java.util.Date() ).getTime(); 
	 
		File file = new File( FILEPATH );
		if(  !file.isDirectory() ){ 
			file.mkdirs();
		}
//
//		FtpUtilCommons ftpUtil = new FtpUtilCommons();
//		ftpUtil.connectServer( FTP_EMS_IP , FTP_EMS_PORT , FTP_EMS_USERNAME, FTP_EMS_PW );
//		ftpUtil.downLoadFile( FILEPATH ,  FTP_EMS_STATION_PATH , "MeteorologicalData.xml" );
	}
	
}
