package shang.util;

import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelTest extends TestCase {
	
	
	
	@Test
	public void testExportWord()
	{
		
		//����
		List  list =new ArrayList ();
		Students student = new Students();
		student.setId("1");
		student.setSex("��");
		student.setName("����");
		student.setAge(18);
		Teacher t = new Teacher();
		t.setName("����ʦ");
		student.setTeacher(t);
		
		list.add( student );
		
		student = new Students();
		student.setId("1");
		student.setSex("Ů");
		student.setName("ϱ��");
		student.setAge(18);
		student.setTeacher(t);
		
		list.add( student );
		
		//��
		List<ExcelColumnModelVo>  columnModels = new ArrayList<ExcelColumnModelVo>();
		columnModels.add( new ExcelColumnModelVo("��ʦ","teacher.name",20 ) );
		columnModels.add( new ExcelColumnModelVo("ID","id",20 ) );
		columnModels.add( new ExcelColumnModelVo("����","name",20 ) );
		columnModels.add( new ExcelColumnModelVo("�Ա�","sex",20 ) );
		
		
		//�����
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
		
		//����
		Students student = new Students();
		student.setId("1");
		student.setSex("��");
		student.setName("����");
		student.setAge(18);
		
		
		Field[] field = com.shang.util.Students.class.getDeclaredFields();
		for(Field f:field)
		{
			System.out.println( f.getName() );
			System.out.println( f.getGenericType() );
		}
		
	}
}
