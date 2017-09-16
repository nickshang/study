package thinkinjava.chapter15_generator.c4;

import net.mindview.util.TwoTuple;

public class TupleTest {
	
	public static void main(String[] args) {
		
		TwoTuple<String,String> t1 = new TwoTuple<>("11","22");
		System.out.println(t1.first);
		System.out.println(t1.second);
	}

}
