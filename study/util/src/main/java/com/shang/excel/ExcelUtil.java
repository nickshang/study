package com.shang.excel;

import java.util.Calendar;

import com.shang.util.DateUtil;
 

public class ExcelUtil {

	
	/**
	 * 功能描述：
	 * @param days
	 * @return
	 */
	public String getDateStringByExcel( float days ){
		//19700101 基准天数 25569
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis( 0   );
		ca.add( Calendar.DATE, (int)(days -25569)  ); 
		return  DateUtil.DateToString( ca.getTime(),"yyyy-MM-dd" ) ;
	}
	
}
