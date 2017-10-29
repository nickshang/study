package com.shang.excelExport;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		
		// 1、获取数据
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("", "");
	
		// 2、设置数据源
		Map<String, Object> dataSource  = new HashMap<String, Object>();
		dataSource.put("yyyy-MM-dd", "20160301");
		
		dataSource.put("ss", 100);

			ExcelExportUtil util = new ExcelExportUtil();
		util.Export("F:\\webservice\\HEBEI\\apache-tomcat-6.0.48\\webapps\\ROOT\\template\\PV.xlsx", dataSource  );
		
		
//		String fileName = "F:\\webservice\\HEBEI\\apache-tomcat-6.0.48\\webapps\\ROOT\\template\\ExportExcel\\98015520-040f-489f-916f-68b73a32cd5fPV.xlsx";
//		//2、写入数据
//		org.apache.poi.ss.usermodel.Workbook workbook = null;
//		try {
//			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
//					fileName.length());
//			if (fileType.equals("xls")) {
//				workbook = new HSSFWorkbook(new FileInputStream(fileName));
//			} else if (fileType.equals("xlsx")) {
//				workbook = new XSSFWorkbook(new FileInputStream(fileName));
//			} else {
//				System.out.println( " 您的文档格式不正确！ ");
//			}
//			
//			
//			// 创建sheet对象
//			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
//			
//			String sheelName = sheet.getSheetName();
//			workbook.setSheetName(0, sheelName);
//		
//					sheet.getRow(6).getCell( 6).setCellValue( 111111);
//					sheet.getRow(7).getCell( 6).setCellValue( 111111);
//					sheet.getRow(8).getCell( 6).setCellValue( 111111);
//					//sheet.getRow(0).getCell( 6).setCellValue( 111111);
//	
//					System.out.println(sheet.getRow(8).getCell( 6).getNumericCellValue());
//
//			// 输出数据
//			FileOutputStream out = new FileOutputStream(fileName);
//			workbook.write(out);
//			out.flush();
//	        out.close();
//	         
//		} catch (Exception e) { 
//			e.printStackTrace();
//
//		}
	}
}
