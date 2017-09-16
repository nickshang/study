package com.shang.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 功能描述：创建Excel文件
 *
 * @author Nick
 */
public class ExportExcelUtil {

    /**
     * 功能描述：创建Excel
     *
     * @param list        数据集合
     * @param columnModel Excel列模型
     * @param fileName    文件路径(完整路径)
     * @return true:成功 false:失败
     */
    public boolean createExcel(List<Object> list, List<ExcelColumnModelVo> columnModel, String filepath) {

        boolean result = false;

        // 删除原有文件
        if (new File(filepath).isFile()) new File(filepath).delete();

        File file_create = null;
        FileOutputStream os = null;
        WritableWorkbook book = null;
        try {

            // 创建文件
            file_create = new File(filepath);
            file_create.createNewFile();
            os = new FileOutputStream(file_create);
            book = Workbook.createWorkbook(os);

            // 设置字体
            WritableFont fontBT = new WritableFont(WritableFont.createFont("宋体"), 16, WritableFont.BOLD); // 标题字体
            WritableFont fontContent = new WritableFont(WritableFont.createFont("楷体_GB2312"), 12, WritableFont.NO_BOLD);// 内容字体
            WritableCellFormat formatZBT = new WritableCellFormat(fontBT);// 表格主标题
            WritableCellFormat formatBT1 = new WritableCellFormat(fontContent);// 表格标题1：单位名称
            WritableCellFormat formatBT2 = new WritableCellFormat(fontContent);// 表格标题2：日期
            WritableCellFormat formatContent = new WritableCellFormat(fontContent);// 内容

            // 画线条
            formatZBT.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 线条
            formatBT1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 线条
            formatBT2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 线条
            formatContent.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 线条

            // 定义排列格式：居中，左对齐，右对齐
            formatZBT.setAlignment(jxl.format.Alignment.CENTRE);// 主标题居中
            formatBT1.setAlignment(jxl.format.Alignment.LEFT);// 表格标题1左对齐
            formatBT2.setAlignment(jxl.format.Alignment.RIGHT);// 表格标题2右对齐
            formatContent.setAlignment(jxl.format.Alignment.CENTRE);// 内容：居中对齐
            formatContent.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 内容：垂直对齐

            if (file_create.exists()) {

                int columnSize = columnModel.size();

                // 添加一个工作表
                WritableSheet sheet = book.createSheet("sheet0", 0);

                //设置单元格每列样式宽度
                for (int i = 0; i <= columnSize - 1; i++) {
                    if (columnModel.get(i).getWidth() == 0) {
                        sheet.setColumnView(0, 10);//将第一列的宽度设为10
                    } else {
                        sheet.setColumnView(i, columnModel.get(i).getWidth());    //将其他列的宽度设为20
                    }
                }

                // 第一行
                sheet.mergeCells(0, 0, columnSize - 1, 0); //合并第一行到第一列到最后一列
                sheet.addCell(new Label(0, 0, getTitle(), formatZBT)); //第一行，第一列（0,0） 单元格内容为:标题

                // 第二行
                sheet.mergeCells(0, 1, columnSize >= 3 ? columnSize - 3 : 0, 1); //合并第二行到第1列到最后第3列
                sheet.mergeCells(columnSize >= 3 ? columnSize - 2 : 0, 1, columnSize - 1, 1); //合并第二行到第3列到最后第1列
                Label label2 = new Label(0, 1, "", formatBT1);
                Label label3 = new Label(columnModel.size() >= 3 ? columnModel.size() - 2 : 0, 1, bz == null ? "统计时间：" + ConvertDate.getNowDate().substring(0, 10) : bz, formatBT2);
                sheet.addCell(label2);
                sheet.addCell(label3); //备注

                // 第三行
                int rows = 2;
                List<ExcelColumnModelVo> listGroupColumnModel = getGroupColumnModel();
                if (listGroupColumnModel != null) {
                    ExcelColumnModelVo vo = null;
                    for (int i = 0; i < listGroupColumnModel.size(); i++) {
                        vo = listGroupColumnModel.get(i);
                        sheet.mergeCells(vo.getCell0(), vo.getRow0(), vo.getCell1(), vo.getRow1());
                        sheet.addCell(new Label(vo.getCell0(), rows, vo.getHeader(), formatContent));
                    }
                    if (listGroupColumnModel.size() > 0) rows += 1;
                }


                // 第三行
                for (int i = 0; i < columnModel.size(); i++) {
                    sheet.addCell(new Label(i, rows, columnModel.get(i).getHeader(), formatContent));
                }
                if (columnModel.size() > 0) rows += 1;

                // 数据列 写入数据并关闭文件

                Object o = null;
                ExcelColumnModelVo vo = null;
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < columnModel.size(); j++) {

                        o = getValue(list.get(i), columnModel.get(j).getDataIndex());
                        vo = columnModel.get(j);

                        if (o instanceof Boolean) {

                            sheet.addCell(new Label(j, i + rows, o.toString(), formatContent));

                        } else if (o instanceof Byte) {

                            sheet.addCell(new jxl.write.Number(j, i + rows, (Byte) o, formatContent));

                        } else if (o instanceof Short) {

                            sheet.addCell(new jxl.write.Number(j, i + rows, (Short) o, formatContent));

                        } else if (o instanceof Integer) {

                            sheet.addCell(new jxl.write.Number(j, i + rows, (Integer) o, formatContent));

                        } else if (o instanceof Long) {

                            sheet.addCell(new jxl.write.Number(j, i + rows, (Long) o, formatContent));

                        } else if (o instanceof Float) {

                            sheet.addCell(new jxl.write.Number(j, i + rows, RoundUtil.round((Float) o, vo.getScale(), vo.getRoundingMode()), formatContent));

                        } else if (o instanceof Double) {

                            sheet.addCell(new jxl.write.Number(j, i + rows, RoundUtil.round((Double) o, vo.getScale(), vo.getRoundingMode()), formatContent));

                        } else if (o instanceof String || o instanceof Character) {

                            sheet.addCell(new Label(j, i + rows, o.toString(), formatContent));
                        } else {
                            sheet.addCell(new Label(j, i + rows, o == null ? "" : o.toString(), formatContent));
                        }
                    }
                }
                rows += 1;

                //合并打印时间列(最后一列)，并写入打印时间
                int row = list.size() + rows;
                sheet.mergeCells(0, row, columnSize >= 3 ? columnSize - 3 : 0, row);
                sheet.mergeCells(columnSize >= 3 ? columnSize - 2 : 0, row, columnSize - 1, row);
                sheet.addCell(new Label(0, row, "", formatBT2));
                sheet.addCell(new Label(columnSize >= 3 ? columnSize - 2 : 0, row, "打印时间：" + ConvertDate.getNowDate().substring(0, 10), formatBT2));
            }
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                book.write();
                book.close();
                os.close();
            } catch (WriteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 功能描述：获取对象，获取方法
     *
     * @param o    对象
     * @param fied 字段名
     * @return 结果对象<code>Object</code>
     */
    @SuppressWarnings("unchecked")
    public Object getValue(Object o, String fied) {

        Object result = null;
        try {
            String modenName = null;

            //确定方法字符串
            String type = "";
//        	 Field[] field = com.shang.util.Students.class.getDeclaredFields();
//     		for(Field f:field)
//     		{
//     			if( fied.equals(f.getName()) ) type = f.getGenericType().toString() ;
//     		}

            String _fied = null;
            if (fied.contains(".")) {
                _fied = fied.substring(1, fied.indexOf("."));
            } else {
                _fied = fied.substring(1);
            }

            if (null != type && type.toLowerCase().equals("boolean")) {
                modenName = "is" + fied.substring(0, 1).toUpperCase() + _fied;
            } else {
                modenName = "get" + fied.substring(0, 1).toUpperCase() + _fied;
            }

            //检查是否有该方法
            Method method = null;
            Method[] methods = o.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equals(modenName) && m.getParameterTypes().length == 0) {
                    method = m;
                    break;
                }
            }

            //利用反射，调用方法
            try {
                if (fied.contains(".")) {

                    result = method.invoke(o, null);

                    _fied = fied.substring(fied.indexOf(".") + 2, fied.length());
                    if (null != type && type.toLowerCase().equals("boolean")) {
                        modenName = "is" + fied.substring(fied.indexOf(".") + 1, fied.indexOf(".") + 2).toUpperCase() + _fied;
                    } else {
                        modenName = "get" + fied.substring(fied.indexOf(".") + 1, fied.indexOf(".") + 2).toUpperCase() + _fied;
                    }

                    methods = result.getClass().getDeclaredMethods();
                    for (Method m : methods) {
                        if (m.getName().equals(modenName) && m.getParameterTypes().length == 0) {
                            method = m;
                            break;
                        }
                    }

                    Object result1 = method.invoke(result, null);
                    return result1;

                } else {
                    result = method.invoke(o, null);
                }
            } catch (NullPointerException e) {
                System.out.println("调用字段" + fied + "方法异常!");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("调用字段" + fied + "方法异常!");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("调用字段" + fied + "方法异常!");
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }
//	
//	/**
//	 * 功能描述：获取对象，获取方法
//	 * @param o 对象 
//	 * @param fied 字段名
//	 * @return  结果对象<code>Object</code>
//	 */
//	public Object getValue(Object o,String fied  ){
//		
//		Object result = null;
//        try {
//        	 String modenName = null;
//        	 
//        	 //确定方法字符串
//        	 String type = "";
////        	 Field[] field = com.shang.util.Students.class.getDeclaredFields();
////     		for(Field f:field)
////     		{
////     			if( fied.equals(f.getName()) ) type = f.getGenericType().toString() ;
////     		}
////        	 
//        	 if( null!=type && type.toLowerCase().equals( "boolean" ) )
//        	 {
//        		 modenName = "is"+fied.substring(0, 1).toUpperCase()+fied.substring(1);
//        	 }else
//        	 {
//        		 modenName = "get"+fied.substring(0, 1).toUpperCase()+fied.substring(1);
//        	 }
//        	 
//        	 //检查是否有该方法
//        	 Method method = null;
//        	 Method[] methods =  o.getClass().getDeclaredMethods();
//        	 for(Method m:methods)
//        	 {
//        		 if( m.getName().equals(modenName) &&  m.getParameterTypes().length ==0 ){
//        			    method = m;
//        			    break;
//        		 }
//        	 }
//        	 
//        	 //利用反射，调用方法
//        	 try
//        	 {
//        		 result =  method.invoke(o, null) ;
//        	 } catch (NullPointerException e) {
//        		 System.out.println("调用字段"+fied+"方法异常!");
//      			 e.printStackTrace();
//      		}catch (IllegalAccessException e) {
//        		 System.out.println("调用字段"+fied+"方法异常!");
//     			e.printStackTrace();
//     		} catch (InvocationTargetException e) {
//     			 System.out.println("调用字段"+fied+"方法异常!");
//     			e.printStackTrace();
//     		}
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}catch (SecurityException e) {
//			e.printStackTrace();
//		} 
//		return result;
//	}


    /**
     * 文件标题
     */
    private String title;


    /**
     * 列组合模式
     */
    private List<ExcelColumnModelVo> GroupColumnModel;


    /**
     * 备注
     */
    private String bz;


    /**
     * 功能描述：获取备注
     *
     * @return <code>String</code>备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 功能描述：设置备注
     *
     * @return
     */
    public void setBz(String bz) {
        this.bz = bz;
    }


    /**
     * 功能描述：获取文件标题
     *
     * @return <code>String</code>文件标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 功能描述：设置文件标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 功能描述：获取列组合模式
     */
    public List<ExcelColumnModelVo> getGroupColumnModel() {
        return GroupColumnModel;
    }

    /**
     * 功能描述：设置列组合模式
     *
     * @return <code> List<ExcelColumnModelVo> </code>
     */
    public void setGroupColumnModel(List<ExcelColumnModelVo> groupColumnModel) {
        GroupColumnModel = groupColumnModel;
    }


}
