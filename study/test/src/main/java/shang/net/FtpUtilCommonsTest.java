package shang.net;

import java.io.File;


public class FtpUtilCommonsTest {

	//����FTP����
	public static String FTP_EMS_IP = "10.41.32.10";   //��ʶֵ
	public static int FTP_EMS_PORT = 21;   //��ʶֵ
	public static String FTP_EMS_USERNAME = "ems";   //��ʶֵ
	public static String FTP_EMS_PW = "sme8003";   //��ʶֵ

	public static String FTP_EMS_STATION_PATH = "/ems_inf/para/station";   //��ʶֵ
	public static String FTP_EMS_YC_PATH = "/ems_inf/para/yc";   //��ʶֵ
	public static String FTP_EMS_YX_PATH = "/ems_inf/para/yx";   //��ʶֵ
	public static String FTP_EMS_REAL_PATH = "/ems_inf/data/real";   //��ʶֵd


	//����Դ�ļ����·��
	public static String DF8003_FILEPATH = "D:\\df8003\\interface";
	//վ���ļ�
	public static String DF8003_FILEPATH_STATION = DF8003_FILEPATH+File.separator+"STATION"; 
	//ң�������ļ�
	public static String DF8003_FILEPATH_YC = DF8003_FILEPATH+File.separator+"YC"; 
	//ң�������ļ�
	public static String DF8003_FILEPATH_YX = DF8003_FILEPATH+File.separator+"YX";
	//ʵʱ�����ļ�
	public static String DF8003_FILEPATH_REAL = DF8003_FILEPATH+File.separator+"REAL"; 
	
	@Test
	public void testS()
	{
		
		
		FtpUtilCommons ftpUtil = new FtpUtilCommons();
		ftpUtil.connectServer("10.231.235.132",  21, "oracle", "oracle");
		ftpUtil.downLoadFile( DF8003_FILEPATH_REAL, "/",  "RUNNING.txt" );
	}
	
}
