package shang.net;

import java.io.File;


public class FtpUtilCommonsTest {

	//数据FTP配置
	public static String FTP_EMS_IP = "10.41.32.10";   //标识值
	public static int FTP_EMS_PORT = 21;   //标识值
	public static String FTP_EMS_USERNAME = "ems";   //标识值
	public static String FTP_EMS_PW = "sme8003";   //标识值

	public static String FTP_EMS_STATION_PATH = "/ems_inf/para/station";   //标识值
	public static String FTP_EMS_YC_PATH = "/ems_inf/para/yc";   //标识值
	public static String FTP_EMS_YX_PATH = "/ems_inf/para/yx";   //标识值
	public static String FTP_EMS_REAL_PATH = "/ems_inf/data/real";   //标识值d


	//数据源文件存放路径
	public static String DF8003_FILEPATH = "D:\\df8003\\interface";
	//站点文件
	public static String DF8003_FILEPATH_STATION = DF8003_FILEPATH+File.separator+"STATION"; 
	//遥性数据文件
	public static String DF8003_FILEPATH_YC = DF8003_FILEPATH+File.separator+"YC"; 
	//遥信数据文件
	public static String DF8003_FILEPATH_YX = DF8003_FILEPATH+File.separator+"YX";
	//实时数据文件
	public static String DF8003_FILEPATH_REAL = DF8003_FILEPATH+File.separator+"REAL"; 
	
	@Test
	public void testS()
	{
		
		
		FtpUtilCommons ftpUtil = new FtpUtilCommons();
		ftpUtil.connectServer("10.231.235.132",  21, "oracle", "oracle");
		ftpUtil.downLoadFile( DF8003_FILEPATH_REAL, "/",  "RUNNING.txt" );
	}
	
}
