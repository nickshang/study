package com.shang.excelExport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.shang.excelExport.JsonUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

/**
 * 功能描述：导出Excel文件模块内容
 * @author NICK
 *
 */
public class ExcelExportUtil {
	

	/**
	 * 功能描述：根据文件模块导出 
	 * @param dataSource 数据源
	 * @param fileName 文件
	 * @return
	 */
	public String Export( String fileName,  Map<String, Object> dataSource) {

		//1、读取Excel
		List<Object[]> list =  readExcel(fileName,0);
		for(Object[] o : list){
			System.out.println(java.util.Arrays.toString(o));
		}
		
		//2、循环Excel数据->查找设置的参数的集合
		List<XYValue> setList = searchSetList(list);
		
		//3、对Excel设置的数据进行转换，并从数据源获取数据
		List<XYValue> covertSetList = covertSetList( dataSource, setList );
		
		System.out.println("---");
		//4、将转换数据输出到Excel中
		//4.1 创建文件夹
		String exportLocalDir = fileName.substring(0, fileName.lastIndexOf(File.separator) + 1) + File.separator + "ExportExcel";
		File file = new File( exportLocalDir );
		if( ! file.isDirectory()  ) file.mkdir();
		
		//4.2 生成文件
		UUID  uuid = UUID.randomUUID();
		String _fileName = exportLocalDir + File.separator + uuid + fileName.substring(  fileName.lastIndexOf( File.separator )+1 );
		getFile( fileName,  _fileName, covertSetList ,  dataSource);

		return _fileName;
	}
	
	
	/**
	 * 功能描述：从Excel中查找数据源配置项的位置、值数据
	 * @param list 从Excel读取后的数据 
	 * @return 数据源配置项的位置、值数据
	 */
	private List<XYValue> searchSetList(List<Object[]> list){
		
		List<XYValue> setList = new ArrayList<XYValue>();
		int y = 0;
		for(Object[] s : list ){
			int x = 0;
			for(Object str : s){
				if( str instanceof String ){
					if(str.toString().trim().indexOf("{") >= 0 &&  str.toString().trim().indexOf("}") > 0){ 
						XYValue  xyValue = new XYValue();
						xyValue.setX(x);
						xyValue.setY(y);
						xyValue.setValue(str.toString());
						setList.add(xyValue);
					}
				}
				x++;
			}
			y++;
		}	
		return setList;
	}
	
	
	/**
	 * 功能描述：根据Excel中数据源配置项的位置、值数据，在数据源中查找数据，转换为每个单元格的数据
	 * @param dataSource
	 * @param setList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<XYValue>  covertSetList( Map<String, Object> dataSource , List<XYValue> setList ){
		
		//转换后的数据集合
		List<XYValue> covertSetList = new ArrayList<XYValue>(); 
		
		//对设置的数据集合依次进行转换
		for (XYValue setXYValuve : setList) {
			int _x = setXYValuve.getX();
			int _y = setXYValuve.getY();
			String _str = setXYValuve.getValue().toString();
			
			
			//1、对设置的数据从JSON转换为DataSourceBean对象
			DataSourceBean  bean = JsonUtil.fromJson( _str, DataSourceBean.class );
			
			//2、获取数据源
			Object o = dataSource.get( bean.getDs());
			List<Map<String,Object>> source = null;
			if ( o == null ) { 
				System.out.println( "数据源中没有名字：" +  bean.getDs() +"的数据！" ); 
			}
			if ( o instanceof List ) {
				source =  (List<Map<String,Object>>)o ;
			}
			
			//3、转换为数据源
			
			//3.1、如果X,Y轴没有填写数据，直接获取数据源的值
			if( ( bean.getX() == null  && bean.getY() == null ) || ( bean.getX().size() == 0 &&  bean.getY().size() == 0 ) ){
				
				XYValue value = new XYValue();
				value.setY(_y);
				value.setX(_x);
				value.setValue(dataSource.get(bean.getDs()));
				covertSetList.add( value );
				 
			} else if( source != null &&  (bean.getY() == null || bean.getY().size() == 0)  ) {   //3.2、如果Y轴没有填写数据源，按照数组的顺序依次填写数据
				for( int y1 = 0; y1 < source.size(); y1++ ){
					int z = 0;
					for(Object x1 : bean.getX() ){
						if(  x1 instanceof String   &&  !x1.toString().trim().equals("") ){
							Map<String,Object> map = source.get( (Integer)y1 );
							Object v = map.get( x1.toString() );
							
							XYValue value = new XYValue();
							value.setY(_y);
							value.setX(_x+z);
							value.setValue(v);
							covertSetList.add( value );
						}
						z++;
					} 
					_y++;
				}
			}else   if( source != null ){
				for(Object y1 : bean.getY() ){  //3.3、按照Y轴填写的数字，顺序依次填写数据
					int z = 0;
					for(Object x1 : bean.getX() ){
						if( y1 instanceof Integer  &&  x1 instanceof String &&  !x1.toString().trim().equals("")  ){
							Object v = source.get( (Integer)y1 ).get( x1.toString() );
							
							XYValue value = new XYValue();
							value.setY(_y);
							value.setX(_x+z);
							value.setValue(v);
							covertSetList.add( value );
						}
						z++;
					} 
					_y++;
				}
			}
		}
		return covertSetList;
	}
	
	
	/**
	 * 功能描述：根据文件名读取Excel中的数据
	 * @param fileName
	 * @return
	 */
	public List<Object[]> readExcel(String fileName, int sheetNumber ){
		
		List<Object[]> list = new java.util.ArrayList<Object[]>();
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());
			if (fileType.equals("xls")) {
				workbook = new HSSFWorkbook(new FileInputStream(fileName));
			} else if (fileType.equals("xlsx")) {
				workbook = new XSSFWorkbook(new FileInputStream(fileName));
			} else {
				System.out.println( " 您的文档格式不正确！ " );
			}
			
