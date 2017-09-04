package _1arrary;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayListT4Test {

	ArrayListT4<String> s = new ArrayListT4<String>();
	
	@Before
	public void setUp() throws Exception {
		s.add("1");
		s.add("2");
		s.add("3");
		s.add("4");
		s.add("5");
		s.add("6");
//		s.add("7");
//		s.add("8");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("容器中有效元素个数:" + s.getSize());
		s.display();
		
	}

	@Test
	public void testAddT() {
		s.add("7");
		
		s.add("8");
		s.add("9");
		s.add("10");
		s.add("11");
		s.add("12");
		s.add("13");
		s.add("14");
		
		s.add("15");
		s.add("16");
		s.add("17");
		s.add("18");
	}

	@Test
	public void testAddIntT() {
		s.add(7, "5s");
//		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		s.remove(1);
	}

	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		
		System.out.println( "获取到的值:" + s.get(2) );
//		fail("Not yet implemented");
	}

	@Test
	public void testGetByValue() {
		System.out.println(  String.format("获取值：%s的位置:%d", "2",s.getByValue("2")) );
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}

}
