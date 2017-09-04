package effectivejava.effectivejava.chapter41;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：41条 慎用重载
 * 
 * @author NICK
 * 
 */
public class SetList {

	public static void main(String[] args) {
		
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> list = new ArrayList<Integer>();

		for (int i = -3; i < 3; i++) {
			set.add(i);
			list.add(i);
		}
		
		for (int i = 0; i < 3; i++) {
			set.remove(i);
			list.remove(i);
		}
		
		System.out.println(set + " : " +  list);
		
	}

}
