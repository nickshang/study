package com.shang.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcel {
	
	public static void main(String[] args) {
		ReadExcel excel = new ReadExcel();
		try {
			List<Map<String,String>> listmap = excel.readXls();
			int i = 0;
			for( Map map:listmap ) {
				if( map.get("地区") != null ){
					if( map.get("地区").toString().contains( "迁") ){
						System.out.println( "I:" + (i++) );
						System.out.println( "KEY:" + map.get("地区"));
						System.out.println( "KEY:" + map.get("用户编号"));
						System.out.println( "KEY:" + map.get("用户名称")); 
					} 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 private List<Map<String,String>> readXls() throws IOException{   
		 
		 	List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		 
		    InputStream is = new FileInputStream( "F:\\work\\23.江苏典型用户13\\12.文档配置管理\\04.客户提供资料\\02、方天\\02、客户用电量\\江苏各地市用户月电量排名（201401）_.xls");   
		    HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);    
		       
		    // 循环工作表Sheet   
//		    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
		    
		    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
		      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);   
		      if(hssfSheet == null){   
		        continue;   
		      }   
		      
		      List<String> clumn = new ArrayList<String>();
		         
		      // 循环行Row    
		      for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){   
		        HSSFRow hssfRow = hssfSheet.getRow( rowNum);   
		        if(hssfRow == null){   
		          continue;   
		        }   
		        
		        if(  rowNum == 0 ){
		        	 // 循环列Cell     
			        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){   
			          HSSFCell hssfCell = hssfRow.getCell( cellNum);   
			          if(hssfCell == null){   
			            continue;   
			          }      
			          clumn.add(  getValue( hssfCell) );
			        }
			        continue;
		        }
		           
		        // 循环列Cell     
		        Map<String,String> map = new HashMap<String, String>();
		        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){   
		          HSSFCell hssfCell = hssfRow.getCell( cellNum);   
		          if(hssfCell == null){   
		            continue;   
		          }   
		          
		          if( getValue( hssfCell) != null && !getValue( hssfCell).equals("") )
		          {
		        	  map.put(clumn.get( cellNum ), getValue( hssfCell) )   ; 
		          }
		          
		        }   
		        result.add( map );
		      }   
		    }  
		    return result;
		  }   
	 
	 @SuppressWarnings("static-access")  
	  private String getValue(HSSFCell hssfCell){  
	    if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
	      return String.valueOf( hssfCell.getBooleanCellValue());  
	    }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
	      return String.valueOf( hssfCell.getNumericCellValue());  
	    }else{  
	      return String.valueOf( hssfCell.getStringCellValue());  
	    }  
	  }  
}
