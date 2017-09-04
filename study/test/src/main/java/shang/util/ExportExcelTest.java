package shang.util;

import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelTest extends TestCase {
	
	
	
	@Test
	public void testExportWord()
	{
		
		//数据
		List  list =new ArrayList ();
		Students student = new Students();
		student.setId("1");
		student.setSex("男");
		student.setName("西风");
		student.setAge(18);
		Teacher t = new Teacher();
		t.setName("好老师");
		student.setTeacher(t);
		
		list.add( student );
		
		student = new Students();
		student.setId("1");
		student.setSex("女");
		student.setName("媳妇");
		student.setAge(18);
		student.setTeacher(t);
		
		list.add( student );
		
		//列
		List<ExcelColumnModelVo>  columnModels = new ArrayList<ExcelColumnModelVo>();
		columnModels.add( new ExcelColumnModelVo("老师","teacher.name",20 ) );
		columnModels.add( new ExcelColumnModelVo("ID","id",20 ) );
		columnModels.add( new ExcelColumnModelVo("姓名","name",20 ) );
		columnModels.add( new ExcelColumnModelVo("性别","sex",20 ) );
		
		
		//组合列
		List<ExcelColumnModelVo>  groupColumnModels = new ArrayList<ExcelColumnModelVo>();
		groupColumnModels.add( new ExcelColumnModelVo("ID","id",20 ,0,2,2,2) );
		
		ExportExcelUtil dao = new ExportExcelUtil();
		dao.setTitle("xxx");
		dao.setGroupColumnModel(groupColumnModels);
		dao.createExcel(list, columnModels, "d:\\test.xls");
		
	}
	
	
	@Test
	public void testExportBeanfiled()
	{
		
		//数据
		Students student = new Students();
		student.setId("1");
		student.setSex("男");
		student.setName("西风");
		student.setAge(18);
		
		
		Field[] field = com.shang.util.Students.class.getDeclaredFields();
		for(Field f:field)
		{
			System.out.println( f.getName() );
			System.out.println( f.getGenericType() );
		}
		
	}
}
