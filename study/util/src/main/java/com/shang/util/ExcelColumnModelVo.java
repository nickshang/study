package com.shang.util;


/**
 * 功能描述：Excel列模型
 * @author Nick
 *
 */
public class ExcelColumnModelVo {

	public ExcelColumnModelVo(){
	}
	
	/**
	 * 功能描述：初始化Excel列模型类
	 * @param header 列名
	 * @param dataIndex 列对应数据(JavaBean)字段
	 * @param width 宽度  
	 */
	public ExcelColumnModelVo(String header,String dataIndex,int width )
	{
		this.header = header;
		this.width = width;
		this.dataIndex = dataIndex;
	}
	
	
	
	/**
	 * 功能描述：初始化Excel列模型类
	 * @param header 列名
	 * @param dataIndex 列对应数据(JavaBean)字段
	 * @param width 宽度  
	 * @param scale 保留小数位
	 * @param roundingMode 舍取模式
	 */
	public ExcelColumnModelVo(String header,String dataIndex,int width  , int scale  ,  int roundingMode )
	{
		this.header = header;
		this.width = width;
		this.dataIndex = dataIndex;
		this.scale = scale; 
		this.roundingMode  = roundingMode;
		
	}
	
	/**
	 * 功能描述：初始化Excel列模型类
	 * @param header 列名
	 * @param dataIndex 列对应数据(JavaBean)字段
	 * @param width  宽度  
	 * @param cell0  
	 * @param row0
	 * @param cell1
	 * @param row1
	 */
	public ExcelColumnModelVo(String header,String dataIndex,int width ,int cell0,int row0, int cell1,int row1 )
	{
		this.header = header;
		this.width = width;
		this.dataIndex = dataIndex;
		this.cell0 = cell0;
		this.row0 = row0;
		this.cell1 = cell1;
		this.row1 = row1;
	}
	
	//列名
	private String header;
	
	//列名对应
	private String dataIndex;
	
	//列宽
	private int width;
	
	//行
	private int cell0;
	
	//列
	private int row0;

	//行
	private int cell1;
	
	//列
	private int row1;
	
	//保留小数位
	private int scale = 2 ;
	
	
	//舍取模式(四舍五入)
	private int roundingMode = java.math.BigDecimal.ROUND_HALF_UP;
	
	
	public int getCell0() {
		return cell0;
	}

	public void setCell0(int cell0) {
		this.cell0 = cell0;
	}

	public int getCell1() {
		return cell1;
	}

	public void setCell1(int cell1) {
		this.cell1 = cell1;
	}

	public int getRow0() {
		return row0;
	}

	public void setRow0(int row0) {
		this.row0 = row0;
	}

	public int getRow1() {
		return row1;
	}

	public void setRow1(int row1) {
		this.row1 = row1;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}


	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getRoundingMode() {
		return roundingMode;
	}

	public void setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
	}

	
}
