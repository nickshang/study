package effectivejava.effectivejava.chapter42;

import java.util.Arrays;

/**
 * 测试可变参数用法
 * @author 
 *
 */
public class ArrayListTest {

	public static void main(String[] args) {
		
		int[] digits = { 3, 1, 3, 5, 5 };
		System.out.println(Arrays.asList(digits));
		
		System.out.println(Arrays.asList(3, 1, 3, 5, 5 ));
		System.out.println(Arrays.toString(digits));
	}
}
