package thinkinjava.chapter15_generator.c2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedStackTest {
	LinkedStack<String> stack = null;

	@Before
	public void setUp() throws Exception {
		stack = new LinkedStack<String>();
		
		for(String s : "hello world nick !".split(" ")){
			stack.put(s);
		}
//		stack.put("1");
//		stack.put("2");
//		stack.put("2");
//		stack.put("3");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testpeek() {
		System.out.println( stack.peek() );
//		fail("Not yet implemented");
	}

	@Test
	public void testpop() {
		System.out.println( stack.pop() );
		System.out.println( stack.peek() );
//		fail("Not yet implemented");
	}
	
}

