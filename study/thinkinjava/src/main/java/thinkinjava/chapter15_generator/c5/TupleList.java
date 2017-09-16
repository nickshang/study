package thinkinjava.chapter15_generator.c5;

import net.mindview.util.FourTuple;

import java.util.ArrayList;

public class TupleList<A,B,C,D> extends ArrayList<FourTuple<A, B, C, D>> {
	public static void main(String[] args) {
		TupleList<String,String,String,String>  t = 
				new TupleList<>();
		
		t.add( new FourTuple<>("1","1","1","1"));
		t.add( new FourTuple<>("2","2","2","2"));
		t.add( new FourTuple<>("3","4","5","6"));
		
		for(FourTuple<String, String, String, String> i : t ){
			System.out.println(i);
		}
	}
}