			// 创建sheet对象
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetNumber);
			int firstRowIndex = sheet.getFirstRowNum();
			int lastRowIndex = sheet.getLastRowNum();
			int coloumNum = 0;
			for(int i = 0; i < lastRowIndex; i++ ){
				coloumNum =  sheet.getRow(i).getPhysicalNumberOfCells() > coloumNum ?   sheet.getRow(i).getPhysicalNumberOfCells() :coloumNum;
			}
			
			System.out.println(" 获得总列数"+ coloumNum );
			for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
				Row row = sheet.getRow(rIndex);
				if (row != null) {
					int firstCellIndex = row.getFirstCellNum();
					int lastCellIndex = row.getLastCellNum();
					Object[] s = new Object[coloumNum];
					for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
						Cell cell = row.getCell(cIndex);
						Object value = "";

						if (cell != null) {
							value = cell.toString();
							int type = cell.getCellType();
							if (Cell.CELL_TYPE_NUMERIC == type) {
								// value =
								// String.valueOf(cell.getNumericCellValue());
								Double val = cell.getNumericCellValue();
								if (val == val.longValue()) {
									value = val.longValue();
								}
							}
							s[cIndex] = value;
						}
					}
					list.add(s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return list;
		
	}
	
	/**
	 * 功能描述：将转换数据输出到Excel中
	 * @param fileName 文件模块
	 * @param _fileName 生成的文件内容
	 * @param covertSetList 是否覆盖
	 * @return 生成的文件地址
	 */
	private String getFile(String fileName, String _fileName, List<XYValue> covertSetList , Map<String, Object> dataSource ){
		
		//1、复制文件 
		CopyFile( fileName , _fileName,true);
	
		//2、写入数据
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());
			if (fileType.equals("xls")) {
				workbook = new HSSFWorkbook(new FileInputStream(fileName));
			} else if (fileType.equals("xlsx")) {
				workbook = new XSSFWorkbook(new FileInputStream(fileName));
			} else {
				System.out.println( " 您的文档格式不正确！ ");
			}
			
			
			// 创建sheet对象
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			
			String sheelName = sheet.getSheetName();
			if( dataSource.get( sheelName ) != null ){
				sheelName = dataSource.get( sheelName ).toString();
			} 
			workbook.setSheetName(0, sheelName);
			
			// 输入数据
			for ( XYValue value : covertSetList ) {
			
				if( sheet.getRow(value.getY()).getCell( value.getX()) == null ){
					System.out.println("----单元格过滤:x="+ value.getX() +",y=" +value.getY() );
					continue;
				}
				
				if( value.getValue() instanceof Integer  )
					sheet.getRow(value.getY()).getCell( value.getX()).setCellValue( (Integer)value.getValue() );
			
				if( value.getValue() instanceof Float  )
					sheet.getRow(value.getY()).getCell( value.getX()).setCellValue( (Float)value.getValue() );
			
				if( value.getValue() instanceof Double  )
						sheet.getRow(value.getY()).getCell( value.getX()).setCellValue( (Double)value.getValue() );
			
				if( value.getValue() instanceof String  )
					sheet.getRow(value.getY()).getCell( value.getX()).setCellValue( value.getValue().toString() );
			}
			
			
			// 更新公式
			int firstRowIndex = sheet.getFirstRowNum();
			int lastRowIndex = sheet.getLastRowNum();
			for(int i=firstRowIndex; i<lastRowIndex; i++){
				 updateFormula( workbook, sheet, i);
			}
			
			
			// 输出数据
			FileOutputStream out = new FileOutputStream(_fileName);
			workbook.write(out);
			workbook.close();
			out.flush();
	        out.close();
	         
		} catch (Exception e) { 
			e.printStackTrace();

		}
		return  _fileName ;
	}
	
	
	
	 private static void updateFormula(Workbook wb,Sheet s,int row){  
         Row r=s.getRow(row);  
         Cell c=null;  
         BaseFormulaEvaluator eval=null;  
         if(wb instanceof HSSFWorkbook)  
             eval=new HSSFFormulaEvaluator((HSSFWorkbook) wb);  
         else if(wb instanceof XSSFWorkbook)  
             eval=new XSSFFormulaEvaluator((XSSFWorkbook) wb);  
         for(int i=r.getFirstCellNum();i<r.getLastCellNum();i++){  
             c=r.getCell(i);  
           //  if(c.getCellType()==Cell.CELL_TYPE_FORMULA)  
                 eval.evaluateFormulaCellEnum(c);  
         }  
     } 
	 

	/**
	 * 功能描述:复制文件
	 * @param file_old_path 需要复制文件路径
	 * @param file_new_path 复制后的文件路径
	 * @param overWriteFile 是否覆盖原文件 true:覆盖 false:不覆盖
	 * @return true：表示成功 false：表示失败
	 */
	public boolean CopyFile( String file_old_path , String file_new_path,boolean overWriteFile)
	{
		boolean check = false;
	
		//1.检查文件名是否相等
		if(file_old_path.equals(file_new_path)){
			return true;
		}
	
		//2.1 检查文件 文件存在 不能覆盖
		File file_new = new File(file_new_path);
		if(file_new.exists()&&!overWriteFile){
			return true;
		}
	
		//3.写入文件
		InputStream in = null;
		OutputStream out = null;
		int i =0;
		try {
			in = new FileInputStream(file_old_path);
			out = new FileOutputStream(file_new_path);
			
			byte[] beffer = new byte[1024] ;
		
			while( (i= in.read(beffer)) !=  -1 ){
				out.write(beffer,0,i);
			}
			out.flush();
			check = true;
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (out != null) 	out.close();
				
				if (in != null) 	out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public static void main(String[] args) {
		ExcelExportUtil util = new ExcelExportUtil();
		
		// 1、获取数据
//		IExportYDDataService service = new ExportYDDataServiceImpl();
//		List<Map<String, Object>> list_t = null;
//		try {
//			list_t = service.query("2015", "01");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		// 2、设置数据源
//		Map<String, Object> dataSource  = new HashMap<String, Object>();
//		dataSource.put("list", list_t);
//		
//		util.Export( "F:\\1.xlsx", dataSource  );
//
//		
	}
}
/**
 * 功能描述：X,Y坐标及对应值的BEAN
 * @author NICK
 *
 */
class XYValue{
	
	/**
	 * X轴值
	 */
	private int x ;
	
	/**
	 * Y轴值
	 */
	private int y ;
	
	
	/**
	 * 设置的数据源配置
	 */
	private Object value;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

/**
 * 功能描述：数据源配置BEAN
 * @author NICK
 *
 */
class DataSourceBean {
	
	/**
	 * 功能描述：数据源名字
	 */
	private String ds;

	
	/**
	 * 功能描述：X轴配置
	 */
	private List<Object> x;
	
	/**
	 * 功能描述：Y轴配置
	 */
	private List<Object> y;
	
	/**
	 * 功能描述：数据来源
	 */
	private String sql;
 

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public List<Object> getX() {
		return x;
	}

	public void setX(List<Object> x) {
		this.x = x;
	}

	public List<Object> getY() {
		return y;
	}

	public void setY(List<Object> y) {
		this.y = y;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
	

