package com.shang.excelExport;

import java.util.Map;


/**
 * 功能描述：导出Excel数据注册中心
 * @author NICK
 *
 */
public interface IExcelExprotDataSource {
	
	/**
	 * 功能描述：获取数据中心
	 * @param para
	 * @return
	 */
	public Map<String,Object> getDs(Map<String,String> parameter);

}
