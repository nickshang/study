
/**
 * @����: ArrayListT2Test.java
 * @����������TODO
 * @���ߣ� NICK
 * @����ʱ�䣺 2016��4��22�� ����11:42:41
 * @version v1.0
 */

package _1arrary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/**
 * @��������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��22�� ����11:42:41
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��4��22�� ����11:42:41
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */

public class ArrayListT2Test {
	
	private ArrayListT2 list = null;

	/**
	 * @����:
	 * @throws Exception
	 * @�������� void
	 * @������ NICK
	 * @����ʱ�� 2016��4��22�� ����11:42:41
	 * @throws
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayListT2(3); 
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		list.add(6);
		list.add(7);
		list.add(8);
	}

	/**
	 * @����:
	 * @throws Exception
	 * @�������� void
	 * @������ NICK
	 * @����ʱ�� 2016��4��22�� ����11:42:41
	 * @throws
	 */
	@After
	public void tearDown() throws Exception {
		list.display();
		System.out.println( "�����С��"+ list.getSize() );; 
	}

	
	/**
	 * Test method for {@link ArrayListT2#add(int)}.
	 */
	@Test
	public void testAdd() {
		
		try{
			list.add(9);
			list.add(10);
			list.add(11);
			list.add(12);
			list.add(13);
			
			list.add(14);
			list.add(15);
			list.add(16);
			
			
			list.add(17);
			list.add(32);
			list.add(33);
			list.add(34);
			list.add(35);
			
			list.add(35);
			list.add(37);
			list.add(39);
			
			list.add(0, -1);
			list.add(2, 88);
		}catch(Exception e){
			fail("Not yet implemented");
		}
	}

	/**
	 * Test method for {@link ArrayListT2#del(int)}.
	 */
	@Test
	public void testDel() {
		try{
			list.remove(0);
			list.remove(0);
			list.remove(0);
		}catch(Exception e){
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	/**
	 * Test method for {@link ArrayListT2#modify(int, int)}.
	 */
	@Test
	public void testModify() {
		try{
			list.set(1, 20);
		}catch(Exception e){
			fail("Not yet implemented");
		}
	}

	/**
	 * Test method for {@link ArrayListT2#query(int)}.
	 */
	@Test
	public void testQuery() {
		try{
			list.get(2);
		}catch(Exception e){
			fail("Not yet implemented");
		}
	}

	/**
	 * Test method for {@link ArrayListT2#display()}.
	 */
	@Test
	public void testDisplay() {
		fail("Not yet implemented");
	}

	
}
