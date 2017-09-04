
/**
 * @标题: ArrayListT2Test.java
 * @功能描述：TODO
 * @作者： NICK
 * @创建时间： 2016年4月22日 下午11:42:41
 * @version v1.0
 */

package _1arrary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/**
 * @类描述：
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月22日 下午11:42:41
 * @修改人：NICK
 * @修改时间：2016年4月22日 下午11:42:41
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */

public class ArrayListT2Test {
	
	private ArrayListT2 list = null;

	/**
	 * @描述:
	 * @throws Exception
	 * @返回类型 void
	 * @创建人 NICK
	 * @创建时间 2016年4月22日 下午11:42:41
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
	 * @描述:
	 * @throws Exception
	 * @返回类型 void
	 * @创建人 NICK
	 * @创建时间 2016年4月22日 下午11:42:41
	 * @throws
	 */
	@After
	public void tearDown() throws Exception {
		list.display();
		System.out.println( "数组大小："+ list.getSize() );; 
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
