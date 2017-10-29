package com.shang.excelExport;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：导出文件配置Bean
 * @author NICK
 * @time 2015-10-25
 */
public class ExcelExportConfig {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 导出文件方方案名称
	 */
	private String name;
	
	/**
	 * 导出文件名称
	 */
	private String exportName;
	
	/**
	 * 文件模板名称地址
	 */
	private String templateFileName;
	
	
	/**
	 * 数据源中心配置列表
	 */
	private String classNames;
	

	/**
	 * 数据源
	 */
	private Map<String,Object> ds =  new HashMap<String, Object>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public Map<String, Object> getDs() {
		return ds;
	}

	
	/**
	 * 功能描述：根据传递的参数，实例化配置的数据源类，设置数据源并获取数据源
	 * @param para
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> setDs(Map<String,String> para ) {
		
		String[] classArray = classNames.split(",");
		for ( String c : classArray ) {
			try {
				Class<IExcelExprotDataSource> ids  = (Class<IExcelExprotDataSource>) Class.forName( c.trim() );
				try {
					ds.putAll( ids.newInstance().getDs( para ) );
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return ds;
	}
	
}

